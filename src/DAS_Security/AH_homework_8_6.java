package DAS_Security;

public class AH_homework_8_6 {
    public static void fun2() {// 字符串转换为ASCII码
        String s = "我要死了！";// 字符串
        char[] chars = s.toCharArray(); // 把字符中转换为字符数组
        System.out.println("\n\n汉字 ASCII\n");
        for (int i = 0; i < chars.length; i++) {// 输出结果
            System.out.println(" " + chars[i] + " " + (int) chars[i]);
        }
    }
//    public static void main(String[] args) {
//            String content = "1234"; // 需要加密的字符
//            String key = "abc"; // 密钥
//            byte[] result = encryption(content, key);
//            System.out.println("1234加密后的值：" + new String(result));
//            System.out.println("---------------");
//            System.out.println("1234解密后的值：" + new String(decipher(new String(result), key)));
//        }

    public static byte[] encryption(String content, String key) {
        byte[] contentBytes = content.getBytes();
        byte[] keyBytes = key.getBytes();
        byte dkey = 0;
        for (byte b : keyBytes) {
            dkey ^= b;
        }
        byte salt = 0; // 随机盐值
        byte[] result = new byte[contentBytes.length];
        for (int i = 0; i < contentBytes.length; i++) {
            salt = (byte) (contentBytes[i] ^ dkey ^ salt);
            result[i] = salt;
        }
        return result;
    }

    public static byte[] decipher(String content, String key) {
        byte[] contentBytes = content.getBytes();
        byte[] keyBytes = key.getBytes();
        byte dkey = 0;
        for (byte b : keyBytes) {
            dkey ^= b;
        }
        byte salt = 0; // 随机盐值
        byte[] result = new byte[contentBytes.length];
        for (int i = contentBytes.length - 1; i >= 0; i--) {
            if (i == 0) {
                salt = 0;
            } else {
                salt = contentBytes[i - 1];
            }
            result[i] = (byte) (contentBytes[i] ^ dkey ^ salt);
        }

        return result;
    }

    public static void main(String[] args) {
        byte[] result = encryption("数据", "key值");
        System.out.println("数据加密后的值：" + new String(result));
        System.out.println("---------------");
        System.out.println("key解密后的值：" + new String(decipher(new String(result), "key值")));
        fun2();
    }

}
