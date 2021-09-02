package concurrent.semaphore;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class demo1 {
}

/**
 * Semaphore method
 */
class ZeroEvenOdd1 {
    private int n;

    // 默认 zero 有一个信号量可用
    private Semaphore zero = new Semaphore(1);

    // 默认 even 和 odd 没有可用信号量
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd1(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1;i <= n; i++){

            // 首次执行时， zero 有一个可用的信号量
            zero.acquire();
            printNumber.accept(0);
            if(i % 2 == 1){
                // 可以理解为 odd 增加一个信号量，这样 odd 可以继续走流程
                odd.release();
            }else{
                // 可以理解为 even 增加一个信号量， 这样 even 可以继续走流程
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        for (int i = 2; i <= n;i += 2){

            // 等待信号量，获取到了信号后，往下走
            even.acquire();

            printNumber.accept(i);

            // 发送信号量给 zero
            zero.release();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1; i <= n; i += 2){

            // 等待信号量，获取到了信号后，往下走
            odd.acquire();
            printNumber.accept(i);

            // 发送信号量给 zero
            zero.release();
        }
    }
}

/**
 * CountDownLatch method
 */
class ZeroEvenOdd2 {
    private int n;
    // CountDownLatch(0); permit run
    private CountDownLatch zeroLatch = new CountDownLatch(0);
    private CountDownLatch evenLatch = new CountDownLatch(1);//偶数
    private CountDownLatch oddLatch = new CountDownLatch(1);//奇数

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroLatch.await();
            printNumber.accept(0);//打印0
            zeroLatch = new CountDownLatch(1);
            if ((i & 1) == 1) oddLatch.countDown();//如果是奇数，就打印奇数
            else evenLatch.countDown();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) {
                evenLatch.await();//开始打印偶数
                printNumber.accept(i);
                evenLatch = new CountDownLatch(1);
                zeroLatch.countDown();//是否zero线程
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 1) {
                oddLatch.await();//开始打印奇数
                printNumber.accept(i);
                oddLatch = new CountDownLatch(1);
                zeroLatch.countDown();//是否zero线程
            }
        }
    }
}

/**
 * ReentrantLock + Condition method
 */
class ZeroEvenOdd3 {
    private int n;

    private volatile int start = 1;

    private volatile int state;
    private Lock lock = new ReentrantLock();
    private Condition zero = lock.newCondition();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();

    public ZeroEvenOdd3(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (start <= n) {
                if (state != 0) {
                    zero.await();
                }
                printNumber.accept(0);
                if (start % 2 == 0) {
                    state = 2;
                    even.signal();
                } else {
                    state = 1;
                    odd.signal();
                }
                zero.await();
            }
            odd.signal();
            even.signal();
        } finally {
            lock.unlock();
        }
    }

    //偶数
    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (start <= n) {
                if (state != 2) {
                    even.await();
                } else {
                    printNumber.accept(start++);
                    state = 0;
                    zero.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    //基数
    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (start <= n) {
                if (state != 1) {
                    odd.await();
                } else {
                    printNumber.accept(start++);
                    state = 0;
                    zero.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}

/**
 * currentHshMap
 */
class ZeroEvenOdd4 {
    private int n;
    private Map<String, Thread> map = new ConcurrentHashMap<>();
    volatile int state = 0;// 0 打印 0 ， 1 打印奇数， 2 打印偶数

    public ZeroEvenOdd4(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        map.put("zero", Thread.currentThread());
        for (int i = 1; i <= n; i++) {
            while (state != 0) {
                LockSupport.park();
            }
            printNumber.accept(0);
            if ((i & 1) == 0) {//偶数
                state = 2;
            } else {
                state = 1;
            }
            map.forEach((k, v) -> LockSupport.unpark(v));//通知其他两个线程
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        map.put("even", Thread.currentThread());
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
                LockSupport.park();
            }
            printNumber.accept(i);
            state = 0;
            LockSupport.unpark(map.get("zero"));//通知zero线程
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        map.put("odd", Thread.currentThread());
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                LockSupport.park();
            }
            printNumber.accept(i);
            state = 0;
            LockSupport.unpark(map.get("zero"));
        }
    }
}

/**
 * while + Thread.yield();
 */
class ZeroEvenOdd5 {

    private int n;
    private volatile int state;
    private volatile boolean control = true;

    public ZeroEvenOdd5(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            printNumber.accept(0);
            if (control) {
                state = 1;
            } else {
                state = 2;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
                Thread.yield();
            }
            printNumber.accept(i);
            control = true;
            state = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            control = false;
            state = 0;
        }
    }
}

/**
 * Semaphore method
 */
class FizzBuzz1 {
    private int n;

    private Semaphore number = new Semaphore(1);
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);


    public FizzBuzz1(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                fizz.acquire();
                printFizz.run();
                number.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                buzz.acquire();
                printBuzz.run();
                number.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            number.acquire();
            if (i % 3 != 0 && i % 5 != 0) {//开始打印
                printNumber.accept(i);
                number.release();
            } else if (i % 3 == 0 && i % 5 != 0) {//fizz开始打印
                fizz.release();
            } else if (i % 3 != 0 && i % 5 == 0) {//buzz开始打印
                buzz.release();
            } else {
                fizzbuzz.release();//fizzbuzz开始打印
            }
        }
    }
}

/**
 * CyclicBarrier method
 */
class FizzBuzz2 {
    private int n;
    private CyclicBarrier cb = new CyclicBarrier(4);

    public FizzBuzz2(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                printFizz.run();
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                printBuzz.run();
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                printFizzBuzz.run();
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}