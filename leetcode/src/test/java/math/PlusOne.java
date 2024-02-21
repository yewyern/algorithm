package math;

import java.util.function.Supplier;
import utils.TestUtils;

class PlusOne {

    /**
     * <a href="https://leetcode.cn/problems/plus-one/">66. 加一</a>
     * <p>给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * <p>最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * <p>示例 1:
     * <p>输入: [1,2,3]
     * <p>输出: [1,2,4]
     * <p>解释: 输入数组表示数字 123。
     *
     * <p>示例 2:
     * <p>输入: [4,3,2,1]
     * <p>输出: [4,3,2,2]
     * <p>解释: 输入数组表示数字 4321。
     * <p>
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        if (len == 0) {
            return digits;
        }
        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        if (digits[0] > 0) {
            return digits;
        }
        int[] res = new int[len + 1];
        res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        TestUtils.testIntArray(test(1, 2, 3), 1);
        TestUtils.testIntArray(test(), 1);
        TestUtils.testIntArray(test(0), 1);
        TestUtils.testIntArray(test(9, 9, 9), 1);
        TestUtils.testIntArray(test(9), 1);
    }

    private static Supplier<int[]> test(int... digits) {
        PlusOne plusOne = new PlusOne();
        return () -> plusOne.plusOne(digits);
    }
}