package easy;

import org.junit.Test;
import utils.RandomUtils;

public class AddBinary {

    /**
     * <a href="https://leetcode.cn/problems/add-binary/">67. 二进制求和</a>
     * <p>给定两个二进制字符串，返回他们的和（用二进制表示）。
     * <p>输入为非空字符串且只包含数字 1 和 0。
     *
     * <p>示例 1:
     * <p>输入: a = "11", b = "1"
     * <p>输出: "100"
     *
     * <p>示例 2:
     * <p>输入: a = "1010", b = "1011"
     * <p>输出: "10101"
     * <p>
     * 11 11 -- 110
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        return add(a.toCharArray(), b.toCharArray());
    }

    private String add(char[] a, char[] b) {
        if (a.length < b.length) {
            return add(b, a);
        }
        int m = a.length;
        int n = b.length;
        char[] res = new char[m + 1];
        int i = m - 1, j = n - 1, p = m;
        int add = 0;
        while (i >= 0 && j >= 0) {
            int t = a[i] - '0' + b[j] - '0' + add;
            res[p] = (char) ('0' + t % 2);
            add = t / 2;
            p--;
            i--;
            j--;
        }
        while (i >= 0) {
            int t = a[i] - '0' + add;
            res[p] = (char) ('0' + t % 2);
            add = t / 2;
            p--;
            i--;
        }
        if (add > 0) {
            res[p] = '1';
            p--;
        }
        return new String(res, p + 1, p == 0 ? m : m + 1);
    }

    public String addBinary2(String a, String b) {
        // 保持a.length >= b.length
        if (a.length() < b.length()) {
            return addBinary(b, a);
        }
        StringBuilder sb = new StringBuilder();
        int add = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0; i--) {
            if (a.charAt(i) == '1') {
                add++;
            }
            if (j >= 0 && b.charAt(j--) == '1') {
                add++;
            }
            sb.append(add % 2);
            add /= 2;
        }
        if (add > 0) {
            sb.append(add);
        }
        return sb.reverse().toString();
    }

    public String addBinaryComparison(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int add = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0; i--) {
            if (a.charAt(i) == '1') {
                add++;
            }
            if (j >= 0 && b.charAt(j--) == '1') {
                add++;
            }
            sb.append(add % 2);
            add /= 2;
        }
        if (add > 0) {
            sb.append(add);
        }
        return sb.reverse().toString();
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            String a = generateRandomBitString();
            String b = generateRandomBitString();
            String s = addBinary(a, b);
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("s = " + s);
            System.out.println("---------------------");
        }
//        TestUtils.test(() -> addBinary("11", "1"), 1);
//        TestUtils.test(() -> addBinary("1010", "1011"), 1);
    }

    public String generateRandomBitString() {
        String ans = Integer.toBinaryString(RandomUtils.nextInt(Integer.MAX_VALUE));
//        boolean high = RandomUtils.nextBool();
//        if (high) {
//            ans = Integer.toBinaryString(RandomUtils.nextInt(Integer.MAX_VALUE)) + ans;
//        }
        return ans;
    }
}