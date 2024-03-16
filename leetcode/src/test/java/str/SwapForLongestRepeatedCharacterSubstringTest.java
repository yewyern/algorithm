package str;

import org.junit.Test;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/">1156. 单字符重复子串的最大长度</a>
 * <p>
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * <p>
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 * <p>
 * 示例 1：
 * 输入：text = "ababa"
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：text = "aaabaaa"
 * 输出：6
 * <p>
 * 示例 3：
 * 输入：text = "aaabbaaa"
 * 输出：4
 * <p>
 * 示例 4：
 * 输入：text = "aaaaa"
 * 输出：5
 * <p>
 * 示例 5：
 * 输入：text = "abcdef"
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= text.length <= 20000
 * text 仅由小写英文字母组成。
 *
 * @author zhou.xu
 * @since 2023/6/3 21:07
 */
public class SwapForLongestRepeatedCharacterSubstringTest {

    private void check(String text, int expected) {
        int maxed = maxRepOpt1(text);
        if (maxed != expected) {
            System.out.println("text = " + text + ", expected = " + expected + ", actual = " + maxed);
        }
    }

    @Test
    public void test() {
        check("ababa", 3);
        check("aaabaaa", 6);
        check("aaabbaaa", 4);
        check("aaaaa", 5);
        check("abcdef", 1);
        check("aaabaaaccca", 7);
        check("abbbaaacaaa", 7);
    }

    public int maxRepOpt1(String text) {
        // 此题的关键在于解决能不能换，怎么换
        // 1、如果只有1段相同字符的连续段，该字符最大就是该连续段的长度
        // 2、如果只有2段连续段，且不相邻，则该字符最大是最大连续段的长度
        // 3、如果只有2段，且相邻，则该字符最大为2段长度相加
        // 4、如果超过2个连续段，则为以上最大值的基础上再加1
        // 要解决2个问题
        // 1、相邻的最大长度
        // 2、有没有可交换的字符
        char[] cs = text.toCharArray();
        int N = cs.length;
        int[] counts = new int[26];
        // 计算每个字符的个数，这样在每一段的最大长度基础上判断是否还可以交换
        for (char c : cs) {
            counts[c - 'a']++;
        }
        int max = 1;
        int l = 0, r = 0;
        while (r < N) {
            int cnt = 0;
            // 这是第一段
            while (r < N && cs[r] == cs[l]) {
                r++;
                cnt++;
            }
            // 判断是否有相邻的第2段
            if (r + 1 < N && cs[r + 1] == cs[l]) {
                // 保存待交换的位置
                int temp = r;
                r++;
                while (r < N && cs[r] == cs[l]) {
                    r++;
                    cnt++;
                }
                // 把r重新回到交换的位置
                r = temp;
            }
            // 总共字符数大于当前最大长度
            if (cnt < counts[cs[l] - 'a']) {
                cnt++;
            }
            max = Math.max(max, cnt);
            l = r;
        }
        return max;
    }

    public int maxRepOptComparison(String text) {
        // 先找到每个字符对应的各个范围，然后对单个字符判断是否可交换
        LinkedList<Range>[] ranges = new LinkedList[26];
        char[] cs = text.toCharArray();
        int N = cs.length;
        int l = 0, r = 0;
        while (r < N) {
            while (r < N && cs[r] == cs[l]) {
                r++;
            }
            int i = cs[l] - 'a';
            LinkedList<Range> list = ranges[i];
            if (list == null) {
                list = new LinkedList<>();
                ranges[i] = list;
            }
            list.add(new Range(l, r, r - l));
            l = r;
        }
        int max = 1;
        for (LinkedList<Range> list : ranges) {
            if (list == null) {
                continue;
            }
            int addToOne = list.size() > 1 ? 1 : 0;
            int addToClosing = list.size() > 2 ? 1 : 0;
            Range last = null;
            for (Range range : list) {
                max = Math.max(range.getLen() + addToOne, max);
                if (last != null && last.getEnd() == range.getStart() - 1) {
                    max = Math.max(range.getLen() + last.getLen() + addToClosing, max);
                }
                last = range;
            }
        }
        return max;
    }

    private static class Range {
        private final int start;
        private final int end;
        private final int len;

        public Range(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getLen() {
            return len;
        }
    }

    public int maxRepOpt2(String text) {
        // 此方法有bug，由于只从后往前换，漏掉了从前往后换的可能
        // 对于此方法增加从前往后换，可解决该问题
        return Math.max(getMaxFromLeft(text), getMaxFromRight(text));
    }

    private static int getMaxFromLeft(String text) {
        char[] cs = text.toCharArray();
        int N = cs.length;
        int max = 1;
        int l = 0, r = 0;
        while (r < N) {
            // 找到本次相同字符的长度
            while (r < N && cs[l] == cs[r]) {
                r++;
            }
            // 到头之后，从右往前找可以交换的
            int swap = N - 1;
            while (swap > r && cs[swap] != cs[l]) {
                swap--;
            }
            if (swap > r) {
                int temp = r;
                // 交换一次
                swap(cs, temp, swap);
                while (r < N && cs[l] == cs[r]) {
                    r++;
                }
                // 还原
                swap(cs, temp, swap);
                max = Math.max(max, r - l);
                l = temp;
                r = l;
            } else {
                // 无法交换
                max = Math.max(max, r - l);
                l = r;
            }
        }
        return max;
    }

    private static int getMaxFromRight(String text) {
        char[] cs = text.toCharArray();
        int N = cs.length;
        int max = 1;
        int l = N - 1, r = N - 1;
        while (l >= 0) {
            // 找到本次相同字符的长度
            while (l >= 0 && cs[l] == cs[r]) {
                l--;
            }
            // 到头之后，从右往前找可以交换的
            int swap = 0;
            while (swap < l && cs[swap] != cs[r]) {
                swap++;
            }
            if (swap < l) {
                int temp = l;
                // 交换一次
                swap(cs, temp, swap);
                while (l >= 0 && cs[l] == cs[r]) {
                    l--;
                }
                // 还原
                swap(cs, temp, swap);
                max = Math.max(max, r - l);
                r = temp;
                l = r;
            } else {
                // 无法交换
                max = Math.max(max, r - l);
                r = l;
            }
        }
        return max;
    }

    private static void swap(char[] cs, int p, int q) {
        char temp = cs[p];
        cs[p] = cs[q];
        cs[q] = temp;
    }


}
