package Bilibili;

import java.util.Scanner;

public class Solution_2021_9_13 {
//    static int tmp = 0;
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
//        while (scanner.hasNext()){
//            String str =scanner.nextLine();
//            String[] numStr = str.replace("[","").replace("]","").split(",");
//            ArrayList<String> strings = new ArrayList<>(Arrays.asList(numStr));
//            arrayList.add(strings);
//            if (str.contains("]]")){
//                break;
//            }
//        }
////        arrayList.forEach(strings -> strings.forEach(s -> System.out.print(s + "\\")));
//        int[][] used = new int[arrayList.size()][arrayList.get(0).size()];
//
//        for (int i = 0; i < arrayList.size(); i++) {
//            for (int j = 0; j < arrayList.get(0).size(); j++) {
//                tmp = 0;
//                Dfs(used,j,i,arrayList);
//            }
//        }
//        ans.sort(Integer::compareTo);
//        System.out.println(ans.get(ans.size() - 1));
//
//        for (int[] ints : used) {
//            for (int i1 = 0; i1 < used[0].length; i1++) {
//                System.out.print(ints[i1]);
//            }
//            System.out.println();
//        }
//    }
//    static ArrayList<Integer> ans = new ArrayList<>();
//    /**
//     * 连续1
//     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
//     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
//     *  [0,1,1,1,1,0,0,0,0,0,0,0,0],
//     *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
//     *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
//     *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
//     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
//     *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
//     *  10
//     *
//     * @param used
//     * @param ans
//     * @param col
//     * @param row
//     * @param map
//     * @param tmp 临时结果
//     */
//    static void Dfs(int[][] used,int col,int row,ArrayList<ArrayList<String>> map){
//
//        if (row > map.size() - 1 || row < 0){
//            return;
//        }
//        if (col > map.get(0).size() - 1 || col < 0){
//            return;
//        }
//        if (map.get(row).get(col).equals("0") || used[row][col] == 1){
//            return;
//        }
//        if (map.get(row).get(col).equals("1") && used[row][col] == 0){
//            used[row][col] = 1;
//            tmp += 1;
//            ans.add(tmp);
//            Dfs(used,  col + 1, row , map);
//            Dfs(used,  col, row + 1, map);
//            Dfs(used,  col - 1, row , map);
//            Dfs(used,  col ,row - 1, map);
//        }
//    }

    /**
     * 螺旋矩形
     * @param args 参数
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = 0;
        int n = 0;
        m = scanner.nextInt();
        n = scanner.nextInt();
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int i1 = 0; i1 < n; i1++) {
                nums[i][i1] = scanner.nextInt();
                System.out.print(nums[i][i1] + " ");
            }
            System.out.println();
        }
        int startCol = 0;
        int endCol = n - 1;
        int startRow = 0;
        int endRow = m - 1;
        int print = 0;
        int flag = 0;
        while (startCol <= endCol && startRow <= endRow){
            for (int i = startCol; i <= endCol; i++) {
                System.out.print(nums[startRow][i] + "->");
            }
            for (int i = startRow + 1; i <= endRow; i++) {
                System.out.print(nums[i][endCol] + "|>");
            }
            for (int i = endCol - 1; i >= startCol; i--) {
                if (startRow == endRow){
                    break;
                }
                System.out.print(nums[endRow][i] + "<-");
            }
            for (int i = endRow - 1; i >= startRow + 1; i--) {
                if (startCol == endCol){
                    break;
                }
                System.out.print(nums[i][startCol] + "<|");
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
    }

}
