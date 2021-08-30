package DAS_Security;

import java.util.HashMap;

public class AH_homework_7_20 {
    public static void printFirstLetter(String str) {
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            if (hashMap.containsKey(str.charAt(i))){
                int value = hashMap.get(str.charAt(i));
                hashMap.put(str.charAt(i), value+1);
            }else {
                hashMap.put(str.charAt(i), 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (hashMap.get(str.charAt(i)) == 1){
                System.out.println(str.charAt(i));
                break;
            }
        }
    }

    public static void main(String[] args) {
        printFirstLetter("test_txt");
    }
}
