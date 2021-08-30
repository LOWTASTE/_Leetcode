package Test;

import java.util.PriorityQueue;

public class QueueTest {
    static int[] a={6,4,7,3,9,8,1,2,5,0};

    public static void main(String[] args) {
        fun();
    }

    static void fun() {
        PriorityQueue<Integer> que=new PriorityQueue<Integer>();
        for(int e:a) {
            que.add(e);
        }
        for(int e:que) {
            System.out.print(e+" ");
        }
        System.out.println();
        while(!que.isEmpty()) {
            int e=que.remove();
            System.out.print(e+" ");
        }
    }
}