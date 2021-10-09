package XieCheng;


public class Solution_9_30_2021 {
    //两个字符串是匹配的，当且仅当
    //• 两个字符串的长度相等
    //• 两个字符串的每一个位置都是匹配的
    //小程现在有一个长度为n的字符串，仅由 T,R,I,P组成
    //其中，T,R互相匹配，I,P互相匹配
    //小程想知道该字符串存在多少个非空子串S，满足：S存在一个排列与S匹配
    //子串的定义：串中任意个连续的字符组成的子序列称为该串的子串
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int len = Integer.parseInt(sc.nextLine());
//        String str = sc.nextLine();
//        sc.close();
//        if (str.length() != len){
//            return;
//        }
//        int ans = 0;
//        char[] chars = str.toCharArray();
//        HashMap<Character,Integer> hashMap = new HashMap<>();
//        for (char c : chars){
//            if (hashMap.containsKey(c)){
//                hashMap.put(c,hashMap.get(c) + 1);
//            }
//            else {
//                hashMap.put(c, 1);
//            }
//        }
//        // 一层控制长度
//        for (int i = 1; i < len; i++) {
//            // 一层控制开始index
//            HashMap<Character,Integer> tmp = new HashMap<>();
//            for (int j = 0; j < len - i + 1; j++) {
//                if (tmp.containsKey(chars[j])){
//                    tmp.put(chars[j],hashMap.get(chars[j]) + 1);
//                }
//                else {
//                    tmp.put(chars[j], 1);
//                }
//            }
//            if (tmp.containsKey('T') && hashMap.containsKey('R')){
//                if (hashMap.get('R') < tmp.get('T')) {
//                    continue;
//                }
//            }
//            if (tmp.containsKey('R') && hashMap.containsKey('T')){
//                if (hashMap.get('T') < tmp.get('R')) {
//                    continue;
//                }
//            }
//            if (tmp.containsKey('P') && hashMap.containsKey('I')){
//                if (hashMap.get('I') < tmp.get('P')) {
//                    continue;
//                }
//            }
//            if (tmp.containsKey('I') && hashMap.containsKey('P')){
//                if (hashMap.get('P') < tmp.get('I')) {
//                    continue;
//                }
//            }
//            ans++;
//        }
//        System.out.println(ans);
//    }


// 55 / 100
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int len = Integer.parseInt(sc.nextLine());
//        String str = sc.nextLine();
//        sc.close();
//        if (str.length() != len){
//            return;
//        }
//        int ans = 0;
//        char[] chars = str.toCharArray();
//        // 一层控制长度
//        for (int i = 1; i <= len; i ++) {
//            // 一层控制开始index
//
//            for (int j = 0; j < len - i + 1; j++) {
//                int tr = 0;
//                int ip = 0;
//                for (int k = j; k < j + i; k++) {
//                    if (chars[k] == 'T'){
//                        tr++;
//                    } else if (chars[k] == 'R'){
//                        tr--;
//                    } else if(chars[k] == 'I'){
//                        ip++;
//                    } else if (chars[k] == 'P'){
//                        ip--;
//                    } else {
//                        break;
//                    }
//                }
//                if (ip == 0 && tr == 0){
//                    ans++;
//                }
//            }
//        }
//        System.out.println(ans);
//    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String n = sc.nextLine();
//        String s = sc.nextLine();
//        int[][] pre = new int[s.length()+1][4];//记录TRIP个数
//        for(int i=0;i<s.length();i++) {
//            pre[i+1][0]=pre[i][0];
//            pre[i+1][1]=pre[i][1];
//            pre[i+1][2]=pre[i][2];
//            pre[i+1][3]=pre[i][3];
//            if(s.charAt(i)=='T') {
//                pre[i+1][0]=pre[i][0]+1;
//            }else if(s.charAt(i)=='R') {
//                pre[i+1][1]=pre[i][1]+1;
//            }else if(s.charAt(i)=='I') {
//                pre[i+1][2]=pre[i][2]+1;
//            }else if(s.charAt(i)=='P') {
//                pre[i+1][3]=pre[i][3]+1;
//            }
//        }
//        int ans = 0;
//        for(int i=0;i<s.length();i++) {
//            for(int j=i+1;j<s.length();j++) {
//                if(solution(pre[j+1][0]-pre[i][0],pre[j+1][1]-pre[i][1],pre[j+1][2]-pre[i][2],pre[j+1][3]-pre[i][3])) {
//                    ans++;
//                }
//            }
//        }
//        System.out.println(ans);
//    }
//
//    private static boolean solution(int T, int R, int I, int P) {
//        return T == R && I == P;
//    }

    // 小程现在手中有n个货物，对于第i个货物，有重量wi
    // 并且从n个货物中任意选出两个货物i,j，一定有  max(wi,wj)≥2×min(wi,wj)
    // 小程现在需要选出其中的m个进行运输，为了保证运输安全，这m个货物的重量总和需要处于闭区间[L,R]中
    // 小程现在希望你能帮忙计算出一共有多少种运输方案
    //
    //输入描述
    //第一行包含2个正整数n,m，分别表示货物总数和需要运输的货物数
    //第二行包含n个正整数wi，表示货物的重量
    //第三行包含2个正整数L,R，表示安全的货物重量之和
    //
    //数字间两两空格隔开
    //1≤n≤50,0≤m≤n
    //1≤wi≤1018,1≤L≤R≤1018

//    18 / 100
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int totalNum = scanner.nextInt();
//        int needNum = scanner.nextInt();
//        int[] weight = new int[totalNum];
//        for (int i = 0; i < totalNum; i++) {
//            weight[i] = scanner.nextInt();
//        }
//        int low = scanner.nextInt();
//        int high = scanner.nextInt();
//        HashSet<Integer> hashSet = new HashSet<>();
//        weightHelper(low,high,weight,hashSet,0,needNum,0);
//        System.out.println(ans);
//    }
//    public static Integer ans = 0;
//    public static void weightHelper(int low,int high,int[] weight,HashSet<Integer> index,int tmp,int target,int start){
//        if (tmp > high){
//            return;
//        }
//        if (target < 0){
//            return;
//        }
//        if (tmp >= low && tmp <=high && target == 0){
//            ans++;
//            return;
//        }
//        for (int i = start; i < weight.length; i++) {
//            if (!index.contains(i)){
//                index.add(i);
//                weightHelper(low, high, weight, index, tmp + weight[i],target - 1,start + 1);
//                index.remove(i);
//            }
//        }
//    }







    // 小红要求小明构造一个只由大写字母组成的字符串，使得该字符串包含恰好N[1]个属于字母“A”的区域、N[2]个属于字母“B”的区域、…、N[25]个属于字母“Y”的区域、
    // N[26]个属于字母“Z”的区域。属于某个字母的区域即字符串中连续的一段该字母，且与该段子串相邻的字符不再是该字母，
    // 例如字符串“BAACCCBBAB”包含恰好2个属于字母“A”的区域、3个属于字母“B”的区域，1个属于字母“C”的区域。
    // 由于小红要求构造的字符串可能不唯一，她还要求所构造的字符串长度最小，且在长度最小的前提下字典序最小。
    //
    //输入描述
    //第一行输入一个整数T（1<=T<=10），表示数据组数。
    //每组数据占一行，输入26个由空格隔开的整数N[1]到N[26]（0<=N[i]<=1000且至少存在一个N[i]不为0）。
    //
    //输出描述
    //每组数据输出占一行，如果我们要求构造的字符串不存在，则输出“!”（不包括引号），否则输出所构造的字符串。
    //
    //样例输入
    //6
    //1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    //0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    //1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    //2 1 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    //3 1 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    //4 1 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    //样例输出
    //A
    //!
    //BAB
    //CACACBC
    //ACACACBC
    //ABCACACAC

    // TODO : 1. 能否成功排序
    //  2. 判断先加哪个 if (2 * tempMax <= sum)
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = Integer.parseInt(scanner.nextLine());
//        for (int i = 0; i < n; i++) {
//            String[] s = scanner.nextLine().split(" ");
//            int[] nums = new int[26];
//            for (int j = 0; j < 26; j++) {
//                nums[j] = Integer.parseInt(s[j]);
//            }
//            int max = 0;        //数组最大值
//            int sum = 0;        //数组求和
//            for (int j = 0; j < 26; j++) {
//                if (nums[j] > max) {
//                    max = nums[j];
//                }
//                sum = sum + nums[j];
//            }
//            // 不能成功排序的情况
//            if (2 * max > sum + 1) {
//                System.out.println("!");
//            }
//            else {
//                StringBuilder sb = new StringBuilder();
//                int temp = -1;      //上次追加的字符下标
//                while (sum > 0) {
//                    for (int j = 0; j < 26; j++) {      //从A-Z遍历
//                        if (nums[j] > 0 && j != temp) {     //如果数量不等于零且上次不是这个数字则进入if，比如上次是'A'，这次就不能为'A'了
//                            int[] tempArr = new int[26];    //这行开始下面6行可以优化。。。。。
//                            int tempMax = 0;
//                            for (int k = 0; k < 26; k++) {
//                                if (k == j) tempArr[k] = nums[k] - 1;
//                                else tempArr[k] = nums[k];
//                                if (tempArr[k] > tempMax) tempMax = tempArr[k];
//                            }
////                            if (2 * tempMax > sum) continue;
////                            else {
////                                sb.append(Character.toChars('A'+j));
////                                nums[j] --;
////                                temp = j;
////                                sum --;
////                                break;
////                            }
//                            if (2 * tempMax <= sum) {
//                                sb.append(Character.toChars('A' + j));
//                                nums[j] --;
//                                temp = j;
//                                sum --;
//                                break;
//                            }
//                        }
//                    }
//                }
//                System.out.println(sb);
//            }
//        }
//    }
}
