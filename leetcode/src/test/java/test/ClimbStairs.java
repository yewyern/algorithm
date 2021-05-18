package test;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>酷跑运动员爬梯子，每向上梯爬一节梯子需要6秒钟，而向下爬一层需要4秒钟，且每爬完1节，停留5秒钟。
 * <p>假设运动员初始在地面，爬梯顺序如下：
 * <p>输入：[1, 2]，输出：17
 * <p>输入：[1,3,2,3,1]，输出：66
 *
 * @author zhou.xu
 * @date 2020/10/12 16:21
 */
public class ClimbStairs {

    private static final int UP_USE = 6;
    private static final int DOWN_USE = 4;
    private static final int WAIT = 5;

    public int climbStairs(int[] nums) {
        int res = 0;
        int curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > curr) {
                res += (nums[i] - curr) * 11;
            } else {
                res += (curr - nums[i]) * 9;
            }
            curr = nums[i];
        }
        return res - 5;
    }
    

    public void test(int expected, int... nums) {
        int n = climbStairs(nums);
        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println("n = " + n);
        Assert.assertEquals(expected, n);
    }

    @Test
    public void test() {
        test(17, 1, 2);
        test(66, 1, 3, 2, 3, 1);
    }
}
