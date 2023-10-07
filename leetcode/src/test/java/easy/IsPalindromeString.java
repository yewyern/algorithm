package easy;

/**
 * <a href="https://leetcode.cn/problems/valid-palindrome">125. 验证回文串</a>
 * <P>给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * <P>说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * <P>示例 1:
 * <p>
 * <P>输入: "A man, a plan, a canal: Panama"
 * <P>输出: true
 * <P>示例 2:
 * <p>
 * <P>输入: "race a car"
 * <P>输出: false
 * <p>
 *
 * @author zhou.xu
 * @since 2020/5/8 14:09
 */
public class IsPalindromeString {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] cs = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isValidChar(cs[i])) {
                i++;
            }
            while (j > i && !isValidChar(cs[j])) {
                j--;
            }
            if (i == j) {
                return true;
            }
            if (isDigit(cs[i]) && cs[i] != cs[j]) {
                return false;
            }
            if (cs[i] != cs[j] && Math.abs(cs[i] - cs[j]) != 0x20) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isValidChar(char c) {
        return isDigit(c)
                || (c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z');
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public boolean isPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] cs = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char a = cs[i];
            char b = cs[j];
            boolean isDigitA = a >= 0x30 && a <= 0x39;
            boolean isUpperA = a >= 0x41 && a <= 0x5A;
            boolean isLowerA = a >= 0x61 && a <= 0x7A;
            if (!isDigitA && !isUpperA && !isLowerA) {
                i++;
                continue;
            }
            boolean isDigitB = b >= 0x30 && b <= 0x39;
            boolean isUpperB = b >= 0x41 && b <= 0x5A;
            boolean isLowerB = b >= 0x61 && b <= 0x7A;
            if (!isDigitB && !isUpperB && !isLowerB) {
                j--;
                continue;
            }
            if (a == b) {
                i++;
                j--;
            } else {
                if (isUpperA && isLowerB && a == b - 0x20) {
                    i++;
                    j--;
                } else if (isLowerA && isUpperB && a - 0x20 == b) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindromeString isPalindrome = new IsPalindromeString();
        boolean b = isPalindrome.isPalindrome("");
        System.out.println("b = " + b);
        b = isPalindrome.isPalindrome("A man, a plan, a canal: Panama");
        System.out.println("b = " + b);
        b = isPalindrome.isPalindrome("race a car");
        System.out.println("b = " + b);
    }
}
