package search.binary;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/find-smallest-letter-greater-than-target/">744. 寻找比目标字母大的最小字母</a>
 *
 * @author zhou.xu
 * @since 2024/1/20 14:08
 */
public class FindSmallestGreatThanTargetTest {

    @Test
    public void test() {
        System.out.println(nextGreatestLetter(new char[]{'e', 'e', 'g', 'g'}, 'g'));
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (letters[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l == n ? letters[0] : letters[l];
    }
}
