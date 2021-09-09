package ByteDance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ByteDance {
}

class Solution{
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int testNum = scanner.nextInt();
//        ArrayList<String> ans = new ArrayList<>();
//        for (int i = 0; i < testNum; i++) {
//            String str = scanner.next();
//            ans.add(doFilter(str));
//        }
//        for (String s:
//             ans) {
//            System.out.println(s);
//        }
//
//    }
    public static String doFilter(String str){
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Integer> delIndex = new ArrayList<>();
        String ans = "";
        if (str == ""){
            return ans;
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)){
                index.add(i);
            }
        }
//        System.out.println(index);
        for (int i = 0; i < index.size(); i++) {
            if (index.contains(index.get(i) - 1) || index.contains(index.get(i) - 2)){
                delIndex.add(index.get(i));
            }
        }
//        System.out.println(delIndex);
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!delIndex.contains(i)){
                stringBuilder.append(chars[i]);
            }
        }
        return stringBuilder.toString();
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int line = scanner.nextInt();
////        (.)\\1+ 表示 (.)\1+ 表示任意一个字符重复两次或两次以上（括号里的点表示任意字符，后面的\\1表示取第一个括号匹配的内容，后面的加号表示匹配1次或1次以上。二者加在一起就是某个字符重复两次或两次以上）
////        $1是第一个小括号里的内容，$2是第二个小括号里面的内容.
//        scanner.nextLine();
//        for (int i = 0; i < line; i++) {
//            System.out.println(scanner.nextLine().replaceAll("(.)\\1+","$1$1").replaceAll("(.)\\1(.)\\2","$1$1$2"));
//        }
//    }

//    private int mod = 99997867;
//
//    private void sln() {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt(), D = sc.nextInt();
//        long cnt = 0;
//        if (N <= 2) {
//            System.out.println(-1);
//            return;
//        }
//        int[] locs = new int[N];
//        for (int i = 0; i < N; i++) {
//            locs[i] = sc.nextInt();
//        }
//        sc.close();
//        int left = 0, right = 2;
//        while (right < N) {
//            if (locs[right] - locs[left] > D) left++;
//            else if (right - left < 2) right++;
//            else {
//                cnt += calC(right - left);
//                right++;
//            }
//        }
//        cnt %= mod;
//        System.out.println(cnt);
//    }
//
//    private long calC(long num) {
//        return num * (num - 1) / 2;
//    }

//    public static void main(String[] args) {
//        new Solution().sln();
//    }




    private void sln() {
        Scanner sc = new Scanner(System.in);
        int[] state = new int[9], helpArr = new int[9];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            int num = sc.nextInt();
            state[num - 1]++;
        }
        for (int i = 0; i < 9; i++) {
            if (state[i] < 4) {
                int num = i + 1;
                System.arraycopy(state, 0, helpArr, 0, 9);
                helpArr[i]++;
                if (canHu(helpArr, 14, false)) res.add(num);
            }
        }
        if (res.isEmpty()) System.out.println(0);
        else {
            StringBuffer sbf = new StringBuffer();
            sbf.append(res.get(0));
            for (int i = 1; i < res.size(); i++) {
                sbf.append(" ");
                sbf.append(res.get(i));
            }
            System.out.println(sbf.toString());
        }
    }

    private boolean canHu(int[] arr, int total, boolean hasHead) {
        if (total == 0) return true;
        if (!hasHead) {
            for (int i = 0; i < 9; i++) {
                if (arr[i] >= 2) {
                    arr[i] -= 2;
                    if (canHu(arr, total - 2, true)) return true;
                    arr[i] += 2;
                }
            }
            return false;
        } else {
            for (int i = 0; i < 9; i++) {
                if (arr[i] > 0) {
                    if (arr[i] >= 3) {
                        arr[i] -= 3;
                        if (canHu(arr, total - 3, true)) return true;
                        arr[i] += 3;
                    }
                    if (i + 2 < 9 && arr[i + 1] > 0 && arr[i + 2] > 0) {
                        arr[i]--;
                        arr[i + 1]--;
                        arr[i + 2]--;
                        if (canHu(arr, total - 3, true)) return true;
                        arr[i]++;
                        arr[i + 1]++;
                        arr[i + 2]++;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new Solution().sln();
    }
}


class Main{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] nums = new int[N][N];
        for(int i=0; i < N; i++){
            for(int j=0; j < N; j++){
                nums[i][j] = sc.nextInt();
            }
        }
        Integer[][] memo = new Integer[(1<<N)][N];
        System.out.println(DFS(nums, 0, 0, memo));
    }

    private static int DFS(int[][] nums, int idx, int state, Integer[][] memo){
        int n = nums.length;
        if(state == (1<<n)-2){ //防止出现0-1-2-0-3的情况
            return nums[idx][0];
        }
        if(memo[state][idx] != null) return memo[state][idx];
        int ret = Integer.MAX_VALUE;
        for(int i=1; i < n; i++){
            if((state & (1<<i)) != 0) continue;
            ret = Math.min(ret, nums[i][idx] + DFS(nums, i, (state ^ (1<<i)), memo));
        }
        memo[state][idx] = ret;
        return ret;
    }







//方法二 使用动态规划
//dp[i]：找i元钱最少需要多少硬币
//base: dp[0]=0;dp[1]=1
//状态转移方程：dp[i]=min(dp[i],dp[i-amount]+1);

//类比
//背包问题：装最少的东西将容量为1024-N的背包装满，每次可以装1/4/16/64
//爬楼梯问题：爬最少的次数爬完1024-N层楼梯，每次可以爬1/4/16/64层
    private int RecvNum_dp(int num) {
        int[] dp = new int[num + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int[] amount = {1,4,16,64};
        //base
        dp[0]=0;dp[1]=1;
        for(int i=2;i<=num;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(i>=amount[j])
                    dp[i]=Math.min(dp[i],dp[i-amount[j]]+1);
            }
        }
        return dp[num];
    }
}

class TSP2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int cityNum = in.nextInt();// 城市数目
        int[][] dist = new int[cityNum][cityNum];// 距离矩阵，距离为欧式空间距离
        for (int i = 0; i < dist.length; i++)
            for (int j = 0; j < cityNum; j++) {
                dist[i][j] = in.nextInt();
            }
        in.close();

        int V = 1 << (cityNum - 1);// 对1进行左移n-1位，值刚好等于2^(n-1)
        // dp表，n行，2^(n-1)列
        int[][] dp = new int[cityNum][V];
        // 初始化dp表第一列
        for (int i = 0; i < cityNum; i++)  dp[i][0] = dist[i][0];

        //设想一个数组城市子集V[j]，长度为V,且V[j] = j,对于V[j]即为压缩状态的城市集合
        //从1到V-1  用二进制表示的话，刚好可以映射成除了0号城市外的剩余n-1个城市在不在子集V[j]，1代表在，0代表不在
        //若有总共有4个城市的话，除了第0号城市，对于1-3号城市
        //111 = V-1 = 2^3 - 1  = 7 ，从高位到低位表示3到1号城市都在子集中
        //而101 = 5 ，表示3,1号城市在子集中，而其他城市不在子集中
        //这里j不仅是dp表的列坐标值，如上描述，j的二进制表示城市相应城市是否在子集中
        for (int j = 1; j < V; j++)
            for (int i = 0; i < cityNum; i++) { //这个i不仅代表城市号，还代表第i次迭代
                dp[i][j] = Integer.MAX_VALUE; //为了方便求最小值,先将其设为最大值
                if (((j >> (i - 1)) & 1) == 0) {
                    // 因为j就代表城市子集V[j],((j >> (i - 1))是把第i号城市取出来
                    //并位与上1，等于0，说明是从i号城市出发，经过城市子集V[j]，回到起点0号城市
                    for (int k = 1; k < cityNum; k++) { // 这里要求经过子集V[j]里的城市回到0号城市的最小距离
                        if (((j >> (k - 1)) & 1) == 1) { //遍历城市子集V[j]
                            //设s=j ^ (1 << (k - 1))
                            //dp[k][j ^ (1 << (k - 1))，是将dp定位到，从k城市出发，经过城市子集V[s]，回到0号城市所花费的最小距离
                            //怎么定位到城市子集V[s]呢，因为如果从k城市出发的，经过城市子集V[s]的话
                            //那么V[s]中肯定不包含k了，那么在j中把第k个城市置0就可以了，而j ^ (1 << (k - 1))的功能就是这个
                            dp[i][j] = Math.min(dp[i][j], dist[i][k] + dp[k][j ^ (1 << (k - 1))]); //^异或
                            //还有怎么保证dp[k][j ^ (1 << (k - 1))]的值已经得到了呢，
                            //注意所有的计算都是以dp表为准，从左往右从上往下的计算的，每次计算都用到左边列的数据
                            //而dp表是有初试值的，所以肯定能表格都能计算出来
                        }
                    }
                }
            }
        System.out.println(dp[0][V - 1]);
    }
}
