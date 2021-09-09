package SwordToOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SwordToOffer {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 09
//        Solution.CQueue cQueue = new Solution.CQueue();
//        cQueue.appendTail(52);
//        cQueue.appendTail(25);
//        cQueue.appendTail(99);
//        System.out.println(cQueue.deleteHead());
//        System.out.println(cQueue.deleteHead());
//        System.out.println(cQueue.deleteHead());
//        System.out.println(cQueue.deleteHead());
        // 10
//        System.out.println(solution.numWays(44));
        // 14
//        System.out.println(solution.cuttingRope(10));
        // 57
//        solution.findContinuousSequence(99);
        // 45
//        int[] arr = {3,30,34,5,9};
//        System.out.println(solution.minNumber(arr));
        // 46
//        System.out.println(solution.translateNum(21522));
        // 47
//        int[][] input = {{1,2,5},{3,2,1}};
//        System.out.println(solution.maxValue(input));

    }
}




class Solution {


    /**
     * 09
     */
    public static class CQueue {
        private final Stack<Integer> stack1;
        private final Stack<Integer> stack2;
        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            while (!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
            stack1.push(value);
        }

        public int deleteHead() {
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            if (stack2.isEmpty()){
                return -1;
            }
            return stack2.pop();
        }
    }

    /**
     * 10 - 01
     */
    public int fib(int n) {
        final int MOD = 1000000007;
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = (p + q) % MOD;
        }
        return r;
    }

    /**
     * 10 - 02
     */
    public int numWays(int n) {
        if (n == 0 || n == 1){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = (int) ((dp[i - 1] + dp[i - 2]) % (Math.pow(10,9) + 7));
        }
        return dp[n];
    }

    /**
     * 14 - 01
     * 以下数学推导总体分为两步：① 当所有绳段长度相等时，乘积最大。② 最优的绳段长度为 3。知道这个可以用动态规划。
     */
    public int cuttingRope(int n) {
        int ans = Integer.MIN_VALUE;
        for (int i = 2; i < n + 1; i++) {
            ans = Math.max(cuttingRope(n, i),ans);
        }
        return (int) (ans % (Math.pow(10,9) + 7));
    }
    public int cuttingRope(int n, int m) {
        int avg = n / m;
        int rest = n - avg * m;
        return (int) (Math.pow(avg,m - rest) * Math.pow(avg + 1,rest));
    }
    /**
     * 57 - 02
     */
    public int[][] findContinuousSequence(int target) {
        int right = 1;
        int left = 1;
        ArrayList<int[]> result = new ArrayList<>();
        while (left <= target/2){
            int cur = (right + left) * (right - left + 1) / 2;
            if (cur < target){
                right++;
            } else if (cur > target){
                left++;
            } else {
                int[] ans = new int[right - left + 1];
                for (int i = left; i < right + 1; i++) {
                    ans[i - left] = i;
                }
                result.add(ans);
                left++;
            }
        }
//        System.out.println(result);
        return result.toArray(new int[result.size()][]);
    }


    public int[][] findContinuousSequence1(int target) {
        int left = 1; // 滑动窗口的左边界
        int right = 1; // 滑动窗口的右边界
        int sum = 0; // 滑动窗口中数字的和
        List<int[]> res = new ArrayList<>();

        while (left <= target / 2) {
            if (sum < target) {
                // 右边界向右移动
                sum += right;
                right++;
            } else if (sum > target) {
                // 左边界向右移动
                sum -= left;
                left++;
            } else {
                // 记录结果
                int[] arr = new int[right-left];
                for (int k = left; k < right; k++) {
                    arr[k-left] = k;
                }
                res.add(arr);
                // 左边界向右移动
                sum -= left;
                left++;
            }
        }
        System.out.println(res);
        return res.toArray(new int[res.size()][]);
    }
    /**
     * 45
     */

//    public String minNumber(int[] nums) {
//        // 问题在于 3 30 9 类型的
//        ArrayList<String> stringArrayList = new ArrayList<>();
//        for (int num : nums){
//            stringArrayList.add(String.valueOf(num));
//        }
//        stringArrayList.sort(String::compareTo);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringArrayList.forEach(stringBuilder::append);
//        return stringBuilder.toString();
//    }

    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }
    /**
     * 46
     */
    public int translateNum(int num) {
        // 存储结果
        ArrayList<String> strings = new ArrayList<>();
        String s = String.valueOf(num);
        // 临时结果
        String cur = "";
        translateNumHelper(s,0,strings,cur);
        System.out.println(strings);
        return strings.size();
    }
    public void translateNumHelper(String num,int index,List<String> stringList, String cur) {
        if (index == num.length()){
            stringList.add(cur);
        } else if (index > num.length()){
            return;
        } else {
            // 两位数和一位数
            String tmp = cur;
            // 一位数需要加上的
            cur += num.substring(index,index + 1);
            translateNumHelper(num,index + 1,stringList,cur);
            cur = tmp;
            // 两位数需要加上的
            // 索引越界
            if (index + 2 > num.length()){
                return;
            }
            // 大于转码范围
            if (Integer.parseInt(num.substring(index,index + 2)) > 25 || Integer.parseInt(num.substring(index,index + 1)) == 0){
                return;
            } else {
                cur += num.substring(index,index + 2);
                translateNumHelper(num,index + 2,stringList,cur);
                cur = tmp;
            }
        }
    }
    /**
     * 57 双指针
     */
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while(i < j) {
            int s = nums[i] + nums[j];
            if(s < target) i++;
            else if(s > target) j--;
            else return new int[] { nums[i], nums[j] };
        }
        return new int[0];
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


//Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    /**
     * 47
     */
    public int maxValue(int[][] grid) {
        int colSize = grid[0].length;
        int rowSize = grid.length;
        int[][] dp = new int[rowSize][colSize];
        for (int i = 0;i < rowSize;i++) {
            for (int j = 0; j < colSize; j++) {
                if (i == 0 && j == 0){
                    dp[0][0] = grid[0][0];
                } else if (j == 0){
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i == 0){
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else{
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[rowSize - 1][colSize - 1];
    }

    /**
     * 58 - 01
     */
    public String reverseWords(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
            while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }
}