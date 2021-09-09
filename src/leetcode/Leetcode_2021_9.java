package leetcode;

import java.util.*;

public class Leetcode_2021_9 {

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
        Deque<Integer> stack = new LinkedList<Integer>();
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
            for (int i = 0; i < parent.length; i++) {
                System.out.println(parent[i]);
            }
        }
    }


}



