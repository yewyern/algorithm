package array;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent">1662. 检查两个字符串数组是否相等</a>
 * @author zhou.xu
 * @since 2022/11/1 21:59
 */
public class CheckIfTwoStringArraysAreEquivalentTest {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(String ss : word1) {
            sb1.append(ss);
        }
        for(String ss : word2) {
            sb2.append(ss);
        }
        return sb1.toString().contentEquals(sb2);
    }

    public boolean arrayStringsAreEqualComparison(String[] word1, String[] word2) {
        // 这个比上面的慢-_-!
        int i = 0, j = 0, p = 0, q = 0;
        while (true) {
            if (p == word1[i].length()) {
                i++;
                p = 0;
            }
            if (q == word2[j].length()) {
                j++;
                q = 0;
            }
            if (i == word1.length && j == word2.length) {
                return true;
            } else if (i == word1.length || j == word2.length) {
                return false;
            }
            if (word1[i].charAt(p) != word2[j].charAt(q)) {
                return false;
            }
            p++;
            q++;
        }
    }

    @Test
    public void test() {
        String[] word1 = {"abc", "d", "defg"}, word2 = {"abcddefg"};
        boolean b = arrayStringsAreEqual(word1, word2);
        System.out.println("b = " + b);
    }
}
