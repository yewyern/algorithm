package easy;

/**
 * 整数反转
 *
 * @author xzer
 */
public class ReverseIntegerTest {

    //给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
//示例 1:
//
//输入: 123
//输出: 321
// 示例 2:
//
//输入: -123
//输出: -321
//示例 3:
//
//输入: 120
//输出: 21
//注意:
//
//假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

    public static int reverse(int x) {
        int revertedNumber = 0;
        int maxValue = Integer.MAX_VALUE / 10;
        int minValue = Integer.MIN_VALUE / 10;
        int maxValueMod = Integer.MAX_VALUE % 10;
        int minValueMod = Integer.MIN_VALUE % 10;
        while (x != 0) {
            int mod = x % 10;
            if (revertedNumber > maxValue || revertedNumber < minValue) {
                return 0;
            }
            if (revertedNumber == maxValue && mod > maxValueMod) {
                return 0;
            }
            if (revertedNumber == minValue && mod < minValueMod) {
                return 0;
            }
            revertedNumber = revertedNumber * 10 + mod;
            x /= 10;
        }
        return revertedNumber;
    }

    public static void main(String[] args) {
        System.out.println("res = " + reverse(-1534236469));
    }
}