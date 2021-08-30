package Pinduoudo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
// 3. 20%

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int T = scanner.nextInt();
//        if (T > 10 || T < 1){
//            System.out.println(-1);
//            return;
//        }
//        int[] len = new int[T];
//        int[] nums = new int[T];
//        for (int i = 0; i < T; i++) {
//            len[i] = scanner.nextInt();
//            if (len[i] < 1 || len[i] > 1000000){
//                System.out.println(-1);
//                return;
//            }
//            nums[i] = scanner.nextInt();
//            if (nums[i] < 1 || nums[i] > 1000000){
//                System.out.println(-1);
//                return;
//            }
//        }
//        for (int i = 0; i < T; i++) {
//            boolean flag = false;
//            for (int j = (int) Math.pow(10,len[i] - 1); j < Math.pow(10,len[i]); j++) {
//                if (j % nums[i] == 0){
//                    System.out.println(j);
//                    flag = true;
//                    break;
//                }
//            }
//            if (!flag){
//                System.out.println(-1);
//            }
//        }
//    }



//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        // 测试用例数量
//        int T = scanner.nextInt();
//        if (T > 10 || T < 1){
//            return;
//        }
//        // 每天的商品数量
//        ArrayList<ArrayList<Product>> arrayListArrayList = new ArrayList<>();
//        int[] nums = new int[T];
//        for (int i = 0; i < T; i++) {
//            nums[i] = scanner.nextInt();
//            ArrayList<Product> products = new ArrayList<>();
//            for (int j = 0; j < nums[i]; j++) {
//                // 评分和上架时间装入
//                int value = scanner.nextInt();
//                int lastTime = scanner.nextInt();
//                Product product =new Product(lastTime,value);
//                products.add(product);
//            }
//            arrayListArrayList.add(products);
//        }
//
//
//        for (int i = 0; i < T; i++) {
//            maxValue(arrayListArrayList.get(i));
//        }
//
//    }
//
//    static void maxValue(ArrayList<Product> products){
//        sortByValue(products);
//        int ans = 0;
//        if (products.size() <= maxTime(products)){
//            for (int i = 0;i < products.size();i++){
//                ans += products.get(i).getValue();
//            }
//            System.out.println(ans);
//        } else {
//            for (int i = 0;i < maxTime(products);i++){
//                  ans += products.get(i).getValue();
//            }
//        }
//    }
//
//    static void sortByValue(ArrayList<Product> products){
//        for (int i = 0;i < products.size();i++){
//            for (int j = i + 1; j < products.size();j++){
//                if (products.get(i).getValue() < products.get(j).getValue()){
//                    Product tmp = products.get(i);
//                    products.set(i,products.get(j));
//                    products.set(j,tmp);
//                }
//            }
//        }
//    }
//
//    static int maxTime(ArrayList<Product> products){
//        int ans = Integer.MIN_VALUE;
//        for (int i = 0;i < products.size();i++){
//            ans =Math.max(ans,products.get(i).getLastTime());
//        }
//        return ans;
//    }
//
//
//    static class Product{
//        private Integer lastTime;
//        private Integer value;
//
//        public Product(Integer lastTime, Integer value) {
//            this.lastTime = lastTime;
//            this.value = value;
//        }
//
//        public Integer getLastTime() {
//            return lastTime;
//        }
//
//        public void setLastTime(Integer lastTime) {
//            this.lastTime = lastTime;
//        }
//
//        public Integer getValue() {
//            return value;
//        }
//
//        public void setValue(Integer value) {
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            return "Product{" +
//                    "lastTime=" + lastTime +
//                    ", value=" + value +
//                    '}';
//        }
//    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 测试用例数量
        int T = scanner.nextInt();
        if (T > 20 || T < 1){
            return;
        }
        ArrayList<ArrayList<Integer>> arrayListArrayList = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(scanner.nextInt());
            arrayList.add(scanner.nextInt());
            arrayListArrayList.add(arrayList);
            arrayList.clear();
        }
    }

    static void backTrace(int now,int target,Integer ans,int record){
        if (target == now){
            return;
        }
        else {
            record++;
            backTrace(now,target - 1,ans,record);
            backTrace(now,target * 2,ans,record);
            backTrace(now,target + 1,ans,record);
            backTrace(now,target - 2,ans,record);
        }
        return;
    }
}
