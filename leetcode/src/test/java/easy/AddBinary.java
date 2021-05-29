package easy;

import utils.TestUtils;

class AddBinary {

    /**
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

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        TestUtils.test(() -> addBinary.addBinary("11", "1"), 1);
        TestUtils.test(() -> addBinary.addBinary("1010", "1011"), 1);
    }
}