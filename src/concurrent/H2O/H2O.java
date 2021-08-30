package concurrent.H2O;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class H2O {
    public static void main(String[] args) {
        H2O2 h2o = new H2O2();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        h2o.hydrogen(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("H");
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        h2o.oxygen(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("O");
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

// Semaphore 信号量
class H2O1 {
    private Semaphore hSema = new Semaphore(2);
    private Semaphore oSema = new Semaphore(0);

    public H2O1() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSema.acquire();
        releaseHydrogen.run();
        oSema.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSema.acquire(2);
        releaseOxygen.run();
        hSema.release(2);
    }
}


class H2O2 {
    private Semaphore hSema = new Semaphore(2);
    private Semaphore oSema = new Semaphore(1);
    private CyclicBarrier cb = new CyclicBarrier(3);

    public H2O2() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSema.acquire();
        try {
            cb.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        releaseHydrogen.run();
        hSema.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSema.acquire();
        try {
            cb.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        releaseOxygen.run();
        oSema.release();
    }
}

class H2O3 {

    private int oCnt = 0;
    private int hCnt = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition con = lock.newCondition();

    public H2O3() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (hCnt == 2) {
                con.await();
            }
            hCnt++;
            if (hCnt == 2 && oCnt == 1) {
                hCnt = 0;
                oCnt = 0;
            }
            releaseHydrogen.run();
            con.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (oCnt == 1) {
                con.await();
            }
            oCnt++;
            if (hCnt == 2 && oCnt == 1) {
                hCnt = 0;
                oCnt = 0;
            }
            releaseOxygen.run();
            con.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class H2O4 {

    private int cnt = 0;
    private BlockingQueue<Integer> hQ = new LinkedBlockingDeque<>(2);
    private BlockingQueue<Integer> oQ = new LinkedBlockingDeque<>(1);

    public H2O4() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hQ.put(1);
        releaseHydrogen.run();
        cnt++;
        if (cnt == 3) {
            cnt = 0;
            hQ.clear();
            oQ.clear();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oQ.put(1);
        releaseOxygen.run();
        cnt++;
        if (cnt == 3) {
            cnt = 0;
            hQ.clear();
            oQ.clear();
        }
    }
}