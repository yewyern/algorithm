package str;

/**
 * <a href="https://leetcode.cn/problems/custom-sort-string/">791. 自定义字符串排序</a>
 * 给定两个字符串 order 和 s 。order 的所有单词都是 唯一 的，并且以前按照一些自定义的顺序排序。
 *
 * 对 s 的字符进行置换，使其与排序的 order 相匹配。更具体地说，如果在 order 中的字符 x 出现字符 y 之前，那么在排列后的字符串中， x 也应该出现在 y 之前。
 *
 * 返回 满足这个性质的 s 的任意排列 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: order = "cba", s = "abcd"
 * 输出: "cbad"
 * 解释:
 * “a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
 * 因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。
 * 示例 2:
 *
 * 输入: order = "cbafg", s = "abcd"
 * 输出: "cbad"
 *
 *
 * 提示:
 *
 * 1 <= order.length <= 26
 * 1 <= s.length <= 200
 * order 和 s 由小写英文字母组成
 * order 中的所有字符都 不同
 *
 * @author xuzhou
 * @since 2022/11/13 16:01
 */
public class CustomSortStringTest {

    public String customSortString(String order, String s) {
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            int cc = c - 'a';
            while (bucket[cc] > 0) {
                sb.append(c);
                bucket[cc]--;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (bucket[i] > 0) {
                char c = (char) (i + 'a');
                for (int j = 0; j < bucket[i]; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
