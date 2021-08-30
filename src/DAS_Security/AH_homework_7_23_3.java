package DAS_Security;

import java.util.*;

public class AH_homework_7_23_3 {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        for(int i = 0;i < 6;i++){
            myThreadPool.add(new MyThread(i));
        }
        myThreadPool.execute();
    }
}


/**
 * 自定义简单线程池
 */
class MyThreadPool {
    private int currThreadNum = 0;
    private int coreThreadNum = 2;
    private int maxThreadNum = 5;
    private Queue<MyThread> threadQueue = new ArrayDeque<>();
    private Queue<MyThread> blockThreadQueue = new ArrayDeque<>();
    public synchronized void add(MyThread currThread){
        if (currThreadNum >= maxThreadNum) {
            System.out.println("thread out of max number");
        } else {
            if (currThreadNum >= coreThreadNum) {
                blockThreadQueue.offer(currThread);
                System.out.println(currThread.getTid() + " Thread is added blockThreadQueue");
            }
            else {
                threadQueue.offer(currThread);
                System.out.println(currThread.getTid() + " Thread is added threadQueue");
            }
            currThreadNum++;
        }
    }
    public void execute(){
        while (threadQueue!=null){
            Objects.requireNonNull(threadQueue.poll()).start();
            while (threadQueue.size() < coreThreadNum){
                if (blockThreadQueue != null){
                    threadQueue.offer(blockThreadQueue.poll());
                }
            }
        }
    }

}
class MyThread extends Thread{
    private int tid;
    MyThread(int id){
        tid = id;
    }

    public int getTid() {
        return tid;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(tid + "` Thread is running");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}