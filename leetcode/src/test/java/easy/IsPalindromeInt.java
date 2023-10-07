package easy;

import org.junit.Test;
import utils.RandomUtils;

/**
 * <a href="https://leetcode.cn/problems/palindrome-number">9. 回文数</a>
 */
public class IsPalindromeInt {

    public static boolean isPalindromeComparison1(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        } else if (x % 10 == 0) {
            return false;
        }
        int right = 0;
        int tempRight, tempX;
        while (true) {
            tempRight = x % 10 + right * 10;
            tempX = x / 10;
            if (tempRight > tempX) {
                if (x == right) {
                    return true;
                } else {
                    return tempX == right;
                }
            }
            right = tempRight;
            x = tempX;
        }
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        } else if (x % 10 == 0) {
            return false;
        }
        char[] chars = String.valueOf(x).toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeComparison2(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    @Test
    public void test() {
        for (int i = 0; i < 100000; i++) {
            int num = RandomUtils.nextInt(Integer.MAX_VALUE);
            if (!RandomUtils.nextBool()) {
                num = -num;
            }
            boolean palindrome = isPalindrome(num);
            if ((isPalindromeComparison1(num) != palindrome) || (palindrome != isPalindromeComparison2(num))) {
                System.out.println("num = " + num);
                System.out.println("palindrome = " + palindrome);
                System.out.println("-------------------");
            }
        }
    }
}