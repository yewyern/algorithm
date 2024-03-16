package normal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

/**
 * @author zhou.xu
 * @since 2020/10/22 10:11
 */
public class PartitionLabels {

    /**
     * 贪心算法+双指针
     *
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        if (S.length() == 1) {
            return Collections.singletonList(1);
        }
        // 取每个字符的最大位置
        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>(26);
        // start 记录当前开始位置，用于计算长度
        // end 表示当前所有字符最大结束位置，如果当前位置=结束位置，说明，当前的所有字符都在此位置之前，可以进行分割
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            int hash = S.charAt(i) - 'a';
            end = Math.max(end, last[hash]);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }

    public List<Integer> partitionLabels2(String S) {
        if (S.length() == 1) {
            return Collections.singletonList(1);
        }
        int[] map = new int[26];
        for (char c : S.toCharArray()) {
            map[c - 'a']++;
        }
        int[] temp = new int[26];
        int last = -1;
        Set<Integer> set = new HashSet<>(26);
        List<Integer> res = new ArrayList<>(26);

        for (int i = 0; i < S.length(); i++) {
            int hash = S.charAt(i) - 'a';
            temp[hash]++;
            if (temp[hash] == map[hash] && set.stream().allMatch(integer -> map[integer] == temp[integer])) {
                res.add(i - last);
                last = i;
                set.clear();
            } else {
                set.add(hash);
            }
        }
        return res;
    }

    @Test
    public void test() {

    }
}
