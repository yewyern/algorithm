package hard;

import org.junit.Test;

public class StringMatchKmp {

    public int match(String s, String m) {
        // TODO
        if (s == null || m == null || s.length() < m.length() || m.length() == 0) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int[] next = getNextArray(match);
        int x = 0, y = 0;//x表示s上的位置，y表示m上的位置
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                // y往前跳
                y = next[y];
            }
        }
        // 如果y移动到m的末尾，说明找到了，第一个匹配的位置就是x-y(m.len)
        return y == match.length ? x - y : -1;
    }

    /**
     * 数组中每个位置表示字符串中前缀和后缀相等的最大长度
     *
     * @param match
     * @return
     */
    private int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int x = 0;// 当前跟i-1比较的位置
        while (i < match.length) {
            if (match[i - 1] == match[x]) {
                // 当前比较位置跟i-1一样
                next[i++] = ++x;
            } else if (x > 0) {
                // 还可以继续往前比
                x = next[x];
            } else {
                // x到0都没找到，往前就是-1了
                next[i++] = 0;
            }
        }

        return next;
    }

    @Test
    public void test() {
        match("aaabbbccc", "bbb");
    }
}
