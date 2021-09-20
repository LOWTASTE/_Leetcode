package Pinduoudo;

public class Practice {

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int target = scanner.nextInt();
//
//        if (target >= 45){
//            System.out.println(-1);
//            return;
//        }
//        int num = 9;
//        while (target > 0){
//            if (target <= num){
//                break;
//            }
//            target -= num;
//            num -= 1;
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(target);
//        for (int i = num + 1; i <= 9; i++) {
//            stringBuilder.append(i);
//        }
//        System.out.println(stringBuilder.toString());
//    }

//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        int N = scanner.nextInt();
//        //每个数位各不相同且各个数位之和等于N——1+2+3+4+5+6+7+8+9 = 45，如果大于45一定会重复
//        if(N > 45){
//            System.out.println(-1);
//            return;
//        }
//        //如果N<10，可以直接返回数字本身
//        if(N < 10){
//            System.out.println(N);
//            return;
//        }
//        //右侧数位越大，越能保证左侧数位越小，越能保证整个数最小
//        int nums = 0;
//        int digit = 0;
//        for(int i = 9; i>0; i--){
//            if(N != 0 && i <= N){
//                N -= i;
//                nums += (int)Math.pow(10,digit)*i;
//                digit++;
//            }
//        }
//        System.out.println(nums);
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int len = Integer.parseInt(scanner.nextLine());
//        String str1 = scanner.nextLine();
//        String str2 = scanner.nextLine();
//        if(str1.length() != str2.length()){
//            return;
//        }
//        char[] char1 = str1.toCharArray();
//        Arrays.sort(char1);
//        char[] char2 = str2.toCharArray();
//        Arrays.sort(char2);
//        int ans = 0;
//        for (int i = 0; i < len; i++) {
//            System.out.println(char1[i] - char2[i]);
//            ans += Math.abs(char1[i] - char2[i]);
//        }
//        System.out.println(ans);
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int treeNum = scanner.nextInt();
//        int m = scanner.nextInt();
//        if (m == 0){
//            return;
//        }
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (int i = 0; i < treeNum; i++) {
//            arrayList.add(scanner.nextInt());
//        }
//        scanner.close();
//        int[] pre = new int[treeNum];
//        int ans = 0;
//        for (int i = 0; i < treeNum; i++) {
//            pre[i] = (i == 0 ? 0 : pre[i - 1]) + arrayList.get(i);
//        }
//        for (int i = 0; i < treeNum; i++) {
//            for (int j = i; j < treeNum; j++) {
//                int total = 0;
//                if (i == j){
//                    total = arrayList.get(i);
//                } else {
//                    total = pre[j] - (i == 0? 0 : pre[i - 1]);
//                }
//                if (total % m == 0) {
//                    ans++;
//                }
//            }
//        }
//        System.out.println(ans);
//    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] params = br.readLine().split(" ");
//        int n = Integer.parseInt(params[0]);
//        int m = Integer.parseInt(params[1]);
//        int[] map = new int[m];       // 除以m的余数为0~m-1
//        params = br.readLine().split(" ");
//        int[] A = new int[n];
//        map[0] = 1;
//        long culSum = 0L;
//        long count = 0L;
//        for(int i = 0; i < n; i++) {
//            A[i] = Integer.parseInt(params[i]);
//            culSum += A[i];
//            int remain = (int)(culSum % m);
//            count += map[remain];
//            map[remain] ++;
//        }
//        System.out.println(count);
//    }
}
