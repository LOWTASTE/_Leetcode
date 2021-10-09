package XieCheng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_9_9_2021 {
    //题目描述：
    //        现在给你一个长度为n的01序列，你可以通过消除连续的1的序列来获取一定的分数。
    //        题目中将给你若干个长度和分数的对应关系，你需要正确求解出对应的答案。
    //        例如：现在给你一个长度为12的01序列111111011111。
    //        现在给你如下可以获取得分的消除方式：
    //        消除三个连续的1，获取得分10
    //        消除四个连续的1，获取得分15
    //        对于前面的六个连续的1，你的消除方案有两种：
    //        消除一次连续的四个1，获得得分15，或者进行两次连续的三个1消除，获取得分20.
    //        对于后面的五个连续的1，你有两种消除方案：
    //        消除一次连续的四个1，获得得分15，或者消除一次连续的三个1，获得得分10
    //        上述方案中可以获得的最大分数是20 + 15 = 35.
    //        你的任务就是设法获得最大的消除分数。
    //        请注意：不一定需要把所有的1的消除完毕，我们的目标是最大化分数而不是最大化消除个数。
    // 【输入描述】 第一行两个空格隔开的正整数n,m，分别表示01串的长度和消除规则的数量。
    // 接下来一行字符串长度为n，每个字符只能是0或者1中的一种。  接下来m行，每行两个空格隔开的正整数k, x，为消除连续的k个1可以获得的分数x  输出描述 输出可以获得的最大分数。
    // 样例输入
    // 11 2
    // 11111101111
    // 3 10 4 15
    // 样例输出
    // 35

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String ss = br.readLine();
//        String[] sss = ss.split(" ");
//        int line = Integer.parseInt(sss[1]);
//        char[] nums = br.readLine().toCharArray();  // 01序列
//        int[][] wvs = new int[line + 1][2];
//        for (int i = 1; i <= line; i++) {
//            String[] wv = br.readLine().split(" ");
//            wvs[i][0] = Integer.parseInt(wv[0]);
//            wvs[i][1] = Integer.parseInt(wv[1]);
//        }
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == '0') {
//                continue;
//            }
//            int W = 0;
//            while (i < nums.length && nums[i] == '1') {
//                W++;
//                i++;
//            }
//            // 将1的连续序列分段
//            list.add(W);
//            i--;
//        }
//        int res = 0;
//        for (Integer W : list) {
//            res += maxValue(W, wvs);
//        }
//        System.out.println(res);
//    }
//
//    public static int maxValue(int V, int[][] wv) {
//        int N = wv.length - 1;
//        int[] dp = new int[V + 1];
//        for (int i = 1; i <= N; i++) {
//            for (int j = 0; j <= V; j++) {
//                if (j >= wv[i][0]) {
//                    dp[j] = Math.max(dp[j - wv[i][0]] + wv[i][1], dp[j]);
//                }
//            }
//        }
//        return dp[V];
//    }


    // 有一个长度为n的序列A，序列中的第i个数为A[i] (1<=i<=n)，现在你可以将序列分成至多连续的k段。
    // 对于每一段，我们定义这一段的不平衡度为段内的最大值减去段内的最小值。显然，对于长度为1的段，其不平衡度为0。
    // 对于一种合法的分段方式（即每一段连续且不超过k段），我们定义这种分段方式的不平衡度为每一段的不平衡度的最大值。
    // 现在你需要找到不平衡度最小的分段方式，输出这种分段方式的不平衡度即可。
    //输入描述
    //第一行两个空格隔开的整数n，k，分别表示序列的长度和最多可分成的段数。
    //第二行是n个用空格隔开的整数，第i个数表示A[i]的值。
    //
    //输出描述
    //一行一个整数，表示最小的不平衡度。
    //输入
    //4 2
    //3 4 7 6
    //输出
    //1

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        k = Integer.parseInt(split[1]);
        String line = bufferedReader.readLine();
        String[] splits = line.split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(splits[i]);
        }
        int left = 0, right = 100002;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (check(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    static int n;
    static int[] nums = new int[100002];
    static int k;

    private static boolean check(int bphd) {
        int count = 1;
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            min = Math.min(cur, min);
            max = Math.max(max, cur);
            if (max - min > bphd) {
                count ++;
                min = cur;
                max = cur;
            }
        }
        return count <= k;
    }


//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String[] nk = scanner.nextLine().split(" ");
//        int n = Integer.parseInt(nk[0]);
//        int k = Integer.parseInt(nk[1]);
//        if (k > n){
//            System.out.println(0);
//            return;
//        }
//        String[] numStr = scanner.nextLine().split(" ");
//        ArrayList<Integer> integers = new ArrayList<>();
//        for (int i = 0; i < numStr.length; i++) {
//            integers.add(Integer.parseInt(numStr[i]));
//        }
//        if (integers.size() != n){
//            return;
//        }
//        // 0 - i i - j j - n k = 3
//        // 暴力需要 k - 1 个量代表位置
//
//        // 储存临时的值
//        HashSet<Integer> hashSet = new HashSet<>();
//        // 所有数字集合
//        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
//        findKPos(n,k-1,hashSet,arrayLists);
////        arrayLists.forEach(arrayList -> arrayList.forEach(System.out::println));
//        int ans = Integer.MAX_VALUE;
//        for (ArrayList <Integer> arrayList: arrayLists) {
//            int tmp = 0;
//            tmp += unBalance(integers,0,arrayList.get(0));
//            for (int i = 1; i < arrayList.size(); i++){
//                tmp +=unBalance(integers,arrayList.get(i - 1) + 1,arrayList.get(i));
//            }
//            tmp += unBalance(integers,arrayList.get(arrayList.size() - 1) + 1,integers.size() - 1);
//            ans = Math.min(tmp,ans);
//        }
//        System.out.println(ans);
//    }
//    public static void findKPos(int n,int k,HashSet<Integer> hashSet,ArrayList<ArrayList<Integer>> arrayLists){
//        if (hashSet.size() == k){
//            ArrayList<Integer> integers = new ArrayList<>(hashSet);
//            arrayLists.add(integers);
//            return;
//        }
//        for (int i = 0; i < n - 1; i++) {
//            if (hashSet.contains(i)){
//                return;
//            }
//            hashSet.add(i);
//            findKPos(n,k, hashSet,arrayLists);
//            hashSet.remove(i);
//        }
//    }
//    public static int unBalance(ArrayList<Integer> arrayList,int start,int end){
//        int max = Integer.MIN_VALUE;
//        int min = Integer.MAX_VALUE;
//        if (start > end){
//            return 0;
//        }
//        if (start == end){
//            return 0;
//        }
//        for (int i = start; i <= end; i++) {
//            max = Math.max(max,arrayList.get(i));
//            min = Math.min(min,arrayList.get(i));
//        }
//        return max - min;
//    }


    //你需要编写一个程序来模拟目录的操作，一开始，你在根目录"\"，一共有两种命令：
    // ●  cd s: s为一个目录名，表示从当前工作目录的路径进入名为s的目录。
    //特别地，"cd .."(即s=="..")表示返回上一级目录，若当前已为根目录，则无视该次操作。数据保证若s不为".."，则一定为小写字母组成的长度不超过10的字符串。
    // ● pwd: 表示查看当前工作目录的路径，你需要输出这个路径。

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = Integer.parseInt(scanner.nextLine().trim());
//        String[] cmd = new String[n];
//        for (int i = 0; i < n; i++) {
//            cmd[i] = scanner.nextLine();
//        }
//        ArrayList<String> output = new ArrayList<>();
//        ArrayList<String> strings = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            if (cmd[i].contains("cd")){
//                if (cmd[i].split(" ")[1].trim().equals("..")){
//                    strings.remove(strings.size() - 1);
//                } else {
//                    strings.add(cmd[i].split(" ")[1].trim());
//                }
//            } else if(cmd[i].equals("pwd")) {
//                StringBuilder stringBuilder = new StringBuilder();
//                if (strings.size() == 0){
//                    output.add("\\");
//                    continue;
//                }
//                for (int j = 0;j < strings.size();j++){
//                    stringBuilder.append("\\").append(strings.get(j));
//                }
//                output.add(stringBuilder.toString());
//            }
//        }
//        output.forEach(System.out::println);
//    }
}