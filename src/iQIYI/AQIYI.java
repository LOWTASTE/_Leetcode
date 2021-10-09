package iQIYI;

import java.util.Scanner;
public class AQIYI {

//    在监控与BI报表系统中，我们经常会采集数据指标进行分析，这里的数据往往都是时序数据，
//    对于时序数据，我们可以用一个数组来表示，例如数组下标表示时间顺序，
//    数组的值表示采集的指标数据大小。现在作为分析师的你，
//    得到如下一个任务：在给定一个整数形式的时序数据，求出这个时序里最大的振幅（“落差”）
//    （振幅 = 时序里相邻的“波峰”与“波谷”相差绝对值）

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
////        ArrayList<Integer> arrayList = new ArrayList<>();
//        String[] nums = scanner.nextLine().split(",");
//        int max = Integer.MIN_VALUE;
//        int min = Integer.MAX_VALUE;
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            arrayList.add(Integer.valueOf(nums[i]));
//        }
//        int[] leftMax = new int[arrayList.size()];
//        leftMax[0] = arrayList.get(0);
//        int[] rightMax = new int[arrayList.size()];
//        rightMax[0] = arrayList.get(arrayList.size()- 1);
//        int[] lenMax = new int[arrayList.size()];
//        for (int i = 1; i < arrayList.size(); i++) {
//            leftMax[i] = Math.max(leftMax[i - 1],arrayList.get(i));
//        }
//        for (int i = 1; i < arrayList.size(); i++) {
//            rightMax[i] = Math.max(rightMax[i - 1],arrayList.get(i));
//        }
//        for (int i = 0; i < arrayList.size(); i++) {
//            leftMax[i] = Math.max(Math.abs(leftMax[i] - arrayList.get(i)),Math.abs(rightMax[i] - arrayList.get(i)));
//        }
//        int ans = Integer.MIN_VALUE;
//        for (int i = 0; i < arrayList.size(); i++) {
//            ans = Math.max(ans, lenMax[i]);
//        }
//
//
//        System.out.println(ans);
//
//    }




    // 皇后问题
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nStr = scanner.nextLine();
        String[] input = nStr.split("=");
        int n =  Integer.parseInt(input[1]);
        System.out.println(n);
        // 皇后回溯？
    }

    public static void backtrace(int posX, int posY, int[][] output){

    }

}





