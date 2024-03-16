package sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/substring-with-concatenation-of-all-words">30. 串联所有单词的子串</a>
 * @author xuzhou
 * @since 2023/12/21 15:44
 */
public class SubstringWithConcatenationOfAllWordsTest {

    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length();
        int m = words.length;
        int k = words[0].length();
        if (n < m * k) {
            return new ArrayList<>();
        }
        Map<String, Integer> wordCount = new HashMap<>(); // 统计每个单词个数
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        String[] smap = new String[n - k]; // 每个位置拆分成单词
        for (int i = 0; i < n - k; i++) {
            smap[i] = s.substring(i, i + k);
        }
        for (int start = 0; start < k; start++) {
            // 相同偏移量的一起计算
            int l = start, r = start;
            Map<String, Integer> canUse = new HashMap<>(wordCount);
            while (r < n - k) {
                // todo
                // 左边界右移
                while (l < r && canUse.getOrDefault(smap[r], 0) == 0) {
                    canUse.put(smap[l], canUse.getOrDefault(smap[l], 0) + 1);
                    l += k;
                }
            }
        }
        return null;
    }


}
