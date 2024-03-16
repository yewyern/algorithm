package dynamic_programing;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/stickers-to-spell-word/">691. 贴纸拼词</a>
 *
 * @author zhou.xu
 * @since 2023/11/12 14:05
 */
public class StickersToSpellWordTest {

    @Test
    public void test() {
        System.out.println(minStickers(new String[]{"with", "example", "science"}, "thehat"));
    }

    public int minStickers(String[] stickers, String target) {
        // 递归解法优化
        int n = stickers.length;
        int[][] resolvedStickers = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                resolvedStickers[i][c - 'a']++;
            }
        }
        Map<String, Integer> cache = new HashMap<>();
        int res = dfs(resolvedStickers, target, cache);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs(int[][] stickers, String target, Map<String, Integer> cache) {
        if (cache.containsKey(target)) {
            return cache.get(target);
        }
        if (target.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[target.charAt(0) - 'a'] == 0) {
                continue;
            }
            String minus = minus(target, sticker);
            int next = dfs(stickers, minus, cache);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(next + 1, min);
            }
        }
        cache.put(target, min);
        return min;
    }

    private String minus(String target, int[] sticker) {
        int[] count = new int[26];
        for (char c : target.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int left = count[i] - sticker[i];
            for (int j = 0; j < left; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }


    public int minStickers1(String[] stickers, String target) {
        // 递归解法优化
        int n = stickers.length;
        int[][] resolvedStickers = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                resolvedStickers[i][c - 'a']++;
            }
        }
        int res = dfs1(resolvedStickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs1(int[][] stickers, String target) {
        if (target.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[target.charAt(0) - 'a'] == 0) {
                continue;
            }
            String minus = minus1(target, sticker);
            int next = dfs1(stickers, minus);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(next + 1, min);
            }
        }
        return min;
    }

    private String minus1(String target, int[] sticker) {
        int[] count = new int[26];
        for (char c : target.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int left = count[i] - sticker[i];
            for (int j = 0; j < left; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    public int minStickers2(String[] stickers, String target) {
        // 暴力递归
        int res = dfs2(stickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs2(String[] stickers, String target) {
        if (target.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String minus = minus2(target, sticker);
            if (minus.equals(target)) {
                continue;
            }
            int next = dfs2(stickers, minus);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(next + 1, min);
            }
        }
        return min;
    }

    private String minus2(String target, String sticker) {
        int[] count = new int[26];
        for (char c : target.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : sticker.toCharArray()) {
            count[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i]; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }
}
