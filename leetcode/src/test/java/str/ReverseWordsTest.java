package str;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof">剑指 Offer 58 - I. 翻转单词顺序</a>
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 注意：本题与主站 151 题相同：<a href="https://leetcode-cn.com/problems/reverse-words-in-a-string/">151. 反转字符串中的单词</a>
 * <p>
 * 注意：此题对比原题有改动
 *
 * @author xuzhou
 * @since 2023/6/15 15:15
 */
public class ReverseWordsTest {

    @Test
    public void test() {
        check("the sky is blue", "blue is sky the");
        check("  hello world!  ", "world! hello");
        check("a good   example", "example good a");
    }

    private void check(String s, String expected) {
        String actual = reverseWords(s);
//        if (!Objects.equals(actual, expected)) {
            System.out.println("str = " + s + ", expected = " + expected + ", actual = " + actual);
//        }
    }

    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int N = s.length();
        char[] cs = s.toCharArray();
        int l = 0, r = N - 1;
        while (l < N && cs[l] == ' ') {
            l++;
        }
        boolean first = true;
        while (r >= l) {
            while (r >= l && cs[r] == ' ') {
                r--;
            }
            int l1 = r;
            while (l1 >= l && cs[l1] != ' ') {
                l1--;
            }
            if (!first) {
                sb.append(" ");
            }
            for (int i = l1 + 1; i <= r; i++) {
                sb.append(cs[i]);
            }
            first = false;
            r = l1;
        }
        return sb.toString();
    }
}
