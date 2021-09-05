package leetcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

//class leetcode.TreeNode {
//    int val = 0;
//    leetcode.TreeNode left = null;
//    leetcode.TreeNode right = null;
//}

public class solution_NC {

//判断给定的链表中是否有环。如果有环则返回true，否则返回false。
    //快慢指针方法
    public boolean hasCycle(ListNode head) {
        if(head==null)
            return false;
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow)
                return true;
        }
        return false;
    }
    //哈希表方法

//给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
//快慢指针方法
    public ListNode removeNthFromEnd (ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        for(int i = 0; i < n; i++)
            first = first.next;
        //如果n的值等于链表的长度,直接返回去掉头结点的链表
        if(first == null)
            return head.next;
        while(first.next != null)   //同时移动两个指针
        {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }

//把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
//二分方法
    public static int GetUglyNumber_Solution(int index) {
        if(index < 7) return index;
        ArrayList<Integer> Ugly = new ArrayList<>();
//        HashSet<Integer> Ugly = new HashSet<>();
        int ans = 1;
        Ugly.add(1);
        ArrayList<Integer> Ugly_2 = new ArrayList<>();
        ArrayList<Integer> Ugly_3 = new ArrayList<>();
        ArrayList<Integer> Ugly_5 = new ArrayList<>();
        while (index > Ugly.size()){
            int pos = Ugly.size();
            Ugly_2.add(Ugly.get(pos - 1) * 2);
            Ugly_3.add(Ugly.get(pos - 1) * 3);
            Ugly_5.add(Ugly.get(pos - 1) * 5);
            int min = Math.min(Math.min(Ugly_2.get(0),Ugly_3.get(0)),Ugly_5.get(0));
            Ugly.add(min);
            if (Ugly_2.get(0)==min){
                Ugly_2.remove(0);
            }
            if (Ugly_3.get(0)==min){
                Ugly_3.remove(0);
            }
            if (Ugly_5.get(0)==min){
                Ugly_5.remove(0);
            }
        }
        return Ugly.get(index - 1);
    }

//给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。如果K>数组的长度，那么返回一个空的数组
//_0方法为快排；
    public static ArrayList<Integer> GetLeastNumbers_Solution_0(int [] input, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(k>input.length)
            return ans;

        quickSort(input,0,input.length - 1,false);
        for (int i = 0;i < k; i++){
            System.out.println(input[i]);
            ans.add(input[i]);
        }
        return ans;
    }

//    public static void quickSort(int[] arr,int low,int high){
//        if (low < high){
//            int mid = partition(arr,low,high);
//            quickSort(arr,low,mid - 1);
//            quickSort(arr,mid + 1,high);
//        }
//    }
//
//    public static int partition(int[] arr,int low, int high){
//        if(low == high)
//            return 0;
//        int pivot = arr[low];
//        int pos = low;
//        for (int i = low + 1;i < high;i++){
//            if (pivot > arr [i]){
//                ++pos;
//                int temp = arr[pos];
//                arr[pos] = arr[i];
//                arr[i] = temp;
//            }
//        }
//        int temp = arr[pos];
//        arr[pos] = arr[low];
//        arr[low] = temp;
//        return pos;
//    }

    static void quickSort(int[] arr, int low, int high, boolean reserve)
    {
        if (low < high)
        {
            int mid = partition(arr, low, high, reserve);
            quickSort(arr, low, mid - 1, reserve);  // Before pi
            quickSort(arr, mid + 1, high, reserve); // After pi
        }
    }
    static int partition(int[] arr, int low, int high, boolean reserve)
    {
        //以高位为轴
        /*int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;
                int temp  = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp  = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;*/

        //以低位为轴
        if (low == high)
            return 0;
        int pivot = arr[low];
        int pos = low;
        int temp;
        if (!reserve)
        {
            for (int i = low + 1;i <= high;i++){
                if (arr[i] < pivot){
                    pos++;
                    temp = arr[i];
                    arr[i] = arr[pos];
                    arr[pos] = temp;
                }
            }
            temp = arr[low];
            arr[low] = arr[pos];
            arr[pos] = temp;
            return pos;
        }
        else {
            for (int i = low + 1;i <= high;i++){
                if (arr[i] > pivot){
                    pos++;
                    temp = arr[i];
                    arr[i] = arr[pos];
                    arr[pos] = temp;
                }
            }
            temp = arr[low];
            arr[low] = arr[pos];
            arr[pos] = temp;
            return pos;
        }

    }

    public static ArrayList<Integer> GetLeastNumbers_Solution_1(int [] input, int k) {
        return null;
    }

//给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
//_0Java递归，首先声明一个成员变量供全局使用；其次写一个向下探索的递归方法，注意先从左边再从右边；每向下一层level+1
//这里很巧妙的是，对于二维List来说，将根视为第0层，树的深度正好等于一维List的个数。
//_1队列重要的性质
    //TODO 个人感悟递归思想一般使用多个函数
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> levelOrder_0 (TreeNode root) {
        // write code here
        if(root == null){
            return res;
        }
        count(root,0);
        return res;
    }

    public void count(TreeNode node, int level){
        if(level == res.size()){
            res.add(new ArrayList<Integer>());
        }

        ArrayList<Integer> list = res.get(level);
        list.add(node.val);

        if(node.left != null){
            count(node.left, level+1);
        }

        if(node.right != null){
            count(node.right, level+1);
        }

    }

    public ArrayList<ArrayList<Integer>> levelOrder_1 (TreeNode root) {
        // write code here
        if(root==null) return new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        stack.addLast(root);
        while(!stack.isEmpty()){
            int size = stack.size();
            ArrayList<Integer> list = new ArrayList<>();
            //开始size = 1；之后加入更多，直至全部取出。
            while(size-->0){
                TreeNode tmp = stack.pollFirst();
                list.add(tmp.val);
                if(tmp.left!=null)
                    stack.addLast(tmp.left);
                if(tmp.right!=null)
                    stack.addLast(tmp.right);
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }


//给你一个n（1<N<10e5），和一个长度为n的数组，在不同时选位置相邻的两个数的基础上，求该序列的最大子序列和（挑选出的子序列可以为空）。
    //TODO 动态规划；序列问题不妨动态构建.
    public long subsequence (int n, int[] array) {
        // write code here
        if(n==1){
            return array[0];
        }
        if(n==2){
            return Math.max(array[0],array[1]);
        }
        int[] dp = new int[n];
        dp[0] = array[0];
        dp[1] = array[1];

        for(int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + array[i]);
        }
        return dp[n-1];
    }

//实现函数 int sqrt(int x).
//计算并返回x的平方根（向下取整）
//二分的简单应用。
    public int sqrt (int x) {
        // write code here
        if (x <= 0)
            return x;
        long left = 1;
        long right = x;
        while(left < right) {
            long middle = (left + right) / 2;
            if (middle * middle <= x && (middle + 1) * (middle + 1) > x) {
                return (int)middle;
            } else if (middle * middle < x) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return (int) left;
    }

//依旧是动态规划，匹配问题的动态规划，有别于上文。
    public String LCS (String str1, String str2) {
        // write code here
        int[][] pos = new int[str1.length()][str2.length()];
        int max = 0,index = -1;
        for (int i = 0;i < str1.length();i++){
            for (int j = 0;j < str2.length();j++){
                if (str1.charAt(i) == str2.charAt(j)){
                    if (i == 0 || j == 0){
                        pos[i][j] = 1;
                    }
                    else {
                        pos[i][j] = pos[i - 1][j - 1] + 1;
                    }

                    if(max < pos[i][j]){
                        max = pos[i][j];
                        index = i;
                    }
                }
            }
        }
        return str1.substring(index - max - 1,index - 1);
    }

//求给定二叉树的最大深度，
//最大深度是指树的根结点到最远叶子结点的最长路径上结点的数量。
    //TODO dfs
    public int maxDepth (TreeNode root) {
        // write code here
        if (root == null){
            return 0;
        }
        int lDepth = maxDepth(root.left);
        int rDepth = maxDepth(root.right);
        return 1 + Math.max(lDepth,rDepth);
    }


//给你一个按 YYYY-MM-DD 格式表示日期的字符串date，请你计算并返回该日期是当年的第几天。
//通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
    public int dayOfYear(String date) {
        try {
            Calendar ca = Calendar.getInstance();
            ca.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            return ca.get(Calendar.DAY_OF_YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


//实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
//双指针与递归方法。
    int size;
    public int kthToLast_0(ListNode head, int k) {
        if (head == null)
            return 0;
        int value = kthToLast_0(head.next, k);
        if (++size == k)
            return head.val;
        return value;
    }

    public int kthToLast_1(ListNode head, int k) {
        //设置哑节点
        ListNode yummy = new ListNode(0);
        yummy.next = head;
        //初始化快慢指针在哑节点处
        ListNode slow = yummy, fast = yummy;
        //快指针先走k步
        for(int i = 0; i < k; i++){
            fast = fast.next;
        }
        //当快指针不为空时，快、慢指针同步向前走
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        //当快指针为空（指向链表末端即最后一节点的next域）时，慢指针所指节点即为所求
        return slow.val;
    }


//给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到 nums1中，使 nums1 成为一个有序数组。
//nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
    //空间复杂度高
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ans = new int[m+n];
        int cur_1 = 0 , cur_2 = 0;
        for (int i = 0;i < m + n;i++){
            if (cur_2 == n||cur_1 == m){
                if (cur_1 == m){
                    ans[i] = nums2[cur_2];
                    ++cur_2;
                }
                else {
                    ans[i] = nums1[cur_1];
                    ++cur_1;
                }
            }
            else {
                if (nums1[cur_1] < nums2[cur_2]){
                    ans[i] = nums1[cur_1];
                    ++cur_1;
                }
                else {
                    ans[i] = nums2[cur_2];
                    ++cur_2;
                }
            }
        }
        if (m + n >= 0) System.arraycopy(ans, 0, nums1, 0, m + n);
    }


//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
    //TODO:回溯用法
    List<List<Integer>> ans = new ArrayList<> ();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, 1, k, new ArrayList<Integer> ());
        return ans;
    }
    private void backtrack(int n, int index, int remain, List<Integer> candidate) {
        // 剪枝条件：当剩下来的数字少于还需要构造的数 说明已经不可能完成
        if (n - index + 1 - remain < 0) return;
        if (remain == 0) {
            ans.add(new ArrayList<> (candidate));
        }
        for (int i = index; i <= n; ++i) {
            // 选择该数
            candidate.add(i);
            // 选择的状态下 考虑后续的数
            backtrack(n, i + 1, remain - 1, candidate);
            // 撤销选择 继续枚举下一个数的情况 相当于这是不选择的情况
            candidate.remove(candidate.size() - 1);
        }
    }

//寻找旋转排序数组中的最小值
//
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }

//解压缩编码列表
//
    /*todo :
        // int[] 转 List<Integer>
        List<Integer> list = Arrays.stream(data).boxed().collect(Collectors.toList());
        // Arrays.stream(arr) 可以替换成IntStream.of(arr)。
        // 1.使用Arrays.stream将int[]转换成IntStream。
        // 2.使用IntStream中的boxed()装箱。将IntStream转换成Stream<Integer>。
        // 3.使用Stream的collect()，将Stream<T>转换成List<T>，因此正是List<Integer>。
        // int[] 转 Integer[]
        Integer[] integers = Arrays.stream(data).boxed().toArray(Integer[]::new);
        // 前两步同上，此时是Stream<Integer>。
        // 然后使用Stream的toArray，传入IntFunction<A[]> generator。
        // 这样就可以返回Integer数组。
        // 不然默认是Object[]。
        // List<Integer> 转 Integer[]
        Integer[] integers = list.toArray(new Integer[0]);
        //  调用toArray。传入参数T[] a。这种用法是目前推荐的。
        // List<String>转String[]也同理。
        // List<Integer> 转 int[]
        int[] arr = list.stream().mapToInt(Integer::valueOf).toArray();
        // 想要转换成int[]类型，就得先转成IntStream。
        // 这里就通过mapToInt()把Stream<Integer>调用Integer::valueOf来转成IntStream
        // 而IntStream中默认toArray()转成int[]。
        // Integer[] 转 int[]
        int[] arr = Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();
        // 思路同上。先将Integer[]转成Stream<Integer>，再转成IntStream。
        // Integer[] 转 List<Integer>
        List<Integer> list = Arrays.asList(integers);*/
    public int[] decompressRLElist(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if (i % 2 == 0){
                for (int j = 0;j < nums[i]; j++){
                    ans.add(nums[i + 1]);
                }
            }
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }


//找树左下角的值
    //TODO : _0 为 DFS.. _1 为 BFS 右子节点先入..
    int max = Integer.MIN_VALUE;
    int res_int;
    public int findBottomLeftValue_0(TreeNode root) {
        dfs(root, 0);
        return res_int;
    }
    public void dfs(TreeNode node, int depth){
        if(node != null){
            if(node.left == null && node.right == null){
                if(max < depth){
                    max = depth;
                    res_int = node.val;
                }
            }
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }
    public int findBottomLeftValue_1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            if (root.right != null) queue.offer(root.right);
            if (root.left != null) queue.offer(root.left);
        }
        return root.val;
    }


//检查两个字符串数组是否相等
//
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return Splicing(word1).equals(Splicing(word2));
    }
    public static String Splicing(String[] word) {
            StringBuilder sb = new StringBuilder();
            for (String str : word) {
                sb.append(str);
            }
            return sb.toString();
        }


//给你链表的头节点 head 和一个整数 k 。交换链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
//
    public ListNode swapNodes(ListNode head, int k) {
        if (k == 0)
            return head;
        else {
            int t;
            ListNode fk = head;
            ListNode bk = head;
            for (int i = 1; i < k; i++) {
                fk = fk.next;
            }
            ListNode tmp;
            tmp = fk;
            while(tmp.next!=null){
                tmp = tmp.next;
                bk = bk.next;
            }
            t = bk.val;
            bk.val = fk.val;
            fk.val = t;
        }
        return head;
    }
//给你两个整数数组 source 和 target ，长度都是 n 。还有一个数组 allowedSwaps ，其中每个 allowedSwaps[i] = [ai, bi] 表示你可以交换数组 source 中下标为 ai 和 bi（下标从 0 开始）的两个元素。
//相同长度的两个数组source 和 target 间的距离
//在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的最小距离 。
    static public int distance(int[] source, int[] target, int[][] allowedSwaps) {
        int distance = 0;
        for (int i = 0; i < target.length; i++) {
            distance += Math.abs(source[i] - target[i]);
        }
        System.out.println(distance);

        for (int i = 0; i < allowedSwaps.length; i++) {
            int pTmp = allowedSwaps[i][0];
            int bTmp = allowedSwaps[i][1];
            if ((Math.abs(source[pTmp]-target[pTmp]) + Math.abs(source[bTmp]-target[bTmp])) > (Math.abs(source[pTmp]-target[bTmp]) + Math.abs(source[bTmp] - target[pTmp]))) {
                int tmp = source[pTmp];
                source[pTmp] = source[bTmp];
                source[bTmp] = tmp;
                distance -= Math.abs((Math.abs(source[pTmp]-target[pTmp]) + Math.abs(source[bTmp]-target[bTmp])) - (Math.abs(source[pTmp]-target[bTmp]) + Math.abs(source[bTmp] - target[pTmp])));
                System.out.println(distance);
            }
        }
        return distance;
    }


//转罗马数字
//
    public String intToRoman(int num) {
        int[] values={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] reps={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        String res = "";
        for(int i=0; i<values.length; i++){
            while(num>=values[i]){
                num -= values[i];
                res += reps[i];
            }
        }
        return res;
    }

//给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
//请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
//返回分配方案中尽可能 最小 的 最大工作时间 。
    public int minimumTimeRequired(int[] jobs, int k) {
        int[] workers = new int[k];
        quickSort(jobs, 0,jobs.length - 1,true);
        for (int i = 0; i < jobs.length; i++) {
            workers[0] += jobs[i];
            quickSort(workers,0,workers.length - 1,false);
        }
        return workers[workers.length - 1];
    }



//两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
//给你一个整数数组 nums，请你计算并返回 nums 中任意两个数之间汉明距离的总和。
//TODO: 位运算+抑或 ：超时

//    public int totalHammingDistance(int[] nums) {
//        int ans = 0;
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int i1 = i + 1; i1 < nums.length; i1++) {
//                ans += totalOneInNum(nums[i]^nums[i1]);
//            }
//        }
//        return ans;
//    }
//
//    public int totalOneInNum(int num){
//        int res = 0;
//        int t = num;
//        while (t != 0){
//            if((t & 1)==1){
//                res++;
//            }
//            t=t>>>1;
//        }
//        return res;
//    }


//TODO: 技巧性较高：
/*
   思路是:
		比如有3个数(用二进制表示) (方向从右到左<-，分别为第1位、第2位...)
			第一个数 a: 1 0 0 1
			第二个数 b: 0 1 1 1
			第三个数 c: 0 0 1 1
		那么第一位上的汉明距离总和是0。
			为什么？ 因为a、b、c该位全是"1"，任意两个"1"的汉明距离是0，所以该位汉明距离总和是0。
		接下来看第二位，a的第二位是"0"，b、c的是"1"，此时该位的汉明距离就是2。
			为什么？ 由上面我们可以得到，该位的"0"有1个，"1"有2个，而任意一个"0"都可以和任意一个"1"组合，
					 一对组合可以产生的汉明距离为1，所以问题转换为了算多少对01组合，那么怎么计算呢？
					 答案就是该位"0"的个数乘以"1"的个数。
		接下来看第三位，可以看出，此时"0"有2个，"1"有1个，所以可以产生的汉明距离为2。
		接下来看第四位，可以看出，此时"0"有2个，"1"有1个，所以可以产生的汉明距离为2。
		于是: 总的汉明距离就是 0 + 2 + 2 + 2 = 6
*/
        public int totalHammingDistance(int[] nums) {
            int ans = 0, n = nums.length;
            for (int i = 0; i < 30; ++i) {
                int c = 0;
                for (int val : nums) {
                    c += (val >> i) & 1;
                }
                ans += c * (n - c);
            }
            return ans;
        }

    public static int minInsertions(String s) {
        String left = new String();
        String right = new String();
        for(int i = 0;i < s.length();i++){
            String tmp = s.substring(0,i) + s.substring(i + 1);
            left = s.substring(0,i);
            right = s.substring(i + 1);
        }
        return 0;
    }

//唯一字符第一次出现的位置
    //时间复杂度O(n^2)，因为indexOf方法复杂度为O(n)
    private static void printFirstLetter(String str){
        for (int i = 0; i < str.length(); i++) {
            //截去第i个字符
            String temp = str.substring(0, i) + str.substring(i + 1);
            //在剩余的字符串中搜索有没有与第i个字符相同的，没有的时候会返回-1
            int idx = temp.indexOf(str.charAt(i));
            //剩下的字符串中没有的，就是唯一的
            if (idx == -1) {
                String c = String.valueOf(str.charAt(i));
                System.out.println(c);
                break;
            }
        }
    }


//给定一个Excel表格中的列名称，返回其相应的列序号
    public static int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            ans += Math.pow(26,i) * (columnTitle.charAt(columnTitle.length() - i - 1) - 'A' + 1);
        }
        return ans;
    }

/*  TODO:
    一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
    骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
    有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
    为了尽快到达公主，骑士决定每次只向右或向下移动一步。*/

    public static int calculateMinimumHP(int[][] dungeon) {
        int life = 1;

        return life;
    }

/*    找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int answer = 1;
        int result = Integer.MIN_VALUE;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums){
            hashSet.add(num);
        }
        for (int pos : hashSet){
            int tmp = pos;
            answer = 1;
            while (hashSet.contains(--tmp)){
                answer++;
            }
            tmp = pos;
            while (hashSet.contains(++tmp)){
                answer++;
            }
            result = Math.max(answer,result);
        }
        return result;
    }*/

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int answer;
        int result = Integer.MIN_VALUE;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums){
            hashSet.add(num);
        }
        for (int pos : hashSet){
            if (!hashSet.contains(pos - 1)){
                answer = 0;
                int tmp = pos;
                while (hashSet.contains(tmp++)){
                    answer++;
                }
                result = Math.max(answer,result);
            }
        }
        return result;
    }

    /**
     * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true
     * 没考虑到节点重复的情况
     */
/*    public static boolean findTarget(leetcode.TreeNode root, int k) {
        boolean res = false;
        HashSet<Integer> hashSet = new HashSet<>();
        preOrderTraversal(root,hashSet);
        for (Integer treeVal : hashSet){
            hashSet.remove(treeVal);
            if (hashSet.contains(k - treeVal)){
                res = true;
            }
            hashSet.add(treeVal);
        }
        return res;
    }
    public static leetcode.TreeNode preOrderTraversal(leetcode.TreeNode root, HashSet<Integer> treeNodes){
        if (root == null){
            return null;
        }
        treeNodes.add(root.val);
        if (root.left != null){
            treeNodes.add(preOrderTraversal(root.left,treeNodes).val);
        }
        if (root.right != null){
            treeNodes.add(preOrderTraversal(root.right,treeNodes).val);
        }
        return root;
    }*/


    public boolean findTarget(TreeNode root, int k) {
        Set < Integer > set = new HashSet();
        return find(root, k, set);
    }
    public boolean find(TreeNode root, int k, Set < Integer > set) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }

    static public int[] deckRevealedIncreasing(int[] deck) {
        int[] ans = new int[deck.length];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int num : deck){
            arrayList.add(num);
        }

        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
//        System.out.println(arrayList);
//        int pos = 0;
        for (int i = arrayList.size();i >= 0;i--){
            ans[i] = arrayList.get(i);
        }

        return ans;

    }




    public static void main(String[] args) {
        int[] nums = {0,3,5,7,1,9,6,8,4,2};
        ListNode head = new ListNode(0);
        ListNode tmp = new ListNode(1);
        head.next = tmp;
        tmp.next = new ListNode(2);
        tmp = tmp.next;
        tmp.next = new ListNode(5);
        tmp = head;
        deckRevealedIncreasing(nums);
//        System.out.println(longestConsecutive(nums));
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BSTIterator {

    private ArrayList<Integer> arrayList;
    private int index = -1;

    public BSTIterator(TreeNode root) {
        index = 0;
        arrayList = new ArrayList<>();
        inOrderTraversal(root);
    }

    public int next() {
        return arrayList.get(index++);
    }

    public boolean hasNext() {
        if (index < arrayList.size()){
            return true;
        } else {
            return false;
        }
    }

    private void inOrderTraversal(TreeNode root){
        //子节点返回
        if (root == null) {
            return;
        } else {
            inOrderTraversal(root.left);
            arrayList.add(root.val);
            inOrderTraversal(root.right);
        }
    }
}

