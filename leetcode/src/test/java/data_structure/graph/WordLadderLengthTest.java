package data_structure.graph;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/word-ladder/">127. 单词接龙</a>
 * @author xuzhou
 * @since 2024/1/5 16:02
 */
public class WordLadderLengthTest {

    @Test
    public void test() {
        System.out.println(ladderLength("hit", "cog", Lists.newArrayList("hot","dot","dog","lot","log","cog")));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        String[] words = new String[wordList.size()];
        int size = 0;
        boolean hasEnd = false;
        for (String s : wordList) {
            if (s.equals(beginWord)) {
                continue;
            }
            if (s.equals(endWord)) {
                hasEnd = true;
            }
            words[size++] = s;
        }
        if (!hasEnd) {
            return 0;
        }

        String[] beginWords = new String[size];
        beginWords[0] = beginWord;
        int step = 1;
        int i = 0;
        int n = 1;
        while (i < n) {
            int k = n;
            while (i < k) {
                String begin = beginWords[i++];
                for (int j = 0; j < size;) {
                    String end = words[j];
                    if (onlyOneDiff(begin, end)) {
                        if (end.equals(endWord)) {
                            return step + 1;
                        }
                        beginWords[n++] = end;
                        words[j] = words[--size];
                        continue;
                    }
                    j++;
                }
            }
            step++;
        }
        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        wordList.remove(beginWord);
        List<String> curr = new LinkedList<>();
        curr.add(beginWord);
        int step = 1;
        while (!curr.isEmpty()) {
            List<String> next = new LinkedList<>();
            for (String start : curr) {
                Iterator<String> iterator = wordList.iterator();
                while (iterator.hasNext()) {
                    String s = iterator.next();
                    if (onlyOneDiff(s, start)) {
                        if (s.equals(endWord)) {
                            return step + 1;
                        }
                        next.add(s);
                        iterator.remove();
                    }
                }
            }
            curr = next;
            step++;
        }
        return 0;
    }

    private boolean onlyOneDiff(String a, String b) {
        int n = a.length();
        int d = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                d++;
                if (d > 1) {
                    return false;
                }
            }
        }
        return d == 1;
    }

}
