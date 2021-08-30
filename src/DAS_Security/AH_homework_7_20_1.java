package DAS_Security;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AH_homework_7_20_1 {
    // important
        static Object syn = new Object();
        public static String next = "a";

        public static void main(String[] args) {
            new AH_homework_7_20_1().print();
        }

        private void print(){
            ExecutorService service =  Executors.newFixedThreadPool(3);
            service.execute(new APrintThread());
            service.execute(new BPrintThread());
            service.execute(new CPrintThread());
        }

        class APrintThread implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    synchronized (syn) {
                        while (!"a".equals(next)) {
                            try {
                                syn.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.out.println("hello");
                        next = "b";
                        syn.notifyAll();
                    }
                }
            }
        }
        class BPrintThread implements Runnable{
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    synchronized (syn) {
                        while(!"b".equals(next)){
                            try {
                                syn.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.out.println("beautiful");
                        next = "c";
                        syn.notifyAll();
                    }
                }
            }
        }
        class CPrintThread implements Runnable{
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    synchronized (syn) {
                        while(!"c".equals(next)){
                            try {
                                syn.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        System.out.println("world");
                        next = "a";
                        syn.notifyAll();
                    }
                }
            }
        }
}
