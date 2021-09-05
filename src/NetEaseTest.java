import java.util.*;

public class NetEaseTest {
      // 1. 100
//    public static void main(String[] args) {
//        int ans = 0;
//        Scanner scanner = new Scanner(System.in);
//        String nums = scanner.nextLine();
//        int target = scanner.nextInt();
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (String num : nums.split(" ")){
//            arrayList.add(Integer.parseInt(num));
//        }
//        for (int i = 0;i < arrayList.size();i++){
//            for (int j = i + 1; j < arrayList.size(); j++) {
//                if (arrayList.get(i) + arrayList.get(j) <= target){
//                    ans++;
//                }
//            }
//        }
//        System.out.println(ans);
//
//    }

      // 2. 100
//    public static void main(String[] args) {
//        // char 97 是 a 122 是 z
////        char a = 'a';
////        char z = 'z';
////        System.out.println( (int) z);
//        leetcode.Solution solution= new leetcode.Solution();
//        System.out.println(solution.findString(4));
//    }
//
//    public static class leetcode.Solution {
//        /**
//         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
//         *
//         * 返回Sn的第k位字符
//         * @param n int整型 Sn的n
//         * @param k int整型 需要返回的字符下标位
//         * @return char字符型
//         */
//
//        public char findKthBit (int n, int k) {
//            // write code here
//            // 返回 sn的k个 sn有计算方式 sn
//            // sn = s n-1 + ln + reverse(invert(s n -1))
//            return findString(n).charAt(k - 1);
//        }
//
//        public String findString(int n){
//            if (n == 1){
//                return "a";
//            }
//            String ans = findString(n - 1) + (char) (96 + n) +  revers(invert(findString(n - 1)));
//            return ans;
//        }
//
//        public String invert(String res){
//            ArrayList<Character> characters = new ArrayList<>();
//            int mid = ((int) 'z' + (int) 'a') / 2;
//            for (char c : res.toCharArray()){
//                if ((int) c < mid){
//                    c = (char) ((int) c + 2 * (mid - c) + 1);
//                } else {
//                    c = (char) ((int) c - 2 * (c - mid) + 1);
//                }
//                characters.add(c);
//            }
//            char[] chars = new char[characters.size()];
//            for (int i = 0; i < chars.length; i++) {
//                chars[i] = characters.get(i);
//            }
//            return String.valueOf(chars);
//        }
//
//        public String revers(String res){
//            int len = res.length();
//            char[] tmp = res.toCharArray();
//            char[] ans = new char[len];
//            for (int i = 0; i < tmp.length; i++) {
//                ans[len - i -1] = tmp[i];
//            }
//            return String.valueOf(ans);
//        }
//
//    }


      // 3. 60
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String nums = scanner.nextLine();
//        // 只给一张一开始
//        int given = 1;
//        int ans = 1;
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (String num : nums.split(" ")){
//            arrayList.add(Integer.parseInt(num));
//        }
//        for (int i = 1; i < arrayList.size();i++) {
//            if (arrayList.get(i) > arrayList.get(i - 1)){
//                given++;
//            } else if (arrayList.get(i) < arrayList.get(i - 1)){
//                given--;
//            }
//            ans += given;
//        }
//        System.out.println(ans);
//    }

      // 3. 70
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String nums = scanner.nextLine();
//        // 只给一张一开始
//        int given = 1;
//        int ans = 1;
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (String num : nums.split(" ")){
//            arrayList.add(Integer.parseInt(num));
//        }
//        for (int i = 1; i < arrayList.size();i++) {
//            if (arrayList.get(i) > arrayList.get(i - 1)){
//                given++;
//            } else if (arrayList.get(i) <= arrayList.get(i - 1)){
//                given--;
//            }
//            ans += given;
//        }
//        System.out.println(ans);
//    }

      // 3. 70
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String nums = scanner.nextLine();
//        // 只给一张一开始
//        int given = 1;
//        int ans = 1;
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (String num : nums.split(" ")){
//            arrayList.add(Integer.parseInt(num));
//        }
//        int dp[] = new int[arrayList.size()];
//        dp[0] = 1;
//        for (int i = 1; i < arrayList.size();i++) {
//            if (arrayList.get(i) > arrayList.get(i - 1)){
//                given++;
//            } else if (arrayList.get(i) <= arrayList.get(i - 1)){
//                given--;
//            }
//
//            ans += given;
//        }
//        System.out.println(ans);
//    }


//    //2. 80% 两次遍历，类似接雨水。
//    public int candy(int[] ratings) {
//        int n = ratings.length;
//        int[] left = new int[n];
//        for (int i = 0; i < n; i++) {
//            if (i > 0 && ratings[i] > ratings[i - 1]) {
//                left[i] = left[i - 1] + 1;
//            } else {
//                // i = 0 || ratings[i] <= ratings[i - 1]
//                left[i] = 1;
//            }
//        }
//
//        int right = 0, ret = 0;
//        for (int i = n - 1; i >= 0; i--) {
//            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
//                right++;
//            } else {
//                // i = 0 || ratings[i] <= ratings[i - 1]
//                right = 1;
//            }
//            ret += Math.max(left[i], right);
//        }
//        return ret;
//    }




    // TODO:
    public static int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // 非法参数处理
        if (validateInputParams(k, m, n)) {
            return -1;
        }
        // 特殊场景处理
        if (m == 1 && n == 1) {
            return 0;
        }

        // BFS对于当前点的下一个点选择，如果grid[i][j]=0则有效入队列 visited[i][j]记录消除障碍次数
        // 若grid[i][j]=1则看是否还有消除障碍机会，若没有 此点丢弃
        // 若有消除障碍机会， （上一个点剩余消除障碍机会 - 1）比visited[i][j] 值比大 此点入队， 小则丢弃（贪心）
        // 例子：k=1, 坐标(0,2)可以为消除(0,1)障碍过来的 visited[0][2] = 0，搜索层级为2
        // 也可能为不消除任何障碍过来的 visited[0][2] = 1，层级为6，更新visited[0][2] = 1并入队
        // 因为到后面还需要消除障碍才能到达目标，先消除障碍走到visited[0][2] = 0的肯定到不了目标...
        // 0 1 0 0 0 1 0 0
        // 0 1 0 1 0 1 0 1
        // 0 0 0 1 0 0 1 0

        // 二维标记数组初始状态为-1，值记录剩余消除障碍的次数，剩余次数越多 越有价值（此处贪心，记录局部最优）
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = -1;
            }
        }
        // 初始步数为0，m=n=1的特殊场景已处理
        int minSteps = 0;

        // 初始位置标记已访问,值记录剩余消除障碍物次数  越多越好
        // 1. 对于其他路径到达此点且剩余消除障碍物次数小于等于当前值 —— 剪枝
        // 2. 对于其他路径到达此点且剩余消除障碍物次数大于当前值 —— 取代并入队
        visited[0][0] = k;
        Queue<Point> queue = new LinkedList<>();
        Point startPoint = new Point(0, 0, 0);
        queue.offer(startPoint);

        // 定义四个方向移动坐标
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        // BFS搜索-队列遍历
        while (!queue.isEmpty()) {
            minSteps++;
            // BFS搜索-遍历相同层级下所有节点
            // 当前队列长度一定要在循环外部定义，循环内部有插入对列操作
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();
                int x = current.x;
                int y = current.y;
                int oneCount = current.oneCount;

                // 对当前节点四个方向进行识别处理
                for (int j = 0; j < 4; j++) {
                    int xNew = x + dx[j];
                    int yNew = y + dy[j];
                    // 越界判断
                    if (xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) {
                        continue;
                    }
                    // 搜索到目标节点直接返回结果，按层级就是最短步数
                    if (xNew == m - 1 && yNew == n - 1) {
                        return minSteps;
                    }
                    // 穿越障碍次数已满
                    if (grid[xNew][yNew] == 1 && oneCount >= k) {
                        continue;
                    }
                    int oneCountNew = grid[xNew][yNew] == 1 ? oneCount + 1 : oneCount;
                    // 剪枝 - 节点已被访问过，且当前visited记录的剩余障碍物消除次数 >= 当前搜索节点层级的剩余消除次数
                    if (visited[xNew][yNew] != -1 && visited[xNew][yNew] >= k - oneCountNew) {
                        continue;
                    } else {
                        // 否则，贪心将最优值更新，并将该层级节点入队
                        visited[xNew][yNew] = k - oneCountNew;
                    }
                    queue.offer(new Point(xNew, yNew, oneCountNew));
                }
            }
        }
        // BFS没搜索到目标，返回-1
        return -1;
    }

    private static boolean validateInputParams(int k, int m, int n) {
        return m > 40 || m < 1 || n > 40 || n < 1 || k < 1 || k > m * n;
    }

    static class Point {
        int x;
        int y;
        int oneCount;

        public Point(int x, int y, int oneCount) {
            this.x = x;
            this.y = y;
            this.oneCount = oneCount;
        }
    }




//    public static class leetcode.Solution {
//        /**
//         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
//         * 水 0  2
//         * 地 1  1
//         * 障碍 2  不能通过
//         * 计算最小航行费用
//         * @param input int整型二维数组 二维网格
//         * @return int整型
//         */
//        public static int minSailCost (int[][] input) {
//            // write code here
//
//
//            return -1;
//        }
//        public static int minCost (int[][] input, int posX, int posY) {
//            // write code here
//            if (posX == input.length && posY == input[0].length){
//                return
//            }
//            return -1;
//        }
//
//    }

    public static void main(String[] args) {

    }
}
