package sliding_window;

/**
 * <a href="https://leetcode.cn/problems/minimum-window-substring">76. 最小覆盖子串</a>
 * @author xuzhou
 * @since 2024/2/22 17:50
 */
public class MinWindowSubStringTest {

    /**
     * 返回字符串s中最短的子串，该子串包含t中所有的字符
     * @param s 字符串s
     * @param t 字符串t
     * @return 最短的子串
     */
    public String minWindow(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int n = str.length;
        int m = target.length;
        if (n < m) {
            return "";
        }
        // 统计字符串t中每个字符出现的次数
        int[] count = new int[128];
        for (char c : target) {
            count[c]++;
        }
        // 初始化左右指针、最短子串长度和起始位置
        int left = 0, right = 0, minLen = Integer.MAX_VALUE, minStart = 0;
        // 遍历字符串s
        while (right < n) {
            count[str[right]]--;
            right++;
            // 如果统计数组中的所有字符出现次数都为0，说明当前窗口包含了字符串t中的所有字符
            while (isAllCount(count)) {
                // 更新最短子串长度和起始位置
                if (right - left < minLen) {
                    minLen = right - left;
                    minStart = left;
                }
                count[str[left]]++;
                left++;
            }
        }
        // 如果最短子串长度为最大值，说明不存在包含字符串t中所有字符的子串，返回空字符串
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    /**
     * 判断统计数组中所有字符出现次数是否都为0
     * @param count 统计数组
     * @return 是否都为0
     */
    private boolean isAllCount(int[] count) {
        for (int i = 'A'; i <= 'z'; i++) {
            if (count[i] > 0) {
                return false;
            }
        }
        return true;
    }

}
