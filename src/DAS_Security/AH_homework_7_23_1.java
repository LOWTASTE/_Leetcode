package DAS_Security;

import java.util.Random;

// 随机产生100个0～100的数字，以20为1组，使用5个线程对每组数据进行累加。最后求总和。
public class AH_homework_7_23_1 {
        public static void main(String[] args) throws Exception{
            Random random = new Random();
            int nums[] = new int[100];
            for (int i = 0; i < 100; i++) {
                nums[i] = Math.abs(random.nextInt() % (100 + 1));
                System.out.print(nums[i] + "   ---   ");
            }
            int trueAns = 0;
            for (int i = 0; i < 100; i++) {
                trueAns += nums[i];
            }
            System.out.println("  ==========  ");
            System.out.println("true answer:  " + trueAns);
            int ans = 0;
            int batchNum = 5;
            int batchSize = 20;
            int pos = 0;
            for (int i = 0; i < 5; i++) {
                Calculator calculator = new Calculator(pos, batchSize + pos, nums);
                pos = pos + batchSize;
                new Thread(calculator).start();
                Thread.sleep(1);
                int result = calculator.getResult();
                ans += result;
            }
            System.out.println("================");
            System.out.println("real answer:  " + ans);
            System.out.println("================");

        }
    }

    class Calculator implements Runnable{
        private volatile int sum = 0;
        private int start;
        private int end;
        private int[] source;

        public Calculator(int start, int end, int[] source) {
            this.start = start;
            this.end = end;
            this.source = source;
        }

        @Override
        public synchronized void run() {
            for (int i = start; i < end; i++) {
                sum += source[i];
            }
        }

        public int getResult() {
            return sum;
        }
}
