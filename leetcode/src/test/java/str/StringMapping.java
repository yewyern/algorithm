package str;

/**
 * 假设 1-A,2-B,3-C
 * 给定字符串 111 ，可以得到以下结果，AAA,KA,AK
 * 从左到右匹配模式
 *
 * @author xuzhou
 * @date 2021/3/26 14:59
 */
public class StringMapping {

    public static void main(String[] args) {
//        test("111", 3);
        test("123", 3);
        test("127", 2);
        test("1279", 2);
        test("12712", 4);
    }

    private static void test(String s, int expected) {
        int map2 = strMap(s);
        if (map2 != expected) {
            System.out.println("s = " + s);
            System.out.println("expected = " + expected);
            System.out.println("map2 = " + map2);
        }
    }

    public static int strMap(String s) {
        return process(s.toCharArray(), 0);
    }

    public static int process(char[] s, int index) {
        // 如果跑完整个字符串，表示一种结果
        if (index == s.length) {
            return 1;
        }
        if (index > s.length || s[index] == '0') {
            // 0必须作为别的后缀，不能作为前缀
            return 0;
        }
        if (s[index] == '1') {
            return process(s, index + 1) + process(s, index + 2);
        }
        if (s[index] == '2') {
            return process(s, index + 1)
                + (index + 1 < s.length && s[index + 1] < '7' ? process(s, index + 2) : 0);
        }
        return process(s, index + 1);
    }
}
