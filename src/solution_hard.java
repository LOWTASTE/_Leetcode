import java.util.HashMap;
import java.util.Map;

public class solution_hard {

//暴力解法
/*    static public int numSubMatrixSumTarget(int[][] matrix, int target) {
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int x1 = 0; x1 < m; x1++) {
            for (int x2 = x1; x2 < m; x2++) {
                for (int y1 = 0; y1 < n; y1++) {
                    for (int y2 = y1; y2 < n; y2++) {
                        if (target == calculateMatrix(matrix, x1, x2, y1, y2))
                            ans++;
                    }
                }
            }
        }
        return ans;
    }

    static public int calculateMatrix(int[][] matrix, int x1, int x2, int y1, int y2){
        if (x2 < x1){
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (y2 < y1){
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        int ans = 0;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                ans += matrix[i][j];
            }
        }
        return ans;
    }*/


//    给出矩阵matrix和目标值target，返回元素总和等于目标值的非空子矩阵的数量。
//    子矩阵x1, y1, x2, y2是满足 x1 <= x <= x2且y1 <= y <= y2的所有单元matrix[x][y]的集合。
//    如果(x1, y1, x2, y2) 和(x1', y1', x2', y2')两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
//    TODO:前缀和思想

    static public int numSubMatrixSumTarget(int[][] matrix, int target) {
        int ans = 0;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) {       // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) {   // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                ans += subarraySum(sum, target);
            }
        }
        return ans;
    }

    static public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        int count = 0, pre = 0;
        for (int x : nums) {
            pre += x;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1,-1},{-1,1}};
        int target = 0;
        System.out.println(numSubMatrixSumTarget(matrix, target));
    }
}
