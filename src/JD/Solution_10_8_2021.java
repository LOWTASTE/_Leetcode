package JD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_10_8_2021 {
}

// 给出m个字符串S1，S2，...，Sm和一个单独的字符串T。请在T中选出尽可能多的子串同时满足：
// 1）这些子串在T中互不相交。
// 2）这些子串都是S1，S2，...，Sm中的某个串。
// 问最多能选出多少个子串。

//输入描述:
//第一行一个数m（1≤m≤10），接下来m行，每行一个串。最后一行输入一个串T。输入中所有单个串的长度不超过100000，串中只会出现小写字母。

//输出描述:
//输出一个数，最多能选出多少串。

class Solution0{
    // 堆空间不足
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        String[] strings = new String[num];
        for (int i = 0; i < num; i++) {
            strings[i] = scanner.nextLine();
            if (strings[i].equals("")){
                return;
            }
        }
        String in = scanner.nextLine();
        helper(in,strings,0);
        System.out.println(ans);
    }
    static Integer ans = 0;
    public static void helper(String in,String[] dic,Integer tmp){
        ans =Math.max(ans,tmp);
        for (String s : dic) {
            if (in.contains(s)) {
                helper(in.replaceFirst(s, " "), dic, tmp + 1);
            }
        }
    }
}

class Solution0_1{
    //求模式串在主串中出现的所有位置区间
    private static int[][] intervals = new int[500000][2];
    private static int k = 0;//下标
    //求模式串的next数组
    public static void getNext(int[] next,String t){
        int j = 0;
        int k = -1;
        next[0] = -1;
        while(j < t.length()-1){
            if(k == -1||t.charAt(j) == t.charAt(k)){
                next[++j] = ++k;
            }else{
                k = next[k]; //回溯
            }
        }
    }

    public static void KMP(String s,String t){
        int[] next = new int[t.length()];
        int i=0,j=0;
        getNext(next,t);//求next数组
        while(i < s.length()){
            if(j==-1||s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else
                j = next[j]; //j回退
            //返回子串在主串中的出现位置
            if(j == t.length()){
                intervals[k++] = new int[]{i-j,i-1};
                j = 0; //重新置为0
            }
        }
    }

    //只保留不相交的区间  活动时间表
    public static int merge(int[][] intervals){
        int count = 0;
        int tail = -1;
        //将所有区间按照右边界进行排序
        Arrays.sort(intervals,(o2, o1) -> o2[1]-o1[1]);
        for (int[] interval : intervals) {
            //若数组为空或当前区间的左边界大于结果集中最后一个区间的右边界则是不相交的
            if (interval[0] > tail) {
                count++;
                tail = interval[1];
            }
        }
        return count;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] t = new String[n];
        for(int i=0;i<n;i++){
            t[i] = sc.next();
        }
        String s = sc.next();

        //寻找各个子串在主串中的区间
        for(int i=0;i<n;i++){
            // findSubString(s,t[i]);
            KMP(s,t[i]);
        }
        intervals = Arrays.copyOf(intervals,k);
        //合并所有相交的区间
        System.out.println(merge(intervals));
    }
}
//小汪作为一个有数学天分的程序猿，设计了一套密码生成器来搞定自己的密码问题。
//密码生成器由N个槽位组成，槽位的下标为0~N-1，每个槽位存储一个数。起初每个槽位都是0。
//密码生成器会进行M轮计算，每轮计算，小汪会输入两个数L,R(L<=R),密码生成器会将这两个数作为下标，将两个下标之间（包含）的所有槽位赋值为i（i为当前的轮次，i∈[1,M]）。
//M轮计算完成后，密码生成器会根据槽位的最终值生成一条密码，密码的生成规则为：
//（0*a[0] + 1*a[1] + 2*a[2] + ... + (N-1)*a[N-1]) mod 100000009
//其中a[i]表示第i个槽位的最终值。
//请帮助小汪把他的密码生成器实现为代码。
//
//输入描述:
//第一行为两个整数N,M,表示槽位个数和计算轮数。
//接下来M行，每行两个整数Li,Ri，表示第i轮计算的输入。
//
//输出描述:
//输出一行，一个整数A,表示小汪的开机密码。
//
//输入例子1:
//5 3
//2 3
//1 2
//1 1
//
//输出例子1:
//10
//
//例子说明1:
//对于输入样例，密码生成过程如下：
//
//初始： 0 0 0 0 0
//第1轮：0 0 1 1 0
//第2轮：0 2 2 1 0
//第3轮：0 3 2 1 0
//密码生成器最终生成 0 3 2 1 0，则密码为(0*0 + 3*1 + 2*2 + 1*3 + 0*4) mod 100000009 = 10
class Solution1{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int round = scanner.nextInt();
        ArrayList<int[]> arrayList = new ArrayList<>();
        for (int i = 0; i < round; i++) {
            int[] lr = new int[2];
            lr[0] = scanner.nextInt();
            lr[1] = scanner.nextInt();
            arrayList.add(lr);
        }
        int[] passwordTmp = new int[len];
        int count = 1;
        for (int[] lr: arrayList) {
            for (int i = lr[0]; i <= lr[1]; i++) {
                passwordTmp[i] = count;
            }
//            System.out.println(Arrays.toString(Arrays.stream(passwordTmp).toArray()));
            count++;
        }
//        System.out.println(Arrays.toString(Arrays.stream(passwordTmp).toArray()));
        System.out.println(generatorPassword(passwordTmp));
    }
    public static int generatorPassword(int[] passwordTmp){
        int ans = 0;
        for (int i = 0; i < passwordTmp.length; i++) {
            ans += (passwordTmp[i] * i) % 100000009;
        }
        return ans;
    }
}

//给出一个二维字符数组和一个单词，判断单词是否在数组中出现，
//单词由相邻单元格的字母连接而成，相邻单元指的是上下左右相邻。同一单元格的字母不能多次使用。
//例如：
//给出的字符数组=
//[
//  ["XYZE"],
//  ["SFZS"],
//  ["XDEE"]
//]
//单词 ="XYZZED", -> 返回 true,
//单词 ="SEE", ->返回 true,
//单词 ="XYZY", -> 返回 fXlse.

class Solution3 {
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dfs(word,0,board,i,j,visited);
            }
        }
        return flag;
    }
    static boolean flag = false;
    public static void dfs(String word,int wordIdx,char[][] board,int i,int j,boolean[][] visited){
        if (wordIdx == word.length()){
            flag = true;
            return;
        }
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(wordIdx)){
            return;
        }
        if (visited[i][j]){
            return;
        }
        visited[i][j] = true;
        dfs(word, wordIdx + 1, board, i - 1, j, visited);
        dfs(word, wordIdx + 1, board, i + 1, j, visited);
        dfs(word, wordIdx + 1, board, i, j - 1, visited);
        dfs(word, wordIdx + 1, board, i, j + 1, visited);
        visited[i][j] = false;
    }

    public static void main(String[] args) {
        char[][] chars = {{'X','Y','Z','E'},{'S','F','Z','S'},{'X','D','E','E'}};
        System.out.println(exist(chars, "XYZZED"));
    }
}



//体育场突然着火了，现场需要紧急疏散，但是过道真的是太窄了，同时只能容许一个人通过。
//现在知道了体育场的所有座位分布，座位分布图是一棵树，已知每个座位上都坐了一个人，安全出口在树的根部，也就是1号结点的位置上。
//其他节点上的人每秒都能向树根部前进一个结点，但是除了安全出口以外，没有任何一个结点可以同时容纳两个及以上的人，
//这就需要一种策略，来使得人群尽快疏散，问在采取最优策略的情况下，体育场最快可以在多长时间内疏散完成。

//输入描述:
//第一行包含一个正整数n，即树的结点数量（1<=n<=100000）。 接下来有n-1行，每行有两个正整数x，y，表示在x和y结点之间存在一条边。(1<=x，y<=n)

//输出描述:
//输出仅包含一个正整数，表示所需要的最短时间

/**
 *
 *  这道题是找根节点下的最大子树
 *  因为是多叉树，可以利用图中的邻接表
 *  先记录根节点下的次子节点子节点。分别以次子节点为根，
 *  然后利用广度优先或者深度优先遍历计算得到各个节点的子树节点总和
 *  取次子节点中最大的值作为结果
 *
 **/
class Solution2{
    public static void main(String[] args) throws Exception{
        BufferedReader df = new BufferedReader(new InputStreamReader(System.in));
        String sd;
        while((sd = df.readLine()) != null){
            int n = Integer.parseInt(sd);
            if(n <= 1){
                System.out.println(0);
                return ;
            }
            HashMap<Integer, Set<Integer>> map = new HashMap<>();
            for(int i = 1;i <= n;i++){
                Set<Integer> set=new HashSet<>();
                //set.add(i);
                map.put(i,set);
            }
            for(int i=0;i<n-1;i++){
                String[] gg=df.readLine().split(" ");
                int t1=Integer.parseInt(gg[0]);
                int t2=Integer.parseInt(gg[1]);
                map.get(t1).add(t2);
                map.get(t2).add(t1);
            }
            boolean[] res = new boolean[n + 1];
            Deque<Integer> list = new LinkedList<>();
            res[1]=true;
            int max = 0;
            for(Integer jj:map.get(1)){
                if(res[jj]){
                    continue;
                }
                list.add(jj);
                res[jj] = true;
                int num = 0;
                while(!list.isEmpty()){
                    int size = list.size();
                    for(int i = 0;i < size;i++){
                        int k = -1;
                        if (list.peek() != null){
                            k = list.poll();
                        }
                        num++;
                        for(Integer ff:map.get(k)){
                            if(res[ff]){
                                continue;
                            }
                            list.add(ff);
                            res[ff] = true;
                        }
                    }
                }
                max=Math.max(max,num);
            }
            System.out.println(max);
        }
    }
}


