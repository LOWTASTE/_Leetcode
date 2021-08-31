import java.util.*;

public class Leetcode_2021_8 {

    // 两个有序数组的中位数

    /**
     *
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int pos1 = 0;
        int pos2 = 0;
        int[] total = new int[nums1.length + nums2.length];
        int pos = 0;
        if (nums1.length == 0 || nums2.length == 0){
            if (nums1.length == 0 && nums2.length != 0){
                return nums2.length % 2 == 0 ? (nums2[nums2.length/2] + nums2[nums2.length/2 - 1])/2.0 : nums2[nums2.length/2];
            } else if (nums1.length != 0){
                return nums1.length % 2 == 0 ? (nums1[nums1.length/2] + nums1[nums1.length/2 - 1])/2.0 : nums1[nums1.length/2];
            }
        }

        while(pos1 < nums1.length || pos2 < nums2.length){
            if (pos1 == nums1.length){
                total[pos++] = nums2[pos2];
                ++pos2;
            } else if (pos2 == nums2.length){
                total[pos++] = nums1[pos1];
                ++pos1;
            } else {
                if (nums1[pos1] <= nums2[pos2]){
                    total[pos++] = nums1[pos1];
                    ++pos1;
                } else {
                    total[pos++] = nums2[pos2];
                    ++pos2;
                }
            }
        }
        int middle = total[(nums1.length + nums2.length) / 2];
        int middleBack = total[(nums1.length + nums2.length) / 2 - 1];
        return total.length % 2 == 0 ? (middle + middleBack)/2.0 : middle;
    }
    */

    /**
     * TODO HERE
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        // 奇数 偶数
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    // 括号生成

    /**
     * 解法一:暴力法 : 暴力的方式，


    public static List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public static void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            System.out.println(current);
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }
    // 判断是否有效
    public static boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
     */

    /**
    * 解法二:回溯法


    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public static void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            // 回溯点
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }    */

    /**
     * 解法三:递归法

    static ArrayList[] cache = new ArrayList[100];

    public static List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left: generate(c)) {
                    for (String right: generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

    public static List<String> generateParenthesis(int n) {
        return generate(n);
    }
     */


    // 等差数列划分I

    public static int numberOfArithmeticSlices(int[] nums) {
        int len =  nums.length;
        if (len < 3){
            return 0;
        }
        int d = nums[1] - nums[0];
        int tmp = 0;
        int ans = 0;
        for (int i = 2;i < len; i++){
            if (nums[i] - nums[i-1] == d){
                ++tmp;
            } else {
                d = nums[i] - nums[i-1];
                tmp = 0;
            }
            ans += tmp;
        }
        return ans;
    }


    // 数的平方等于两数乘积的方法数

    /**
     * 方法一：暴力解法 SB

    public static int numTriplets(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int ans = 0;
        for (int i =0; i < len1; i++){
            long target = (long) Math.pow((long) nums1[i],2);
            for (int j = 0; j < len2; j++){
                for (int k = j + 1;k < len2; k++){
                    if ((long) nums2[k] * nums2[j] == target){
                        ++ans;
                    }
                }
            }
        }

        for (int i = 0; i < len2; i++){
            long target = (long) Math.pow((long) nums2[i],2);
            for (int j = 0; j < len1; j++){
                for (int k = j + 1;k < len1; k++){
                    if ((long) nums1[k] * nums1[j] == target){
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
    */

    /**
     * 方法二：hash表存值 时间复杂度O(mn)
     */
    public static int numTriplets(int[] nums1, int[] nums2){
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map1.getOrDefault(num, 0) + 1;
            map1.put(num, count);
        }
        for (int num : nums2) {
            int count = map2.getOrDefault(num, 0) + 1;
            map2.put(num, count);
        }
        return getTriplets(map1, map2) + getTriplets(map2, map1);
    }
    public static int getTriplets(Map<Integer,Integer> map1,Map<Integer,Integer> map2){
        int ans = 0;
        int key3 = 0;
        Set<Integer> set1 = map1.keySet();
        Set<Integer> set2 = map2.keySet();
        for (Integer key1 : set1){
            int count1 = map1.get(key1);
            long target = (long) key1 * key1;
            for (Integer key2 : set2){
                int count2 = map2.get(key2);
                if (target % key2 == 0 && target / key2 <= Integer.MAX_VALUE){
                    key3 = (int) (target / key2);
                    if (key3 == key2){
                        ans += count1 * count2 * (count2 - 1) / 2;
                    }
                    else {
                        // key2 < key3 保证1次 不重复
                        if (key2 < key3 && set2.contains(key3)){
                            int count3 = map2.get(key3);
                            ans += count1 * count2 * count3;
                        }
                    }
                }
            }
        }
        return ans;
    }


    // 最长回文子序列

    /**
     * DP 规划 ：
     * f[i][j] 记录字符串 s 的下标范围 [i,j] 内的最长回文子序列的长度。
     * 边界情况是，对任意 0 ≤ i < n，都有 f[i][i]=1。
     * 如果 s[i]=s[j]，则首先得到 s 的下标范围 [i+1,j−1] 内的最长回文子序列，然后在该子序列的首尾分别添加 s[i]s[i] 和 s[j]s[j]，
     * 即可得到 s的下标范围 [i,j] 内的最长回文子序列，因此 f[i][j]=f[i+1][j−1]+2；
     * 如果s[i]!=s[j]，则 s[i]s[i] 和 s[j]s[j] 不可能同时作为同一个回文子序列的首尾，因此f[i][j]=max(f[i+1][j],f[i][j−1])。
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }


    // 单词拆分

    /**
     * 错误一 ：简单贪心
     * 大问题 s = "aaaaaaa" wordDict = {"aaa","aaaa"}
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null){
            return false;
        }
        int len = s.length();
        int pre = 0;
        int next = 1;
        int tmp;
        for (; next <= len; next++){
            if (wordDict.contains(s.substring(pre,next))){
                // 已经把位置定死了
                pre = next;
            }
        }
        return pre == len;
    }

    /**
     * 解法一 ： 动态规划
     * i 控制全局
     * j 在 i 中拼接
     */
    public static boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    // 盛最多水的容器

    /**
     * 解法一 ：暴力SB: 时间复杂度O(n^2)
     */
    public static int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int len = height.length;
        for (int i = 0;i < len;i++){
            for (int j = i + 1;j < len;j++){
                max = Math.max(max,(j - i) * Math.min(height[i],height[j]));
            }
        }
        return max;
    }

    /**
     * 解法二 ：双指针 : 贪心思想
     */
    public static int maxArea1(int[] height) {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (right > left){
            max = Math.max(max,Math.min(height[left],height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }


    // 两两交换链表
    public ListNode swapPairs(ListNode head) {
        return null;
    }

    // 环形子数组的最大和

    /**
     * 联想到接雨水问题 ；maxRight 找到
     * ans = Math.max(ans, leftsum + maxright[i+2]);
     */

    public static int maxSubarraySumCircular(int[] A) {
        int N = A.length;
        int ans = A[0], cur = A[0];
        for (int i = 1; i < N; ++i) {
            cur = A[i] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }

        // ans is the answer for 1-interval subarrays.
        // Now, let's consider all 2-interval subarrays.
        // For each i, we want to know
        // the maximum of sum(A[j:]) with j >= i+2

        // rightsums[i] = A[i] + A[i+1] + ... + A[N-1]
        int[] rightsums = new int[N];
        rightsums[N-1] = A[N-1];
        for (int i = N-2; i >= 0; --i)
            rightsums[i] = rightsums[i+1] + A[i];

        // maxright[i] = max_{j >= i} rightsums[j]
        int[] maxright = new int[N];
        maxright[N-1] = A[N-1];
        for (int i = N-2; i >= 0; --i)
            maxright[i] = Math.max(maxright[i+1], rightsums[i]);

        int leftsum = 0;
        for (int i = 0; i < N-2; ++i) {
            leftsum += A[i];
            ans = Math.max(ans, leftsum + maxright[i+2]);
        }

        return ans;
    }

    // N皇后---回溯方法

    /**
     * 回溯思路
     */
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }


    // 下降路径最小和
    public static int minFallingPathSum(int[][] A) {
        int N = A.length;
        for (int r = N-2; r >= 0; --r) {
            for (int c = 0; c < N; ++c) {
                // best = min(A[r+1][c-1], A[r+1][c], A[r+1][c+1])
                int best = A[r+1][c];
                if (c > 0)
                    best = Math.min(best, A[r+1][c-1]);
                if (c+1 < N)
                    best = Math.min(best, A[r+1][c+1]);
                A[r][c] += best;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int x: A[0])
            ans = Math.min(ans, x);
        return ans;
    }

    // 三角路径下降路径最小和

    public static int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> answers = new ArrayList<>();
        int H = triangle.size();
        for (int i = H - 1; i >= 0 ; i--) {
            List<Integer> cur = new ArrayList<>();
            List<Integer> curList = triangle.get(i);
            for (int j = 0; j < curList.size(); j++) {
                if (i == H - 1){
                    cur.add(curList.get(j));
                } else {
                    cur.add(curList.get(j) + Math.min(answers.get(i + 1).get(j),answers.get(i + 1).get(j + 1)));
                }
            }
            answers.add(cur);
        }
        return answers.get(answers.size() - 1).get(0);
    }

    // 不同路径
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 不同路径
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0){
                    dp[i][j] = 1;
                } else if (obstacleGrid[i][j] != 0){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 最大正方形
    public static int maximalSquare(char[][] matrix) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0){
                    dp[i][j] = Integer.parseInt(String.valueOf(matrix[i][j]));
                } else if (matrix[i][j] == '0'){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]),dp[i - 1][j - 1]) + 1;
                }
                ans = Math.max(ans,dp[i][j]);
            }
        }
        return (int) Math.pow(ans,2);
    }





    public static void main(String[] args) {
//        int[] res = {1, 2 ,3 ,6 ,8 ,10};
//        int[] res1 = {1, 2 ,3 ,6 ,9 ,10};
//        System.out.println(numberOfArithmeticSlices(res));
//        System.out.println(combinationSum(res, 11));
//        System.out.println(numTriplets(res, res1));
        System.out.println(longestPalindromeSubseq("bbbabb"));

//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("aaaa");
//        wordDict.add("aaa");
//        System.out.println(wordBreak1("aaaaaaa", wordDict));

//        int[] height = {1,8,6,2,5,4,8,3,7};
//        System.out.println(maxArea1(height));
//        Scanner scanner = new Scanner(System.in);

        int[] A = {1,8,6,2,-500,4,8,3,7};
        char[][] B = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalSquare(B));
    }
}

class Test {


//    -128<= i <= 127 在缓冲中 == true；
//    public static void main(String[] args) {
//
//        Integer i = new Integer(128);
//        Integer i2 = 128;
//
//        System.out.println(i == i2);
//
//        Integer i3 = new Integer(127);
//        Integer i4 = 127;
//        System.out.println(i3 == i4);
//
//        Integer i5 = 128;
//        Integer i6 = 128;
//        System.out.println(i5 == i6);
//
//        Integer i7 = 127;
//        Integer i8 = 127;
//        System.out.println(i7 == i8);
//    }

//    public static void main(String[] args) {
//        String s1 = new String("a");
//        String s2 = new String("a");
//        String s3 = "a";
//        String s4 = "a";
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println(s3 == s4);
//    }
}