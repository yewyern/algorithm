package str;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 67. 把字符串转换成整数</a>
 * @author xuzhou
 * @since 2023/6/2 14:00
 */
public class StrToIntTest {

    public int strToInt(String str) {
        long res = 0;
        int flag = 1;
        char[] cs = str.toCharArray();
        int i = 0;
        // 跳过前置空格
        while (i < cs.length && cs[i] == ' ') {
            i++;
        }
        if (i >= cs.length) {
            return 0;
        }
        if (cs[i] == '-') {
            flag = -1;
            i++;
        } else if (cs[i] == '+') {
            i++;
        } else if (cs[i] < '0' || cs[i] > '9') {
            return 0;
        }
        long temp = 0;
        while (i < cs.length && cs[i] >= '0' && cs[i] <= '9') {
            temp = temp * 10 + (cs[i] - '0');
            res = flag * temp;
            if (res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            } else if (res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            i++;
        }
        return (int) res;
    }

    @Test
    public void test() {
        check("42", 42);
        check("   -42", -42);
        check("4193 with words", 4193);
        check("words and 987", 0);
        check("2147483647", 2147483647);
        check("-2147483648", -2147483648);
    }

    private void check(String str, int expected) {
        int i = strToInt(str);
        if (i != expected) {
            System.out.println("str = " + str + ", expected = " + expected + ", res = " + i);
        }
    }
}
