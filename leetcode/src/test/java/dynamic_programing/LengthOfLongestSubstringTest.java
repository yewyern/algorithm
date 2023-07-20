package dynamic_programing;

import org.junit.Test;
import utils.GenerateRandomString;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 48. 最长不含重复字符的子字符串</a>
 * <p>
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * s.length <= 40000
 * 注意：本题与主站 3 题相同：<a href="https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/">3. 无重复字符的最长子串</a>
 *
 * @author xuzhou
 * @since 2023/7/19 17:57
 */
public class LengthOfLongestSubstringTest {

    @Test
    public void test() {
        lengthOfLongestSubstring("abcabcbb");
        for (int i = 0; i < 100000; i++) {
            String s = GenerateRandomString.generateRandomLenString(1000);
            int res = lengthOfLongestSubstring(s);
            int res2 = lengthOfLongestSubstring(s);
            if (res != res2) {
                System.out.println("s = " + s);
                System.out.println("res = " + res);
                System.out.println("res2 = " + res2);
                break;
            }
        }
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] cs = s.toCharArray();
        // 优化: map改成数组
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int max = 0, start = 0;
        for (int i = 0; i < N; i++) {
            int last = map[cs[i]];
            // 优化1：不需要删除，直接判断start和last谁在前
            if (last >= 0 && start <= last) {
                max = Math.max(max, i - start);
                start = last + 1;
            } else {
                max = Math.max(max, i - start + 1);
            }
            map[cs[i]] = i;
        }
        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, start = 0;
        for (int i = 0; i < N; i++) {
            Integer last = map.getOrDefault(cs[i], -1);
            // 优化：不需要删除，直接判断start和last谁在前
            if (last >= 0 && start <= last) {
                max = Math.max(max, i - start);
                start = last + 1;
            } else {
                max = Math.max(max, i - start + 1);
            }
            map.put(cs[i], i);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, start = 0;
        for (int i = 0; i < N; i++) {
            Integer last = map.getOrDefault(cs[i], -1);
            for (int j = start; j <= last; j++) {
                map.remove(cs[j]);
            }
            if (last > 0) {
                max = Math.max(max, i - start);
                start = last + 1;
            } else {
                max = Math.max(max, i - start + 1);
            }
            map.put(cs[i], i);
        }
        return max;
    }

    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] cs = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (set.contains(cs[j])) {
                    max = Math.max(max, j - i);
                    break;
                }
                max = Math.max(max, j - i + 1);
                set.add(cs[j]);
            }
            set.clear();
        }
        return max;
    }
}
