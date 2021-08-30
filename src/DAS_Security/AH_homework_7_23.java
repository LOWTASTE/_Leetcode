package DAS_Security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AH_homework_7_23 {

//    现在有一个List<String>对象，内部存储了很多字符串。将他转换成一个Map<String,Integer>对象。value对应的是相同的key出现的次数，默认为1次。利用流式操作实现该需求。
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("2");
        stringList.add("3");
        stringList.add("3");
        stringList.add("3");
        System.out.println(ListToMap(stringList));
    }

    public static Map<String,Integer> ListToMap(List<String> stringList){
        Map<String, Integer> map = stringList.stream().collect(Collectors.toMap(k -> k, v -> 1, (k, v) -> ++k ));
        return map;
    }

}
