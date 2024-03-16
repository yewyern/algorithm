package str;

import org.junit.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/repeated-dna-sequences">187. 重复的DNA序列</a>
 *
 * @author zhou.xu
 * @since 2023/11/5 16:39
 */
public class FindRepeatedDnaSequencesTest {

    @Test
    public void test() {
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAA"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        if (n <= 10) {
            return new ArrayList<>();
        }
        // 'A' = 00 'C' = 01 'G' = 10 'T' = 10
        // 10个字符可以用20个bit进行表示
        List<String> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        int r = 0;
        int hash = 0;
        int move = 0;
        while (r < 10) {
            int h = toHash(cs[r++]) << move;
            hash |= h;
            move += (r < 10 ? 2 : 0);
        }
        map.put(hash, 1);
        while (r < n) {
            hash >>= 2;
            int h = toHash(cs[r++]) << move;
            hash |= h;
            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                res.add(toStr(entry.getKey()));
            }
        }
        return res;
    }

    private int toHash(char c) {
        if (c == 'C') {
            return 1;
        } else if (c == 'G') {
            return 2;
        } else if (c == 'T') {
            return 3;
        }
        return 0;
    }

    private char toChar(int i) {
        if (i == 1) {
            return 'C';
        } else if (i == 2) {
            return 'G';
        } else if (i == 3) {
            return 'T';
        }
        return 'A';
    }

    private String toStr(int hash) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char c = toChar(hash & 3);
            sb.append(c);
            hash >>= 2;
        }
        return sb.toString();
    }


    public List<String> findRepeatedDnaSequences2(String s) {
        int n = s.length();
        if (n <= 10) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        int l = 0, r = 10;
        while (r <= n) {
            String s1 = String.valueOf(Arrays.copyOfRange(cs, l++, r++));
            map.put(s1, map.getOrDefault(s1, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

}
