package iQIYI;

import java.util.concurrent.Semaphore;

public class Demo {
    static  int result = 0;
    public static void main(String[] args) throws InterruptedException {
        int N = 3;
        Thread[] threads = new Thread[N];//声明N个数据
        Semaphore[] semaphore = new Semaphore[N];//声明N个信息号量
        for(int i=0;i<N;i++){
            semaphore[i] = new Semaphore(1);
            if(i != N - 1){
                //最后一个不获取信号量许可
                semaphore[i].acquire();//获取一个信号量许可
            }
        }
        for(int i = 0;i < N;i++){
            Semaphore lastSemaphore = i==0 ? semaphore[N-1]:semaphore[i-1];//0:2 1:0 2:1   0的最后一个信号量是2,  1的最后一个信号量是0,  2的后一个信号量是1
            Semaphore currentSemaphore = semaphore[i];//当前信号量
            int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            lastSemaphore.acquire(); //每次只会有一个信号量获得许可，顺序为2,0,1
                            System.out.println("thread"+ index +":"+(result++));
                            if(result>100){
                                System.exit(0);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            currentSemaphore.release();
                        }
                    }
                }
            });
            threads[i].start();
        }
    }
}
