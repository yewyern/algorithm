package sliding_window;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/VabMRr/description/">LCR 015. 找到字符串中所有字母异位词</a>
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 变位词 指字母相同，但排列不同的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的变位词。
 * 示例 2：
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的变位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * <p>
 * <p>
 * 注意：本题与主站 438 题相同： <a href="https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/">438. 找到字符串中所有字母异位词</a>
 *
 * @author xuzhou
 * @since 2023/9/7 17:25
 */
public class FindAnagramsTest {

    @Test
    public void test() {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }


    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        char[] cs = s.toCharArray();
        int[] pCount = new int[26];
        int pMark = calcPMark(p.toCharArray(), pCount);
        int[] sCount = new int[26];
        int sMark = 0;
        int pn = p.length();
        int n = cs.length;
        for (int l = 0, r = 0; r < n; r++) {
            int c = cs[r] - 'a';
            sCount[c]++;
            sMark = calcSMark(sMark, c, sCount, pCount);
            if (r >= pn) {
                c = cs[l] - 'a';
                sCount[cs[l] - 'a']--;
                sMark = calcSMark(sMark, c, sCount, pCount);
                l++;
            }
            if (sMark == pMark) {
                ans.add(l);
            }
        }
        return ans;
    }

    private int calcPMark(char[] cs, int[] count) {
        int mark = 0;
        for (char c : cs) {
            int p = c - 'a';
            count[p]++;
            mark |= 1 << p;
        }
        return mark;
    }

    private int calcSMark(int sMark, int i, int[] sCount, int[] pCount) {
        if (pCount[i] > 0 && sCount[i] == pCount[i]) {
            sMark |= 1 << i;
        } else if ((sMark & (1 << i)) != 0) {
            sMark ^= 1 << i;
        }
        return sMark;
    }
}
