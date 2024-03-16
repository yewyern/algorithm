package str;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/maximum-repeating-substring/">1668. 最大重复子字符串</a>
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
 *
 * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：sequence = "ababc", word = "ab"
 * 输出：2
 * 解释："abab" 是 "ababc" 的子字符串。
 * 示例 2：
 *
 * 输入：sequence = "ababc", word = "ba"
 * 输出：1
 * 解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
 * 示例 3：
 *
 * 输入：sequence = "ababc", word = "ac"
 * 输出：0
 * 解释："ac" 不是 "ababc" 的子字符串。
 *
 * 提示：
 *
 * 1 <= sequence.length <= 100
 * 1 <= word.length <= 100
 * sequence 和 word 都只包含小写英文字母。
 *
 * @author xuzhou
 * @since 2022/11/3 9:51
 */
public class MaximumRepeatingSubstringTest {

    public int maxRepeating(String sequence, String word) {
        int max = 0;
        for (int i = 0; i < sequence.length(); i++) {
            int l = sequence.indexOf(word, i);
            if (l >= 0) {
                int count = 1;
                int j = l + word.length();
                while (sequence.startsWith(word, j)) {
                    j += word.length();
                    count++;
                }
                i = l + 1;
                max = Math.max(max, count);
            }
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(maxRepeating("abcdabc", "abcd")); // 1
        System.out.println(maxRepeating("ababc", "ab")); // 2
        System.out.println(maxRepeating("ababc", "ba")); // 1
        System.out.println(maxRepeating("ababc", "ac")); // 0
        System.out.println(maxRepeating("aaabc", "a")); // 3
        System.out.println(maxRepeating("aaabaaaac", "a")); // 4
        System.out.println(maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba")); // 5
    }
}
