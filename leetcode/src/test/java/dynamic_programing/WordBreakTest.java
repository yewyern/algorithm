package dynamic_programing;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/word-break">139. 单词拆分</a>
 * @author xuzhou
 * @since 2024/1/10 16:26
 */
public class WordBreakTest {

    @Test
    public void test() {
        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] processed = new boolean[s.length()];
        return process(s, wordDict, 0, processed);
    }

    public boolean process(String s, List<String> wordDict, int offset, boolean[] processed) {
        if (offset >= s.length()) {
            return true;
        }
        if (processed[offset]) {
            return false;
        }
        for (String word : wordDict) {
            if (s.startsWith(word, offset)) {
                if (process(s, wordDict, offset + word.length(), processed)) {
                    return true;
                }
            }
        }
        processed[offset] = true;
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        return process2(s, new HashSet<>(wordDict), 0);
    }

    public boolean process2(String s, Set<String> wordDict, int start) {
        int n = s.length();
        if (start == n) {
            return true;
        }
        for (int i = 1; i <= 20; i++) {
            if (start + i > n) {
                break;
            }
            String word = s.substring(start, start + i);
            if (wordDict.contains(word) && process2(s, wordDict, start + i)) {
                return true;
            }
        }
        return false;
    }
}
