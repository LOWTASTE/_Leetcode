package Alibaba;

import java.util.ArrayList;
import java.util.Scanner;

public class AlibabaTest {
//        1st 1solution time out
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//            int len = sc.nextInt();
//            ArrayList<Integer> arrayList = new ArrayList<>();
//            for (int i = 0;i < len;i++){
//                arrayList.add(sc.nextInt());
//            }
//            sc.close();
//
//            int ans = Integer.MIN_VALUE;
//            int index = -1;
//            for (int i = 0;i < len;i++){
//                int prev = 0;
//                int next = 0;
//                for (int j = 0;j <= i; j++){
//                    prev += arrayList.get(j);
//                }
//                for (int k = i + 1;k < len;k++){
//                    next += arrayList.get(k);
//                }
//                ans =Math.max(prev * next , ans);
//                if (ans == prev * next){
//                    index = i + 1;
//                }
//            }
//            System.out.println(index);
//        }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int l = sc.nextInt();
//        int r = sc.nextInt();
//        sc.close();
//        if (r < l){
//            return;
//        }
//        int[] ans = new int[n];
//        for (int i = l; i <= r; i++) {
//            int res = n % i;
//            for (int j = 0; j < res;j++){
//                ans[n - 1 - j]++;
//            }
//        }
//        // 3.
//        for (int num : ans){
//            System.out.print(num + " ");
//        }
//    }


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        long n = sc.nextLong();
//        long l = sc.nextLong();
//        long r = sc.nextLong();
//        sc.close();
//        if (r < l){
//            return;
//        }
//        if (r - l > 3 * Math.pow(10,5)){
//            return;
//        }
//        // 35%
////        long[] ans = new long[(int) n];
//        //
//        long[] ans = new long[Math.toIntExact(n)];
//        for (int i = (int) l; i <= r; i++) {
//            int res = (int) (n % i);
//            for (int j = 0; j < res;j++){
//                ans[(int) (n - 1 - j)]++;
//            }
//        }
//        for (long num : ans){
//            System.out.print(num + " ");
//        }
//    }

// 13.33 %

//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//            int len = sc.nextInt();
//            if(len < 2){
//                return;
//            }
//            ArrayList<Integer> arrayList = new ArrayList<>();
//            for (int i = 0;i < len;i++){
//                arrayList.add(sc.nextInt());
//            }
//            sc.close();
//            int prev = 0;
//            int next = 0;
//            for (int i = 0; i < len - 1 ; i ++){
//                next += arrayList.get(i);
//            }
//            int ans = Integer.MIN_VALUE;
//            int index = -1;
//            for (int i = 0;i < len;i++){
//                prev += arrayList.get(i);
//                next -= arrayList.get(i);
//                ans = Math.max(prev*next , ans);
//                if (ans == prev*next){
//                    index = i + 1;
//                }
//            }
//
//            System.out.println(index);
//        }

//    public static int minCost(int[] cost){
//        if (cost.length == 0){
//            return 0;
//        }
//        if (cost.length == 1){
//            return cost[0];
//        }
//        if (cost.length == 2){
//            return Math.min(cost[0],cost[1]);
//        }
//        int[] dp = new int[cost.length];
//        dp[0] = cost[0];
//        dp[1] = cost[1];
//        for (int i = 2; i < dp.length; i++) {
//            dp[i] = Math.min(dp[i - 1],dp[i - 2]) + cost[i];
//        }
//        return Math.min(dp[dp.length - 1],dp[dp.length - 2]);
//    }
//
//    public static void main(String[] args) {
////        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
////        System.out.println(minCost(cost));
//
//
//    }

//    public static class TreeNode{
//        TreeNode left;
//        TreeNode right;
//        Integer val;
//
//        TreeNode(Integer val){
//            this.val = val;
//        }
//
//        public TreeNode getLeft() {
//            return left;
//        }
//
//        public TreeNode getRight() {
//            return right;
//        }
//
//        public void setRight(TreeNode right) {
//            this.right = right;
//        }
//
//        public void setLeft(TreeNode left) {
//            this.left = left;
//        }
//
//        @Override
//        public String toString() {
//            return "TreeNode{" +
//                    "left=" + left +
//                    ", right=" + right +
//                    '}';
//        }
//    }

    // 转换为数组判断
//    public static boolean isChildStruct(TreeNode A,TreeNode B){
//        ArrayList<Integer> resA = new ArrayList<>();
//        ArrayList<Integer> resB = new ArrayList<>();
//        dfs(A,resA);
//        dfs(B,resB);
//        System.out.println(resA);
//        System.out.println(resB);
//        for (int i = 0; i < resA.size(); i++) {
//            if (resA.get(i).equals(resB.get(0))){
//                for (int j = 0; j < resB.size(); j++) {
//                    if (!resB.get(j).equals(resA.get(j + i))){
//                        break;
//                    } else if (j == resB.size() - 1){
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public static void dfs(TreeNode treeNode, ArrayList<Integer> res){
//        if (treeNode == null){
//            return;
//        }else {
//            res.add(treeNode.val);
//            dfs(treeNode.left, res);
//            dfs(treeNode.right,res);
//        }
//    }
//
//    public static void main(String[] args) {
//        TreeNode A3 = new TreeNode(3);
//        TreeNode A4 = new TreeNode(4);
//        TreeNode A5 = new TreeNode(5);
//        TreeNode A1 = new TreeNode(1);
//        TreeNode A2 = new TreeNode(2);
//        A3.setLeft(A4);
//        A3.setRight(A5);
//        A4.setLeft(A1);
//        A4.setRight(A2);
//
//        TreeNode B4 = new TreeNode(4);
//        TreeNode B1 =new TreeNode(1);
//        B4.setLeft(B1);
//
//        System.out.println(isChildStruct(A3,B4));
//
//    }

}
