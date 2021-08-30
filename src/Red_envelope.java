import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Red_envelope {
        public static void main(String[] args) {
            int peopleCount = 10;
            int totalMoney = 100;
            getRandomMoney(peopleCount, totalMoney);
            System.out.print(divideRedPackage(totalMoney,peopleCount));
            Integer sum = 0;
            System.out.print(divideRedPackage_1(Double.parseDouble(String.valueOf(totalMoney)),peopleCount));
        }

        private static void getRandomMoney(int peopleCount, int totalMoney) {
            List<Integer> list = new ArrayList<Integer>();
            for(int i=0; i<peopleCount-1; i++) {
                int r = (int)(Math.random() * (totalMoney - 1) + 1);
                if(list.contains(r)) {
                    i--;
                }
                else {
                    list.add(r);
                }
            }
            list.add(0);
            list.add(totalMoney);
            Collections.sort(list);
            for(int j=0; j<peopleCount; j++) {
                int get = list.get(j+1) - list.get(j);
                System.out.println("第" + (j+1) +"个人抢到:" + get);
            }
        }

        public static List divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
            List amountList = new ArrayList();
            //为了使用random.nextInt(Integer)方法不得不先把红包金额放大100倍，最后在main函数里面再除以100
            //这样就可以保证每个人抢到的金额都可以精确到小数点后两位
            Integer restAmount = totalAmount * 100;
            Integer restPeopleNum = totalPeopleNum;
            Random random = new Random();
            for (int i = 0; i < totalPeopleNum - 1; i++) {
            // 随机范围：[1，剩余人均金额的两倍)，左闭右开
                int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
                restAmount -= amount;
                restPeopleNum--;
                amountList.add(amount/100.0);
            }
            amountList.add(restAmount/100.0);
            return amountList;

        }

        public static List<BigDecimal> divideRedPackage_1(double totalAmount, Integer totalPeopleNum){
            List amountList = new ArrayList();
            BigDecimal restAmount = new BigDecimal(Double.toString(totalAmount));
            restAmount.multiply(new BigDecimal(Double.parseDouble("100")));
            Integer restPeopleNum = totalPeopleNum;
            Random random = new Random();
            for (int i = 0; i < totalPeopleNum - 1; i++) {
                int amount = random.nextInt((int) (restAmount.doubleValue() / restPeopleNum * 2 - 1)) + 1;
                restAmount.subtract(BigDecimal.valueOf(amount));
                restPeopleNum--;
                amountList.add(amount/100.0);
            }
            return amountList;
        }

}
