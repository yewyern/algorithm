package dynamic_programing;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/longest-valid-parentheses/">32. 最长有效括号</a>
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 * <p>
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
public class LongestValidParenthesesTest {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N];
        dp[0] = 0;
        // dp[i]表示当前位置往前最长有效括号长度
        // 如果是(，直接是0
        // 如果是)，找他左边能配对的那个，找左边的方法，i - dp[i - 1] - 1，有可能越界
        // 还要看下它左边的位置是不是能连起来
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (str[i] == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    // ()(())
                    // 020026
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                    max = Math.max(max, dp[i]);
                }
            }
        }
//        System.out.println("dp = " + Arrays.toString(dp));
        return max;
    }

    @Test
    public void test() {
        int parentheses = longestValidParentheses("()(())");
        System.out.println("parentheses = " + parentheses);
        int parentheses2 = longestValidParentheses(")()(())");
        System.out.println("parentheses2 = " + parentheses2);
        int parentheses3 = longestValidParentheses(")))(())");
        System.out.println("parentheses3 = " + parentheses3);
    }
}
