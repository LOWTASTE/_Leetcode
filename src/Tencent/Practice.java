package Tencent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Practice {
}

class UnionFind {
    public int[] parent = new int[10000001];
    // 附加
    public int[] personNums = new int[10000001];
    public UnionFind(){
        for(int i = 1; i <= 10000000; i++){
            parent[i] = i;
            personNums[i] = 1;
        }
    }

    public int find(int x) {
        while(x != parent[x]){
            // 路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return;
        parent[rootY] = rootX;
        // 附加
        personNums[rootX] += personNums[rootY];
    }
}

//现在有10^7个用户，编号为1 - 10^7，现在已知有m对关系，每一对关系给你两个数x和y，代表编号为x的用户和编号为y的用户是在一个圈子中，
//例如：A和B在一个圈子中，B和C在一个圈子中，那么A,B,C就在一个圈子中。现在想知道最多的一个圈子内有多少个用户。

class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());
            UnionFind uf = new UnionFind();
            for(int i = 0; i < n; i++){
                String[] params = br.readLine().trim().split(" ");
                int x = Integer.parseInt(params[0]);
                int y = Integer.parseInt(params[1]);
                uf.union(x, y);
            }
            int max = 0;
            for(int i = 1; i < uf.personNums.length; i++)
                max = Math.max(max, uf.personNums[i]);
            System.out.println(max);
        }
    }
}

//输入一个字符串 s，s 由小写英文字母组成，保证 s 长度小于等于 5000 并且大于等于 1。
//在 s 的所有不同的子串中，输出字典序第 k 小的字符串。
//字符串中任意个连续的字符组成的子序列称为该字符串的子串。
//字母序表示英文单词在字典中的先后顺序，即先比较第一个字母，
// 若第一个字母相同，则比较第二个字母的字典序，依次类推，则可比较出该字符串的字典序大小。

/**
 * 首先我们需要一个有序的容器来存放子串，由于需要保证子串各不相同，因此还需要一个集合来对子串进行去重。
 * 由于子串的数量可能非常庞大，我们做一些优化，考虑到字典序第k小的子串长度一定不会超过k，
 * 因此在对长度进行遍历时，没有必要将候选的长度尝试到k以上。
 * 同时，我们还希望这个有序的容器能够以O(1)的复杂度获取到字典序第k小的子串，
 * 这里采用大根堆作为此有序容器，并将堆的大小控制为k，当堆中元素数量小于k时，直接往堆中插入元素；当堆中元素数量达到了k时，
 * 为了保证堆中是字典序最小的k个子串，我们仅在待入队子串的字典序小于堆顶子串时将堆顶子串出队，然后将待入队子串插入。
 * 如此一来，遍历完所有的子串后，直接取出堆顶元素即为字典序第k小的子串。
 */
class Solution2 {
    public static void main(String[] args) throws IOException {
        // 处理输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int k = Integer.parseInt(br.readLine());
        // 字典去重
        HashSet<String> set = new HashSet<>();
        PriorityQueue<String> queue = new PriorityQueue<>((s1, s2) -> s2.compareTo(s1));
        for(int len = 1; len <= k; len ++){
            for(int i = 0; i < str.length() - len + 1; i++){
                String substr = str.substring(i, i + len);
                if(!set.contains(substr)){
                    if(queue.size() < k){
                        queue.offer(substr);
                    }else{
                        if(substr.compareTo(queue.peek()) < 0){
                            queue.poll();
                            queue.offer(substr);
                        }
                    }
                    set.add(substr);
                }
            }
        }
        System.out.println(queue.peek());
    }
}

// y^2 = 2Ax 与 y= Bx + C 封闭图形的面积
class Solution3 {
    private static double A = 0;
    private static double B = 0;
    private static double C = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            String[] params = br.readLine().split(" ");
            A = Double.parseDouble(params[0]);
            B = Double.parseDouble(params[1]);
            C = Double.parseDouble(params[2]);
            if(A*A - 2*A*B*C < 0){
                System.out.println(0);
            }else{
                double lb = (A - Math.sqrt(A*A - 2*A*B*C)) / B;
                double ub = (A + Math.sqrt(A*A - 2*A*B*C)) / B;
                System.out.println(f(ub) - f(lb));
            }
        }
    }

    private static double f(double y) {
        return -y*y*y / (6*A) + y*y /(2*B) - C*y/B;
    }
}

// 实现队列
class Solution4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-- > 0){
            int n = Integer.parseInt(br.readLine().trim());
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 0; i < n; i++){
                String command = br.readLine();
                if(command.startsWith("PUSH")){
                    String[] params = command.split(" ");
                    int elem = Integer.parseInt(params[1]);
                    queue.offer(elem);
                }else if(command.equals("TOP")){
                    if(queue.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(queue.peek());
                }else if(command.equals("POP")){
                    if(queue.isEmpty())
                        System.out.println(-1);
                    else
                        queue.poll();
                }else if(command.equals("SIZE")){
                    System.out.println(queue.size());
                }else if(command.equals("CLEAR"))
                    queue.clear();
            }
        }
    }
}


//    牛牛最近迷上了《找不同》这个小游戏，在这个游戏中，每一轮，会给你两张很相似的照片，需要你指出其中的所有不同之处。
//    这一天，牛牛玩着这个游戏，路过牛妹身旁，偶然间注意到牛妹正对着很多数字发呆。
//    牛牛瞄了一眼数字，随手指了一个数字，说这个数字在这些数中只出现了一次。经过牛妹人工检验，发现牛牛说得对。
//    牛妹非常好奇牛牛的这个新能力，觉得是因为牛牛玩《找不同》玩多了，于是对于这类不同于其它的部分特别敏感。
//    为了进一步检测牛牛的能力，牛妹决定拟定一份问卷，让牛牛回答，每份问卷中有若干道题目，每道题目含有若干个数字，需要牛牛快速回答出，每道题所给的数字中，
//    最小的一个只出现了一次的数字是什么？
//    由于题量很多，显然不能让牛妹人工核对答案，于是向你求助，希望你能给予牛妹帮助。
class Solution5{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int testNum = scanner.nextInt();
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        for (int i = 0; i < testNum; i++) {
            int n = scanner.nextInt();
            ArrayList<Integer> integers = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                integers.add(scanner.nextInt());
            }
            arrayLists.add(integers);
        }
        for(ArrayList<Integer> integers : arrayLists){
            // integers.sort(Integer::compareTo);
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (Integer integer : integers) {
                if (!hashMap.containsKey(integer)) {
                    hashMap.put(integer, 1);
                } else {
                    hashMap.put(integer, hashMap.get(integer) + 1);
                }
            }
            int ans = Integer.MAX_VALUE;
            for (Map.Entry<Integer,Integer> entry : hashMap.entrySet()){
                if (entry.getValue() == 1){
                    ans = Math.min(ans,entry.getKey());
                }
            }
            if (ans == Integer.MAX_VALUE){
                System.out.println(-1);
            } else {
                System.out.println(ans);
            }
        }
    }
}


//给定一棵包含n >= 3个节点且以节点1为根节点的树。
//你需要从中选出个不同的节点，使得其两两之间的最短距离之和最大，并求出这个最大和。
//定义树上两点之间的最短距离为这两点之间的简单路径所经过的边的数量。
//class Solution6 {
//    static Node[] tree;
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        tree = new Node[n];
//        for (int i = 0; i < n; i++) {
//            tree[i] = new Node();
//        }
//        for (int i = 1; i < n; i++) {
//            int parent = scanner.nextInt();
//            tree[parent-1].addChild(tree[i]);
//        }
//        int[] max3 = calMaxLength(tree[0]);
//        int sumOfMax3 = 0;
//        for (int i = 0; i < 3;i++){
//            sumOfMax3 += max3[i];
//        }
//        System.out.println(sumOfMax3*2);
//    }
//    public static int[] calMaxLength(Node root){
//        int maxLsum = 0;
//        int maxdeep = 0;
//        int[] ret = new int[4];
//        int[] c2MaxSum1 = new int[4];
//        int[] c3MaxSum = new int[4];
//        if(root.child.isEmpty() == false){
//            for (Node chi: root.child){
//                int[] temp = calMaxLength(chi);
//                if(maxdeep < temp[3]){
//                    maxdeep = temp[3];
//                }
//                int c3minidex = -1;//该节点为三个节点的共同交点，计算并与已有最大值比较
//                for(int i = 0;i < 3;i++) {
//                    if(c3minidex == -1 || c3MaxSum[i] < c3MaxSum[c3minidex] ){
//                        c3minidex = i;
//                    }
//                }
//                if(c3MaxSum[c3minidex] < temp[3]) {
//                    c3MaxSum[c3minidex] = temp[3];
//                    int c3temp = 0;
//                    for(int i = 0;i < 3;i++){
//                        c3temp += c3MaxSum[i];
//                    }
//                    if(c3temp > maxLsum){
//                        maxLsum = c3temp;
//                        ret = c3MaxSum;
//                    }
//                }
//                int c1temp = 0;//该节点不是共同交点，计算并与已有最大值比较
//                for (int i = 0;i < 3;i++){
//                    c1temp += temp[i];
//                }
//                if(maxLsum < c1temp){
//                    maxLsum = c1temp;
//                    ret = temp;
//                }
//                int[] templ = Arrays.copyOf(temp,3);//该节点为两个节点的共同交点，计算并与已有最大值比较
//                Arrays.sort(templ);
//                int[] templmax = Arrays.copyOf(c2MaxSum1,3);
//                Arrays.sort(templmax);
//                int maxoftwo = templ[1] > templmax[1] ? templ[1] : templmax[1];
//                if(temp[3] + c2MaxSum1[3] + maxoftwo > maxLsum){
//                    maxLsum = temp[3] + c2MaxSum1[3] + maxoftwo;
//                    ret = new int[4];
//                    ret[0] = temp[3];
//                    ret[1] = c2MaxSum1[3];
//                    ret[2] = maxoftwo;
//                }
//                if(c2MaxSum1[3] < temp[3] || (c2MaxSum1[3] == temp[3] && templ[1] > templmax[1])){
//                    c2MaxSum1 = temp;
//                }
//            }
//        }
//        ret[3] = maxdeep+1;
//        return ret;
//    }
//}
//class Node{
//    List<Node> child = new ArrayList<>();
//    public void addChild(Node child){
//        this.child.add(child);
//    }
//}

//class LRU<K,V> {
//    private static final float hashLoadFactory = 0.75f;
//    private LinkedHashMap<K,V> map;
//    private int cacheSize;
//
//    public LRU(int cacheSize) {
//        this.cacheSize = cacheSize;
//        int capacity = (int)Math.ceil(cacheSize / hashLoadFactory) + 1;
//        map = new LinkedHashMap<K,V>(capacity, hashLoadFactory, true){
//            private static final long serialVersionUID = 1;
//
//            @Override
//            protected boolean removeEldestEntry(Map.Entry eldest) {
//                return size() > LRU.this.cacheSize;
//            }
//        };
//    }
//
//    public synchronized V get(K key) {
//        return map.get(key);
//    }
//
//    public synchronized void put(K key, V value) {
//        map.put(key, value);
//    }
//
//    public synchronized void clear() {
//        map.clear();
//    }
//
//    public synchronized int usedSize() {
//        return map.size();
//    }
//
//    public void print() {
//        for (Map.Entry<K, V> entry : map.entrySet()) {
//            System.out.print(entry.getValue() + "--");
//        }
//        System.out.println();
//    }
//}






