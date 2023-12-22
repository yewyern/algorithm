package data_structure.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/word-pattern">290. 单词规律</a>
 *
 * @author xuzhou
 * @since 2023/12/22 10:03
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String s) {
        Set<String> appearedWords = new HashSet<>();
        String[] map = new String[26];
        String[] words = s.split(" ");
        char[] chars = pattern.toCharArray();
        if (chars.length != words.length) {
            return false;
        }
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            int idx = chars[i] - 'a';
            if (map[idx] == null) {
                if (appearedWords.contains(words[i])) {
                    return false;
                }
                map[idx] = words[i];
                appearedWords.add(words[i]);
            } else if (!map[idx].equals(words[i])) {
                return false;
            }
        }
        return true;
    }
}
