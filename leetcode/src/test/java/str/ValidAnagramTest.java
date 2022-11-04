package str;

/**
 * <a href="https://leetcode.cn/problems/valid-anagram/">242. 有效的字母异位词</a>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, t.length <= 5 * 10^4
 * s 和 t 仅包含小写字母
 * <p>
 * <p>
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * @author zhou.xu
 * @since 2022/11/4 21:53
 */
public class ValidAnagramTest {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[128];
        // toCharArray遍历比charAt快
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        for (char c : t.toCharArray()) {
            count[c]--;
        }
        for (int j : count) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }
}
