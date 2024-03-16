package str;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/">1790. 仅执行一次字符串交换能否使两个字符串相等</a>
 * <p>
 * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 * <p>
 * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s1 = "bank", s2 = "kanb" 输出：true 解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank" 示例 2：
 * <p>
 * 输入：s1 = "attack", s2 = "defend" 输出：false 解释：一次字符串交换无法使两个字符串相等 示例 3：
 * <p>
 * 输入：s1 = "kelb", s2 = "kelb" 输出：true 解释：两个字符串已经相等，所以不需要进行字符串交换 示例 4：
 * <p>
 * 输入：s1 = "abcd", s2 = "dcba" 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s1.length, s2.length <= 100 s1.length == s2.length s1 和 s2 仅由小写英文字母组成
 *
 * @author zhou.xu
 * @since 2022/10/11 22:59
 */
public class CheckIfOneStringSwapCanMakeStringsEqualTest {

    public boolean areAlmostEqual(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        int count = 0;
        int[] indexes = new int[2];
        // 当2个字符串有且只有2个不同字符不同，且这2个字符相等时，即表明可以互换完成
        for (int i = 0; i < cs1.length; i++) {
            if (cs1[i] != cs2[i]) {
                if (count >= indexes.length) {
                    return false;
                }
                indexes[count] = i;
                count++;
            }
        }
        return count == 0
            || (count == 2 && cs1[indexes[0]] == cs2[indexes[1]] && cs1[indexes[1]] == cs2[indexes[0]]);
    }

    public boolean areAlmostEqualComparison(String s1, String s2) {
        // 暴力实现
        if (s1.equals(s2)) {
            return true;
        }
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        for (int i = 0; i < cs1.length - 1; i++) {
            for (int j = i + 1; j < cs1.length; j++) {
                swap(cs1, i, j);
                if (equals(cs1, cs2)) {
                    return true;
                }
                swap(cs1, i, j);
            }
        }
        return false;
    }

    private void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    private boolean equals(char[] cs1, char[] cs2) {
        for (int i = 0; i < cs1.length; i++) {
            if (cs1[i] != cs2[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        test("bank", "kanb", true);
        test("attack", "defend", false);
        test("kelb", "kelb", true);
        test("abcd", "dcba", false);
        test("aa", "bb", false);
        test("aaa", "bbb", false);
    }

    private void test(String s1, String s2, boolean expectedResult) {
        boolean b = areAlmostEqual(s1, s2);
        if (b == expectedResult) {
            System.out.println(s1 + ", " + s2 + ", 预期结果：" + expectedResult + ", 实际结果：" + b + ", 通过！");
        } else {
            System.out.println(s1 + ", " + s2 + ", 预期结果：" + expectedResult + ", 实际结果：" + b + ", 不通过！");
        }
    }
}
