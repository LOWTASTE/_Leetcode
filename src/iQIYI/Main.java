package iQIYI;

import javax.management.ObjectName;
import java.util.Scanner;
import java.util.function.IntConsumer;

class Main {
    public static void main(String[] args) {
        final Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        new Thread(() -> {
            try {
                zeroEvenOdd.printZero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.printEven(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.printOdd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class ZeroEvenOdd {
    private int n;
    private volatile int count = 0;
    static final Object object = new Object();
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void printZero(IntConsumer printNumber) throws InterruptedException {
        synchronized (object){
            while (count % 2 == 0){
                try {
                    object.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("0");
            count++;
            object.notifyAll();
        }
    }

    public void printEven(IntConsumer printNumber) throws InterruptedException {
        synchronized (object){
            while ((count - 1) % 4 == 0){
                try {
                    object.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(count);
            count++;
            object.notifyAll();
        }
    }

    public void printOdd(IntConsumer printNumber) throws InterruptedException {
        synchronized (object){
            while ((count - 3) % 4 == 0){
                try {
                    object.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(count);
            count++;
            object.notifyAll();
        }
    }


}

