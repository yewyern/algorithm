package str;

/**
 * <a href="https://leetcode.cn/problems/find-the-longest-balanced-substring-of-a-binary-string">2609. 最长平衡子字符串</a>
 * @author xuzhou
 * @since 2023/11/8 11:19
 */
public class FindTheLongestBalancedSubstringTest {

    public int findTheLongestBalancedSubstring(String s) {
        int max = 0;
        int zero = 0;
        int one = 0;
        boolean isOne = true;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                if (isOne) {
                    zero = 0;
                    one = 0;
                    isOne = false;
                }
                zero++;
            } else {
                isOne = true;
                one++;
                max = Math.max(Math.min(zero, one) << 1, max);
            }
        }
        return max;
    }
}
