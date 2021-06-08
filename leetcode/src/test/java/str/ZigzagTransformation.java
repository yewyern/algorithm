package str;

import org.junit.Test;

/**
 * <p>将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * <p>比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * <p>L   C   I   R
 * <p>E T O E S I I G
 * <p>E   D   H   N
 * <p>之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * <p>请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * <p>string convert(string s, int numRows);
 * <p>示例 1:
 * <p>
 * <p>输入: s = "LEETCODEISHIRING", numRows = 3
 * <p>输出: "LCIRETOESIIGEDHN"
 * <p>示例 2:
 * <p>
 * <p>输入: s = "LEETCODEISHIRING", numRows = 4
 * <p>输出: "LDREOEIIECIHNTSG"
 * <p>解释:
 * <p>
 * <p>L     D     R
 * <p>E   O E   I I
 * <p>E C   I H   N
 * <p>T     S     G
 *
 * @author zhou.xu
 * @date 2020/8/4 17:35
 */
public class ZigzagTransformation {

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows == 1) {
            return s;
        }
        char[] cs = s.toCharArray();
        char[] res = new char[cs.length];
        for (int i = 0, idx = 0; i < numRows; i++) {
            for (int j = i, k = numRows - i - 1; j < cs.length; ) {
                res[idx++] = cs[j];
                if (k == 0) {
                    k = numRows - k - 1;
                }
                j += 2 * k;
                k = numRows - k - 1;
            }
        }
        return String.valueOf(res);
    }

    public void test(String s, int numRows, String expected) {
        String r = convert(s, numRows);
        System.out.println("s = " + s);
        System.out.println("n = " + numRows);
        System.out.println("e = " + expected);
        System.out.println("r = " + r);
        System.out.println(expected.equals(r));
        System.out.println("------------------");
    }

    @Test
    public void test() {
        test("LEETCODEISHIRING", 3, "LCIRETOESIIGEDHN");
        test("LEETCODEISHIRING", 4, "LDREOEIIECIHNTSG");
        test("A", 1, "A");
    }
}
