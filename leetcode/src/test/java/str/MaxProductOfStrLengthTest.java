package str;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/maximum-product-of-word-lengths">318. 最大单词长度乘积</a>
 *
 * @author xuzhou
 * @since 2023/11/6 14:14
 */
public class MaxProductOfStrLengthTest {

    private static final int FULL_CHARS = (1 << 26) - 1;

    public int maxProduct(String[] words) {
        int n = words.length;
        int max = 0;
        int[] marks = toMarks(words, n);
        for (int i = 0; i < n - 1; i++) {
            if (marks[i] == FULL_CHARS) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if ((marks[i] & marks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    private int[] toMarks(String[] words, int n) {
        int[] marks = new int[n];
        for (int i = 0; i < words.length; i++) {
            marks[i] = toMark(words[i]);
        }
        return marks;
    }

    private int toMark(String word) {
        int mark = 0;
        for (char c : word.toCharArray()) {
            mark |= 1 << (c - 'a');
            if (mark == FULL_CHARS) {
                break;
            }
        }
        return mark;
    }

    public int maxProduct2(String[] words) {
        int n = words.length;
        int max = 0;
        Map<Integer, Integer> marks = toMarkMap(words, n);
        Map.Entry[] array = marks.entrySet().toArray(new Map.Entry[0]);
        for (int i = 0; i < array.length - 1; i++) {
            if ((int) array[i].getKey() == FULL_CHARS) {
                continue;
            }
            for (int j = i + 1; j < array.length; j++) {
                if (((int) array[i].getKey() & (int) array[j].getKey()) == 0) {
                    max = Math.max(max, (int) array[i].getValue() * (int) array[j].getValue());
                }
            }
        }
        return max;
    }

    private Map<Integer, Integer> toMarkMap(String[] words, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            int mark = toMark(words[i]);
            if (!map.containsKey(mark) || map.get(mark) < len) {
                map.put(mark, len);
            }
        }
        return map;
    }

}
