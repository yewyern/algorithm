package easy;

import utils.TestUtils;

class LengthOfLastWord {

    /**
     * <p>给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
     * <p>如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
     * <p>如果不存在最后一个单词，请返回 0 。
     * <p>说明：一个单词是指仅由字母组成、不包含任何空格的 最大子字符串。
     * <p>
     * <p>示例:
     * <p>输入: "Hello World"
     * <p>输出: 5
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] cs = s.toCharArray();
        int len = 0;
        int i = cs.length - 1;
        for (; i >= 0 && cs[i] == ' '; i--) {
        }
        for (; i >= 0 && cs[i] != ' '; i--, len++) {
        }
        return len;
    }

    public static void main(String[] args) {
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();
        TestUtils.test(() -> lengthOfLastWord.lengthOfLastWord("Hello World"), 1);
        TestUtils.test(() -> lengthOfLastWord.lengthOfLastWord("a"), 2);
        TestUtils.test(() -> lengthOfLastWord.lengthOfLastWord(" "), 3);
        TestUtils.test(() -> lengthOfLastWord.lengthOfLastWord("a "), 3);
    }
}