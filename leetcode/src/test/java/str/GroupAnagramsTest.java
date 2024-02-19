package str;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/group-anagrams/">49. 字母异位词分组</a>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 * @author xuzhou
 * @since 2022/11/13 18:58
 */
public class GroupAnagramsTest {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int hash = calc(str);
            map.computeIfAbsent(hash, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private static int calc(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int[] count = new int[26]; // 假设只包含小写字母
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        int hash = 0;
        for (int i : count) {
            hash = 31 * hash + i;
        }
        return hash;
    }

    public List<List<String>> groupAnagrams1(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(this::calc1)).values());
    }

    private String calc1(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<List<String>> groupAnagramsComparison(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(this::calc2)).values());
    }

    private String calc2(String s) {
        int[] a = new int[26];
        for (char c : s.toCharArray()) {
            a[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (a[i] > 0) {
                sb.append((char) i + 'a').append(a[i]);
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{""}));
        System.out.println(groupAnagrams(new String[]{"a"}));
    }
}
