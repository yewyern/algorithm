package str;

/**
 * <a href="https://leetcode.cn/problems/add-strings/description/">415. 字符串相加</a>
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * <p>
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 示例 1：
 * <p>
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 示例 2：
 * <p>
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 示例 3：
 * <p>
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 * <p>
 * 提示：
 * <p>
 * 1 <= num1.length, num2.length <= 10^4
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 *
 * @author xuzhou
 * @since 2023/7/17 11:18
 */
public class AddStringTest {

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        char[] cs1 = num1.toCharArray();
        char[] cs2 = num2.toCharArray();
        int m = cs1.length - 1, n = cs2.length - 1;
        int sum = 0;
        while (m >= 0 || n >= 0) {
            sum += m >= 0 ? cs1[m] - '0' : 0;
            sum += n >= 0 ? cs2[n] - '0' : 0;
            sb.append(sum > 9 ? sum - 10 : sum);
            sum = sum > 9 ? 1 : 0;
            m--;
            n--;
        }
        if (sum == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}
