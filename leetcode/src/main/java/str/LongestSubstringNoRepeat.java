package str;

import java.util.Arrays;
import utils.GenerateRandomString;

/**
 * 最长的无重复字符的连续子串
 *
 * @author xuzhou
 * @date 2021/4/27 15:00
 */
public class LongestSubstringNoRepeat {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String s = GenerateRandomString.generateRandomString();
            int process = process(s);
            int process2 = process2(s);
            if (process != process2) {
                System.out.println("s = " + s);
                System.out.println("process = " + process);
                System.out.println("process2 = " + process2);
            }
        }
    }

    /**
     * "acddcb"
     * 1、每次到一个位置，看下当前位置和上次出现当前字符的位置的长度，不考虑其他字符出现重复时的长度
     * 2、考虑其他字符出现重复的情况，上一次出现重复的时候是哪个位置，只看最近的位置
     *
     * @param s
     * @return
     */
    public static int process(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] cs = s.toCharArray();
        int[] map = new int[256];
        Arrays.fill(map, -1);
        int len = 0;// 当前最大长度
        int cur; // 当前长度
        int pre = -1; // 上一次最近的重复的字符的索引
        for (int i = 0; i < cs.length; i++) {
            pre = Math.max(map[cs[i]], pre);
            cur = i - pre;
            len = Math.max(cur, len);
            map[cs[i]] = i;
        }
        return len;
    }

    // 方法2： 滑动窗口，双指针法
    public static int process2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int l = 0, r = 0;
        int max = 1;
        char[] str = s.toCharArray();
        boolean[] map = new boolean[256];
        while (l < str.length) {
            // 先把右边界往右扩，遇到重复字符就停
            while (r < str.length && !map[str[r]]) {
                map[str[r]] = true;
                r++;
            }
            // 更新最大长度
            max = Math.max(r - l, max);
            if (r == str.length - 1) {
                break;
            }
            // 左边界往右
            map[str[l]] = false;
            l++;
        }
        return max;
    }
}
