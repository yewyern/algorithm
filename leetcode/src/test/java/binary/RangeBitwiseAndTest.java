package binary;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/bitwise-and-of-numbers-range/">201. 数字范围按位与</a>
 * @author xuzhou
 * @since 2024/1/10 15:34
 */
public class RangeBitwiseAndTest {

    @Test
    public void test() {
        System.out.println(rangeBitwiseAnd(1, 2147483647));
    }

    public int rangeBitwiseAnd(int left, int right) {
        if (left == 0 || right == 0) {
            return 0;
        }
        int i = Integer.highestOneBit(left);
        int j = Integer.highestOneBit(right);
        if (i == j) {
            return i | rangeBitwiseAnd(left ^ i, right ^ j);
        }
        return 0;
    }
}
