package leetcode;

import java.util.ArrayList;
import java.util.List;

public class BackTrack {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution{

    /**
     * 二叉树中和为某一值的路径
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        pathSumBackTrack(root, target, output, combine);
        return output;
    }

    public void pathSumBackTrack(TreeNode root, int target, List<List<Integer>> output, List<Integer> combine) {
        if (root == null){
            return;
        }
        combine.add(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target== 0){
            output.add(new ArrayList<>(combine));
        }
        pathSumBackTrack(root.left, target, output, combine);
        pathSumBackTrack(root.right, target, output, combine);
        // 回溯点
        combine.remove(combine.size() - 1);
    }


    /** 组合总数
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        combinationSumDfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void combinationSumDfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        // 防止越界
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过 : 这一步确保所有可能性
        combinationSumDfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            combinationSumDfs(candidates, target - candidates[idx], ans, combine, idx);
            // 回溯点
            combine.remove(combine.size() - 1);
        }
    }

    //组合总数2
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        combinationSumDfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void combinationSum2Dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        // 防止越界
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过 : 这一步确保所有可能性
        combinationSumDfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            combinationSumDfs(candidates, target - candidates[idx], ans, combine, idx + 1);
            // 回溯点
            combine.remove(combine.size() - 1);
        }
    }



    /**
     * 组合总数 3
     * 找出所有相加之和为 n 的 k 个数的组合。
     * 每种组合中不存在重复的数字。1-9。
     */
    public List<List<Integer>> combinationSum(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            list.add(i);
        }
        combinationSum3Dfs(k, n, ans, combine,list);
        return ans;
    }

    public void combinationSum3Dfs(int k, int n, List<List<Integer>> ans, List<Integer> combine, List<Integer> list){
        if (k < 0){
            return;
        }
        if (n == 0 && k == 0){
            ans.add(new ArrayList<>(combine));
        }
        int tmp = list.get(list.size() - 1);
        combine.add(tmp);
        list.remove(list.size() - 1);
        n -= tmp;
        --k;
        combinationSum3Dfs(k,n,ans,combine,list);
        combine.remove(list.size() - 1);
        list.add(tmp);
    }


    // 子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetsBacktrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    private void subsetsBacktrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            subsetsBacktrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }



    // 答案储存
    int ans;
    // 网格
    int[][] grid;
    int tr, tc;
    int[] dr = new int[]{0, -1, 0, 1};
    int[] dc = new int[]{1, 0, -1, 0};
    int R, C;

    public int uniquePathsIII(int[][] grid) {
        // 初始化
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;

        int todo = 0;
        int sr = 0, sc = 0;
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] != -1) {
                    todo++;
                }

                if (grid[r][c] == 1) {
                    sr = r;
                    sc = c;
                } else if (grid[r][c] == 2) {
                    tr = r;
                    tc = c;
                }
            }
        // sr sc 开始坐标
        // tr tc 结束坐标
        ans = 0;
        dfs(sr, sc, todo);
        return ans;
    }

    public void dfs(int r, int c, int todo) {
        todo--;
        if (todo < 0) return;
        if (r == tr && c == tc) {
            if (todo == 0) ans++;
            return;
        }
        // 标志走过
        grid[r][c] = 3;
        for (int k = 0; k < 4; ++k) {
            // 上下左右走一遍
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if (grid[nr][nc] % 2 == 0)
                    dfs(nr, nc, todo);
            }
        }
        // 取消走过 回溯点
        grid[r][c] = 0;
    }

}
