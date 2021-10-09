package TP_LINK;

public class Solution_9_25_2021 {

//    public static int ans = -1;
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String strNum = scanner.nextLine();
//        int target = Integer.parseInt(scanner.nextLine());
//        scanner.close();
//        String[] charNum = strNum.split(" ");
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (String s : charNum){
//            arrayList.add(Integer.parseInt(s));
//        }
//        check(0,arrayList.size(),target,arrayList);
//        System.out.println(ans);
//
//    }
//    public static void check(int left,int right, int target,ArrayList<Integer> arrayList){
//        int middle = (left + right) / 2;
//        if (arrayList.get(middle) == target){
//            ans = middle;
//        }
//        if (left == right){
//            return;
//        }
//        if (arrayList.get(middle) > target){
//            check(left, middle, target, arrayList);
//        }
//        if (arrayList.get(middle) < target){
//            check(middle + 1, right, target, arrayList);
//        }
//    }



//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String strNum = scanner.nextLine();
//        scanner.close();
//        String[] charNum = strNum.split(" ");
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (String s : charNum){
//            arrayList.add(Integer.parseInt(s));
//        }
//        HashSet<Integer> integers = new HashSet<>();
//        for (Integer i: arrayList) {
//            if (integers.contains(i)){
//                System.out.println(i);
//                return;
//            } else {
//                integers.add(i);
//            }
//        }
//        System.out.println(-1);
//    }



//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        scanner.close();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 1; i < n + 1; i++) {
//            stringBuilder.append(i);
//        }
//        if (m > n){
//            System.out.println(-1);
//            return;
//        }
//        String s = stringBuilder.toString();
//        System.out.println(s);
//        System.out.println(s.charAt(s.length() - m));
//    }
}
