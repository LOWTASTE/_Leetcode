import java.util.*;

public class DP {
    // 接雨水

    /**
     * 思考一 ： 从低向上遍历 时间复杂度O(mn) : m极大时，会裂开来。
     */

    /**
     * 解法一 ：动态规划。
     * 正向遍历数组 height 得到数组 leftMax 的每个元素值，反向遍历数组 height 得到数组 rightMax 的每个元素值。
     * 在得到数组 leftMax 和 rightMax 的每个元素值之后，对于 0≤i<n，下标 i 处能接的雨水量等于 min(leftMax[i],rightMax[i])−height[i]。
     * 遍历每个下标位置即可得到能接的雨水总量。
     *
     * 方法问题 ：智商
     */

    public static int trap1(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        // 正向遍历
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 反向遍历
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 解法二 ：双指针。
     */
    public static int trap2(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    /**
     * 解法三 ：单调栈。
     */

    public static int trap3(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }


    // 最大子序和

    /**
     * 解法一 ：动态规划
     */

    public static int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }


    // 爬楼梯

    /**
     * 动态规划
     */

    public static int climbStairs(int n) {
        if (n < 3){
            return n;
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i < 3 ; i++){
            dp[i] = i;
        }
        for (int i = 3; i < n + 1;i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    // 使用最小花费爬楼梯

    /**
     * 动态规划
     */

    public static int minCostClimbingStairs(int[] cost) {
        int dp[] = new int[cost.length];
        for (int i = 0;i < dp.length;i++){
            if (i <= 1){
                dp[i] = cost[i];
            } else {
                dp[i] = Math.min(dp[i - 1],dp[i - 2]) + cost[i];
            }
        }
        return Math.min(dp[dp.length - 1],dp[dp.length - 2]);
    }


    // 打家劫舍

    /**
     * 动态规划
     */

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            if (i == 0){
                dp[0] = nums[0];
            }
            else if (i == 1){
                dp[1] = Math.max(nums[0],nums[1]);
            }
            else {
                dp[i] = Math.max(dp[i - 1],dp[i - 2] + nums[i]);
            }
        }
        return dp[dp.length - 1];
    }


    // 打家劫舍ii

    public static int rob2(int[] nums){
        if (nums.length == 1){
            return nums[0];
        }
        return Math.max(rob(nums,0, nums.length - 2), rob(nums,1 ,nums.length - 1));
    }

    public static int rob(int[] nums, int start ,int end) {
        int[] dp = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            if (i == start){
                dp[0] = nums[i];
            }
            else if (i == start + 1){
                dp[1] = Math.max(nums[start],nums[start + 1]);
            }
            else {
                dp[i - start] = Math.max(dp[i - 1 - start],dp[i - 2 - start] + nums[i]);
            }
        }
        return dp[dp.length - 1];
    }


    // TODO 出界的路径数

    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0){
            return 0;
        }
        if (startColumn == n || startColumn == -1 || startRow == m || startRow == -1){
            return 1;
        }
        // 四面移动
        return  findPaths(m, n, maxMove - 1,startRow - 1,startColumn) +
                findPaths(m, n, maxMove - 1,startRow,startColumn + 1) +
                findPaths(m, n, maxMove - 1, startRow + 1, startColumn) +
                findPaths(m, n, maxMove - 1, startRow, startColumn - 1);
    }


    // 跳跃游戏

    /**
     * 解法一 ： 贪心
     */
    public static boolean canJump(int[] nums) {
        int maxPos = 0;
        for (int i = 0; i < nums.length; i++){
            if (i <= maxPos){
                maxPos = Math.max(maxPos,nums[i] + i);
            } else {
                return false;
            }
        }
        return maxPos > nums.length - 1;
    }

    // 跳跃游戏ii

    public static int jump(int[] nums) {
        if (nums.length <= 0){
            System.out.println("error");
            return -1;
        }

        // 到每个点的最小跳数
        int[] dp = new int[nums.length];

        // 每个点最大能到的点数
        int[] max = new int[nums.length];

        // 一个记录当前能到的最大值
        int maxPos = 0;

        // 初始化
        dp[0] = 0;
        for (int i = 1;i < nums.length; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        // 可以之后计算提升效率
//        for (int i = 0;i < max.length; i++){
//            max[i] = i + nums[i];
//        }
        for (int i = 0; i < dp.length; i++) {
            // 判断是否能到当前点
            if (maxPos >= i){
                max[i] = i + nums[i];
                maxPos = Math.max(maxPos, max[i]);
                // 能到当前的节点
                for (int j = 0; j < i; j++){
                    if (max[j] >= i){
                        dp[i] = Math.min(dp[j] + 1, dp[i]);
                    }
                }
            } else {
                // 到不了返回 -1
                return -1;
            }
        }
        return dp[nums.length - 1];
    }

    // 乘积最大子数组

    public static int maxProduct(int[] nums) {
        // 由于负负得正 所以不能简单的记录 第i位 前的最大序列
        // 记录最大最小值
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        for (int i = 1;i < nums.length;i++){
            max[i] = Math.max(Math.max(max[i - 1] * nums[i],min[i - 1] * nums[i]),nums[i]);
            min[i] = Math.min(Math.min(max[i - 1] * nums[i],min[i - 1] * nums[i]),nums[i]);
        }
        int ans = max[0];
        for (int i = 1; i < max.length; ++i) {
            ans = Math.max(ans, max[i]);
        }
        return ans;
    }

    public static int maxProduct1(int[] nums) {
        // 由于负负得正 所以不能简单的记录 第i位 前的最大序列
        // 记录最大最小值
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }


/*    // 乘积为正数的最长子数组长度

    public int getMaxLen(int[] nums) {
        int ans = -1;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {

        }
        return ans;
    }*/

//    最佳观光组合

    public static int maxScoreSightseeingPair(int[] values) {
        int ans = 0, mx = values[0] + 0;
        for (int j = 1; j < values.length; ++j) {
            ans = Math.max(ans, mx + values[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }


//     买卖股票的最佳时间ii


    /**
     * 动态规划 dp 里除了结果之外，还存在状态。
     */

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        // 无股票
        dp[0][0] = 0;
        // 有股票
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            // 要么前一天也没股票，有股票今天卖掉了。
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 要么前一天没股票买进，要么前一天有股票今天不买。
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 没股票的最大值。
        return dp[n - 1][0];
    }


    /**
     * 贪心算法
     */

    public static int maxProfit2(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }


//     买卖股票的最佳时间含冷冻期

    /**
     * 动态规划 dp 里除了结果之外，还存在状态。
     */
    public int maxProfit3(int[] prices) {
        int[][] dp = new int[prices.length][3];
        // 初始化
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        for (int i = 1; i < prices.length; i++) {
            // 前一天没股票不冻结
            dp[i][0] = Math.max(dp[i - 1][0],dp[i - 1][2]);
            // 前一天有股票
            dp[i][1] = Math.max(dp[i - 1][1],dp[i - 1][0] - prices[i]);
            // 前一天是没有股票的，而且卖掉了
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        return Math.max(dp[prices.length - 1][0],dp[prices.length - 1][2]);
    }



//     买卖股票的最佳时间含手续费
    public int maxProfit4(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        // 初始化 0 持有 1 不持有
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0],dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1],dp[i - 1][0] - prices[i]);
        }
        return dp[dp.length - 1][0];
    }


//      成绩为正数的最长子数组

    public static int getMaxLen(int[] nums) {
        int length = nums.length;
        int positive = nums[0] > 0 ? 1 : 0;
        int negative = nums[0] < 0 ? 1 : 0;
        int maxLength = positive;
        for (int i = 1; i < length; i++) {
            if (nums[i] > 0) {
                positive++;
                negative = negative > 0 ? negative + 1 : 0;
            } else if (nums[i] < 0) {
                int newPositive = negative > 0 ? negative + 1 : 0;
                int newNegative = positive + 1;
                positive = newPositive;
                negative = newNegative;
            } else {
                positive = 0;
                negative = 0;
            }
            maxLength = Math.max(maxLength, positive);
        }
        return maxLength;
    }



    // 最大正方形

    /**
     * 动态规划
     */
    public static int maximalSquare1(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    public static int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    public static int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }



    // 杨辉三角
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans =new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i){
                    list.add(1);
                } else {
                    List<Integer> tmp = ans.get(i - 1);
                    list.add(tmp.get(j - 1) + tmp.get(j));
                }
            }
            ans.add(list);
        }
        return ans;
    }
    // 杨辉三角ii
    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> lists = generate(rowIndex);
        return lists.get(lists.size() - 1);
    }

    // N皇后
    /**
     * 方法一 : 回溯方法
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }
    public static void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    // 救生艇
    public static int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int prev = 0;
        int last = people.length - 1;
        while (prev <= last){
            if (people[last] + people[prev] > limit){
                if (people[last] <= limit){
                    last--;
                    ans++;
                }
                else {
                    return -1;
                }
            } else if (people[last] + people[prev] <= limit){
                last--;
                prev++;
                ans++;
            } else if (prev == last){
                ans++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {

//        int[] res = {0,1,0,2,1,0,1,3,2,1,2,1};
//        System.out.println(trap3(res));
//        int[] cost = {10, 15, 20};
//        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
//        System.out.println(minCostClimbingStairs(cost));

//        int[] nums ={0};
//        System.out.println(rob2(nums));

//        int m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0;
//        System.out.println(findPaths(m, n, maxMove, startRow, startColumn));

//        int[] num = {2,3,1,1,4};
//        System.out.println(canJump(num));

//        int[] num = {2,3,0,1,4};
//        System.out.println(jump(num));
//        System.out.println(maxProduct(num) == maxProduct1(num));

//        System.out.println(nthUglyNumber(12));

//        System.out.println(getRow(5));

//        System.out.println(solveNQueens(5));

        int[] people = {3,5,3,4};
        System.out.println(numRescueBoats(people, 5));
    }

}
