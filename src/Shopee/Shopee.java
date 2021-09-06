package Shopee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Shopee {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.char_and_num_return("xb 1 cc 5 dd 10 ee 2");
//        solution.Paired69_("669");
        int[] array = {2,1,5,2,3,3};
        System.out.println(solution.cost(array));

    }


}

class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * @param text_source string字符串 原始输入
     * @return string字符串
     * 90 %
     */
    public String char_and_num_return (String text_source) {
        // write code here
        if (text_source == null){
            return "";
        }
        String ans = "";
        StringBuilder stringBuilder = new StringBuilder();
        // 空格拆分
        String[] texts = text_source.split(" ");
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for (String str : texts){
            try {
                integers.add(Integer.parseInt(str));
            } catch (Exception e){
                strings.add(str);
            }
        }
        strings.forEach(s -> stringBuilder.append(s).append(" "));
        integers.sort(Integer::compareTo);
        integers.forEach(integer -> stringBuilder.append(integer).append(" "));

        ans = String.valueOf(stringBuilder);
        ans = ans.trim();
        System.out.println(ans);
        return ans;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * @param text_source string字符串 原始输入
     * @return string字符串
     *
     * 100 %
     */
    public String char_and_num_return_1 (String text_source) {
        // write code here
        String[] words = text_source.split(" ");
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
            public int compare(String a, String b){
                return (int)(Long.parseLong(a) - Long.parseLong(b));
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String word: words){
            if(word.charAt(0) > 57){    // 首字符的ascii码大于57一定是字母item
                sb.append(word).append(" ");      // 字母字符串直接加入stringbuilder
            }else
                pq.offer(word);       // 数字字符串加入小根堆
        }
        // 将数字字符串按升序出队
        while(!pq.isEmpty()) sb.append(pq.poll()).append(" ");
        return sb.toString().trim();
    }


// can not accept
    public String Paired69 (String S) {
        // write code here
        String tmp = S;
        while (tmp.contains("69")){
            tmp =  tmp.replace("69","");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (tmp.contains("6")){
            stringBuilder.append(S);
            stringBuilder.append("9".repeat(tmp.length()));
        } else {
            stringBuilder.append("6".repeat(tmp.length()));
            stringBuilder.append(S);
        }
        System.out.println(S);
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder);
    }

    public String Paired69_ (String S) {
        if (S == ""){
            return "";
        }
        // write code here
        String tmp = S;
        while (tmp.contains("69")){
            tmp =  tmp.replace("69","");
        }
        String ans = "";
        if (tmp.contains("6")){
            ans += S;
            ans += "9".repeat(tmp.length());
        } else {
            ans += "6".repeat(tmp.length());
            ans += S;
        }
        return ans;
    }

    public int cost (int[] array) {
        // write code here
        int n = array.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (array[i] >= array[i - 1]) {
                dec = 0;
                pre = array[i] == array[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }
}