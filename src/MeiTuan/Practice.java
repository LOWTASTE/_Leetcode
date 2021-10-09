package MeiTuan;

import java.io.*;
import java.util.*;

public class Practice {

}


class Solution1{
//    某比赛已经进入了淘汰赛阶段,已知共有n名选手参与了此阶段比赛，
//    他们的得分分别是a_1,a_2….a_n,小美作为比赛的裁判希望设定一个分数线m，
//    使得所有分数大于m的选手晋级，其他人淘汰。
//    但是为了保护粉丝脆弱的心脏，小美希望晋级和淘汰的人数均在[x,y]之间。
//    显然这个m有可能是不存在的，也有可能存在多个m，如果不存在，请你输出-1，
//    如果存在多个，请你输出符合条件的最低的分数线。
    //input
    // n x y
    // a_i

    //output
    //m

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[] a_i = new int[n];
        for (int i = 0; i < n; i++) {
            a_i[i] = scanner.nextInt();
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min =Math.min(min,a_i[i]);
            max =Math.max(max,a_i[i]);
        }
        int ans = 0;
        for (int m = min;m < max;m++){
            int countFailed = 0;
            int countPass = 0;
            for (int j : a_i) {
                if (j <= m) {
                    ++countFailed;
                } else {
                    ++countPass;
                }
            }
            if (countPass>=x&&countPass<=y&&countFailed>=x&&countFailed<=y){
                ans = m;
                break;
            }
        }
        System.out.println(ans);
    }
}

class Solution2{
//    我们称一个长度为n的序列为正则序列，当且仅当该序列是一个由1~n组成的排列，
//    即该序列由n个正整数组成，取值在[1,n]范围，且不存在重复的数，
//    同时正则序列不要求排序
//    有一天小团得到了一个长度为n的任意序列，
//    他需要在有限次操作内，将这个序列变成一个正则序列，
//    每次操作他可以任选序列中的一个数字，并将该数字加一或者减一。
//    请问他最少用多少次操作可以把这个序列变成正则序列？

//    输入第一行仅包含一个正整数n，表示任意序列的长度。(1<=n<=20000)
//    输入第二行包含n个整数，表示给出的序列，每个数的绝对值都小于10000。

    // 0.1 ;
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] res = new int[n];
//        for (int i = 0; i < n; i++) {
//            res[i] = scanner.nextInt();
//        }
//        int ans = 0;
//        HashSet<Integer> hashSet = new HashSet<>();
//        for (int i = 0; i < n; i++) {
//            if (res[i] < 1){
//                ans += Math.abs(res[i] - 1);
//                res[i] = 1;
//            }
//            if (res[i] > n){
//                ans += Math.abs(res[i] - n);
//                res[i] = n;
//            }
//            if (!hashSet.contains(res[i])){
//                hashSet.add(res[i]);
//            } else {
//                for (int j = 1; j < n; j++) {
//                    int tmp1 = res[i] + j;
//                    int tmp2 = res[i] - j;
//                    if (!hashSet.contains(tmp1)){
//                        hashSet.add(tmp1);
//                        ans += j;
//                        break;
//                    }
//                    if (!hashSet.contains(tmp2)){
//                        hashSet.add(tmp2);
//                        ans += j;
//                        break;
//                    }
//                }
//            }
//        }
//        System.out.println(ans);
//    }
    public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int n = Integer.parseInt(br.readLine().trim());
                String[] strSeq = br.readLine().trim().split(" ");
                int[] seq = new int[n];
                for(int i = 0; i < n; i++) seq[i] = Integer.parseInt(strSeq[i]);
                Arrays.sort(seq);
                int modifyTimes = 0;
                for(int i = 1; i <= n; i++) modifyTimes += Math.abs(seq[i - 1] - i);
                System.out.println(modifyTimes);
    }
}


//    我们称一个长度为n的序列为正则序列，当且仅当该序列是一个由1~n组成的排列，
//    即该序列由n个正整数组成，取值在[1,n]范围，且不存在重复的数，
//    同时正则序列不要求排序
//    有一天小团得到了一个长度为n的任意序列，
//    他需要在有限次操作内，将这个序列变成一个正则序列，
//    每次操作他可以任选序列中的一个数字，并将该数字加一或者减一。
//    请问他最少用多少次操作可以把这个序列变成正则序列？

//    输入第一行仅包含一个正整数n，表示任意序列的长度。(1<=n<=20000)
//    输入第二行包含n个整数，表示给出的序列，每个数的绝对值都小于10000。

class Solution3{


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-- > 0){
            int tableNum = scanner.nextInt();
            int[] tables = new int[tableNum];

            String str = scanner.next();
            int index = 0;
            // 座位
            for (String personNum : str.split("")){
                tables[index++] = Integer.parseInt(personNum);
            }
            int personOutNum = scanner.nextInt();
            // 性别输入
            String[] personGender = scanner.next().split("");

            // 处理
            for (String gender : personGender) {
                int ans = -1;
                if (gender.equals("M")){
                    for (int i = 0; i < tableNum; i++) {
                        if (tables[i] == 1){
                            tables[i]++;
                            ans = i;
                            break;
                        }
                    }
                    if (ans == -1){
                        for (int i = 0; i < tableNum; i++) {
                            if (tables[i] == 0){
                                tables[i]++;
                                ans = i;
                                break;
                            }
                        }
                    }
                }
                else if (gender.equals("F")){
                    for (int i = 0; i < tableNum; i++) {
                        if (tables[i] == 0){
                            tables[i]++;
                            ans = i;
                            break;
                        }
                    }
                    if (ans == -1){
                        for (int i = 0; i < tableNum; i++) {
                            if (tables[i] == 1){
                                tables[i]++;
                                ans = i;
                                break;
                            }
                        }
                    }
                }

                System.out.println(ans + 1);
            }

        }
    }
}



// 选座位问题，优先队列维护。效率较高。
// 小美和小团所在公司的食堂有N张餐桌，从左到右摆成一排，每张餐桌有2张餐椅供至多2人用餐，公司职员排队进入食堂用餐。
// 小美发现职员用餐的一个规律并告诉小团：当男职员进入食堂时，他会优先选择已经坐有1人的餐桌用餐，只有当每张餐桌要么空着要么坐满2人时，他才会考虑空着的餐桌；
// 当女职员进入食堂时，她会优先选择未坐人的餐桌用餐，只有当每张餐桌都坐有至少1人时，她才会考虑已经坐有1人的餐桌；
// 无论男女，当有多张餐桌供职员选择时，他会选择最靠左的餐桌用餐。
// 现在食堂内已有若干人在用餐，另外M个人正排队进入食堂，小团会根据小美告诉他的规律预测排队的每个人分别会坐哪张餐桌。

class Solution4 {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine());
            String tables = reader.readLine();
            int M = Integer.parseInt(reader.readLine());
            String enters = reader.readLine();

            int[] res = solve(tables, enters);
            for (int r : res) {
                writer.write(Integer.toString(r));
                writer.newLine();
            }
        }
        writer.flush();
    }

    private static int[] solve(String tables, String enters) {
        List<PriorityQueue<Integer>> pqs = new ArrayList<>(3);
        pqs.add(new PriorityQueue<>());
        pqs.add(new PriorityQueue<>());
        pqs.add(new PriorityQueue<>());
        for (int i = 0; i < tables.length(); i++) {
            pqs.get(tables.charAt(i) - '0').add(i);
        }
        int[] res = new int[enters.length()];
        for (int i = 0; i < enters.length(); i++) {
            int table;
            if (enters.charAt(i) == 'M') {
                if (pqs.get(1).isEmpty()) {
                    table = pqs.get(0).poll();
                    pqs.get(1).add(table);
                } else {
                    table = pqs.get(1).poll();
                    pqs.get(2).add(table);
                }
            } else {
                if (!pqs.get(0).isEmpty()) {
                    table = pqs.get(0).poll();
                    pqs.get(1).add(table);
                } else {
                    table = pqs.get(1).poll();
                    pqs.get(2).add(table);
                }
            }
            res[i] = table + 1;
        }
        return res;
    }
}


// 小团有一个由N个节点组成的二叉树，每个节点有一个权值。定义二叉树每条边的开销为其两端节点权值的乘积，二叉树的总开销即每条边的开销之和。
// 小团按照二叉树的中序遍历依次记录下每个节点的权值，即他记录下了N个数，第i个数表示位于中序遍历第i个位置的节点的权值。
// 之后由于某种原因，小团遗忘了二叉树的具体结构。
// 在所有可能的二叉树中，总开销最小的二叉树被称为最优二叉树。现在，小团请小美求出最优二叉树的总开销。
class Solution5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // n个节点
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[][][] dp = new int[n][n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1][i] = arr[i] * arr[i + 1];
            dp[i][i + 1][i + 1] = dp[i][i + 1][i];
        }
        for (int k = 2; k < n; k++) {
            for (int i = 0; i < n - k; i++) {
                for (int m = i; m <= i + k; m++) {
                    int left = i == m ? 0 : dp[i][m - 1][i] + arr[i] * arr[m];
                    for (int l = i + 1; l < m; l++) {
                        left = Math.min(left, dp[i][m - 1][l] + arr[l] * arr[m]);
                    }
                    int right = m == i + k ? 0 : dp[m + 1][i + k][i + k] + arr[i + k] * arr[m];
                    for (int r = m + 1; r < i + k; r++) {
                        right = Math.min(right, dp[m + 1][i + k][r] + arr[r] * arr[m]);
                    }
                    dp[i][i + k][m] = left + right;
                }
            }
        }
        int res = dp[0][n - 1][0];
        for (int i = 1; i < n; i++) {
            res = Math.min(res, dp[0][n - 1][i]);
        }
        System.out.println(res);
    }
}

class Solution5_1{
        static int[][][] mem;
        static int[] weight;
        static int n;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine().trim());
            String[] strW = br.readLine().trim().split(" ");
            weight = new int[n];
            for(int i = 0; i < n; i++) weight[i] = Integer.parseInt(strW[i]);
            // mem[l][r][k]表示以weight[l:r]为子节点，以weight[k]为根节点的树开销
            mem = new int[n][n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++)
                    for(int k = 0; k < n; k++) mem[i][j][k] = -1;
            }
            System.out.println(recur(0, n - 1, -1));
        }

        private static int recur(int left, int right, int root) {
            if(left > right) return 0;
            if(root >= 0 && mem[left][right][root] != -1) return mem[left][right][root];
            int cost = Integer.MAX_VALUE;
            // [left,right]中的元素轮流做根节点构建二叉树
            int leftCost = 0, rightCost = 0;
            for(int i = left; i <= right; i++){
                leftCost = recur(left, i - 1, i);     // 左子树开销
                rightCost = recur(i + 1, right, i);    // 右子树开销
                // root=-1时表示初始根节点还没有确定，不会有根节点连接左右子树的边
                cost = Math.min(cost, leftCost + rightCost + weight[i]*(root != -1? weight[root]: 0));
            }
            if(root >= 0) mem[left][right][root] = cost;
            return cost;
        }
}

//小美最近发现了一种有趣的游戏，给定一个队列q，小美会按照以下规则进行游戏：
//每次从队列中取出一个数，如果这个数是当前队列中最小的值，那么小美就会丢掉这个数。
//否则小美就会把这个数重新加入队列。
//小美会一直进行游戏直到队列变空为止，但是小美并没有多少耐心，因此她想知道她最多需要进行多少次操作才能结束游戏。
class Solution6{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] arr = new int[len];
        int ans = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
            queue.add(arr[i]);
        }
        Arrays.sort(arr);
        int pos = 0;
        while (!queue.isEmpty()){
            int tmp = queue.poll();
            if (tmp == arr[pos]){
                pos += 1;
            }
            else {
                queue.offer(tmp);
            }
            ans++;
        }
        System.out.println(ans);
    }
}

//小团作为一名美团骑手，最喜欢的颜色就是黄和黑，因此对包含这两种颜色的事物都格外留意。
//这天他发现有一棵树，树上的每个节点都是黄的或者黑的。
//现在小团想知道对于这棵树中的每个节点，在以其为根的子树中，所有与其颜色不同的节点的深度之和是多少。
//子树中节点的深度被定义为该节点与该子树根节点之间的最短路径的边数。
class Solution7{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] color = new int[len];
        int[] parent = new int[len];
        for (int i = 0; i < color.length; i++) {
            color[i] = scanner.nextInt();
        }
        for (int i = 0; i < parent.length; i++) {
            parent[i] = scanner.nextInt();
        }

    }
}

//第一行两个正整数N和P，N表示蟠桃的数量，P是王母娘娘还有多长时间后会回来。
//接下来一行N个整数表示每个蟠桃吃掉所需的时间。
//注意，掐着王母娘娘的回来的点吃完是很危险的，所以必须在王母娘娘回来前吃完，
//不能是回来时刚好吃完。
class Solution8{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        long time = scanner.nextLong();
        long[] peach = new long[len];
        int ans = 0;
        for (int i = 0; i < peach.length; i++) {
            peach[i] = scanner.nextLong();
        }
        Arrays.sort(peach);
        for (long j : peach) {
            if (time - j > 0) {
                ans++;
                time = time - j;
            } else {
                break;
            }
        }
        System.out.println(ans);
    }
}

//小团正在装饰自己的书桌，他的书桌上从左到右有m个空位需要放上装饰物。
//商店中每个整数价格的装饰物恰好有一种，且每种装饰物的数量无限多。
//小团去商店的时候，想到了一个购买方案，他要让右边的装饰物价格是左边的倍数。
//用数学语言来说，假设小团的m个装饰物价格为a_1,a_2,...,a_m，那么对于任意的1≤i≤j≤m，a_j是a_i的倍数。
//小团是一个节约的人，他希望最贵的装饰物不超过n元。现在，请你计算小团有多少种购买的方案？
class Solution9{

    public static ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer> tmp = new ArrayList<>();
        backtrace(n,m,tmp);
        System.out.println(arrayList.size());
    }
    public static void backtrace(int n,int m,ArrayList<Integer> tmp){
        if (tmp.size() == m){
            ArrayList<Integer> integerArrayList = new ArrayList<>(tmp);
            arrayList.add(integerArrayList);
            return;
        }
        // 倍数
        for (int i = 1; i <= n; i++) {
            // 基数
            for (int j = 1; j <= n; j++) {
                if (i * j > n){
                    return;
                }
                tmp.add(i * j);
                backtrace(n, m, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}

// 替换空格
class Solution10{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] chars = {' ','a',' ','b','c',' ','\0'};
        System.out.println(chars);
        int len = chars.length;
        int pos = 0;
        int insert = 0;
        int prev = 0; // 标记前一个是否' '
        for (insert = 0; insert < chars.length; insert++) {
            if (chars[insert] == '\0'){
                break;
            }
            if (chars[insert] != ' ' || prev == 1){
                prev = 1;
                if (chars[insert] == ' '){
                    prev = 0;
                }
                char tmp = chars[insert];
                chars[insert] = chars[pos];
                chars[pos] = tmp;
                pos++;
            }
        }
        int offset = 0;
        for (int i = chars.length - 2; i >= 0; i--) {
            if (chars[i] != ' '){
                break;
            }
            offset++;
        }
        chars[chars.length - 1 - offset] = chars[chars.length - 1];
        chars[chars.length - 1] = ' ';
        System.out.println(chars);
    }
}