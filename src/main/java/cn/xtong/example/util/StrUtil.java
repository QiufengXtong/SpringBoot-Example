package cn.xtong.example.util;

public class StrUtil {

    /**
     * 首字母转大写
     * @param str
     * @return
     */
    public static String firstLetterToUpper(String str) {
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String hello = StrUtil.firstLetterToUpper("hello");
        System.out.println(hello);
    }
}
