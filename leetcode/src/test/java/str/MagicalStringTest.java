package str;

import java.util.Arrays;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/magical-string/">481. 神奇字符串</a>
 * 神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
 * <p>
 * 神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
 * s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s
 * 自身。
 * <p>
 * 给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 6
 * 输出：3
 * 解释：神奇字符串 s 的前 6 个元素是 “122112”，它包含三个 1，因此返回 3 。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * 1 <= n <= 105
 *
 * @author xuzhou
 * @since 2022/10/31 19:06
 */
public class MagicalStringTest {

    public int magicalString(int n) {
        // 0 1 2 3 4 5
        // 1 2 2 1 1 2 1 2 2
        // 0 1 1 2 2 3 4
        int total = 1;
        int[] s = new int[n + 1];
        s[0] = 1;
        s[1] = 2;
        int cur = 2;
        for (int i = 1, j = 1; i < n && j < n; i++) {
            for (int k = 0; k < s[i] && j < n; k++) {
                s[j++] = cur;
                if (cur == 1) {
                    total++;
                }
            }
            cur = cur ^ 3;
        }
        System.out.println("s = " + Arrays.toString(s));
        return total;
    }

    @Test
    public void magicalStringTest() {
        System.out.println(magicalString(6));
        System.out.println(magicalString(1));
        System.out.println(magicalString(4));
        System.out.println(magicalString(7));
    }
}
