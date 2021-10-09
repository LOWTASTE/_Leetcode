package leetcode;

import java.util.*;

public class Leetcode_2021_9 {



    public static void main(String[] args) {
//        int[] temperatures = {73,74,75,71,69,72,76,73};
//        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));

//        char[] strings = {'A','A','A','A','A','A','B','C','D','E','F','G'};
//        int n = 2;
//        System.out.println(leastInterval(strings, n));

//        int[] nums = {1,1,1,1,1};
//        System.out.println(findTargetSumWays(nums, 3));

        Solution_9 solution_9 = new Solution_9();
        int[] row = {0,1,2,5,6,4,7,8,9,3,10,11};
        System.out.println(solution_9.minSwapsCouples(row));
    }
}


class Solution_9 {
    // 环形链表2
    public static ListNode detectCycle(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<ListNode>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }

    // 每日温度
    public static int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    // 合并二叉树
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode merge = new TreeNode();
        if (root1 != null && root2 !=null){
            merge = new TreeNode(root1.val + root2.val);
            merge.left = mergeTrees(root1.left,root2.left);
            merge.right = mergeTrees(root1.right,root2.right);
        } else if (root1 == null && root2 != null){
            return root2;
        } else if (root1 != null && root2 == null){
            return root1;
        } else {
            return null;
        }
        return merge;
    }

    // 任务调度器
    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }


    static int count = 0;

    public static int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public static void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    public int minSwapsCouples(int[] row) {
        int len = row.length;
        // len / 2 组
        int N = len / 2;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < len; i += 2) {
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        unionFind.printParent();
        return N - unionFind.getCount();
    }

    private class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
           return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            count--;
        }
        public void printParent(){
            for (int j : parent) {
                System.out.println(j);
            }
        }
    }

    /**
     * 给你一个字符串 S，找出所有长度为 K 且包含重复字符的子串，请你返回全部满足要求的子串的数目。
     */
    public static int numKLenSubstrRepeats (String s, int k) {
        // write code here
        int ans = 0;
        for (int i = 0; i < s.length() - k + 1; i++) {
//            System.out.println(s.substring(i, i + k));
            String str = s.substring(i, i + k);
            HashSet<Character> hashSet = new HashSet<>();
            char[] aChar = str.toCharArray();
            for (char c :
                    aChar) {
                hashSet.add(c);
            }
            if (hashSet.size() == str.length()){
                continue;
            }
            ans++;
        }
        return ans;
    }

    /**
     * TODO:IMPORTANT !
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * @param nums int整型一维数组
     * @return int整型二维数组
     */
    //这个方法用于获取下一个字典序的数组
    private static int[] getNext(int[] nums){
        int n = nums.length;
        int head = -1,tail = -1;
        for(int i = n - 1;i > 0;i--){
            if(nums[i] > nums[i - 1]){
                head = i - 1;
                break;
            }
        }
        if(head == -1) return null;
        for(int i = n - 1;i > head;i--){
            if(nums[i] > nums[head]){
                tail = i;
                break;
            }
        }
        int swap = nums[head];
        nums[head] = nums[tail];
        nums[tail] = swap;
        Arrays.sort(nums,head + 1,n);
        return nums;
    }

    public static int[][] UniquePerm (int[] nums) {
        // write code here
        int n = nums.length;
        List<int[]>res = new ArrayList<>();
        Arrays.sort(nums);
        int []newN = new int[nums.length];
        System.arraycopy(nums, 0, newN, 0, newN.length);
        res.add(newN);
        while(true){
            nums = getNext(nums);
            if(nums == null) break;
            int[] ins = new int[nums.length];
            System.arraycopy(nums, 0, ins, 0, nums.length);
            res.add(ins);
        }
        int [][]result = new int[res.size()][n];
        for(int i = 0;i < res.size();i++){
            result[i] = res.get(i);
        }
        return result;
    }

    /**
     * TODO: 前驱节点技巧
     */
    //将两个降序链表合并为一个新的 降序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    public ListNode MergeTwoLists (ListNode list1, ListNode list2) {
        // write code here
        ListNode head = new ListNode(0);
        ListNode before = head;
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                before.next = list1;
                list1 = list1.next;
            }else{
                before.next = list2;
                list2 = list2.next;
            }
            before = before.next;
        }
        if(list1 != null) before.next = list1;
        if(list2 != null) before.next = list2;
        return head.next;
    }

    public static void main(String[] args) {
//        int[] res = {1,3,5,7};
//        System.out.println(Arrays.deepToString(UniquePerm(res)));
//        long aLong = 65L;
//        System.out.println(Long.toBinaryString(aLong));
//        PriorityQueue<PQTest> pqTests = new PriorityQueue<>();
//        pqTests.add(new PQTest(5,9));




















//        pqTests.add(new PQTest(9,5));
//        System.out.println(pqTests.poll());

    }

    /**
     * 实现Comparable<PQTest>与pqTests配合使用
     */
    static class PQTest implements Comparable<PQTest>{
        PQTest(int integer1,int integer2){
            this.integer1 = integer1;
            this.integer2 = integer2;
        }
        Integer integer1;
        Integer integer2;

        @Override
        public String toString() {
            return "PQTest{" +
                    "integer1=" + integer1 +
                    ", integer2=" + integer2 +
                    '}';
        }

        @Override
        public int compareTo(PQTest o) {
            if (this.integer2 > o.integer2){
                return -1;
            } else {
                return 1;
            }
        }
    }

}



