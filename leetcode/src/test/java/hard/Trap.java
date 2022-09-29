package hard;

import java.util.Arrays;
import org.junit.Test;

/**
 * <p>接雨水
 * <p>给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>示例 1：
 * <p>
 * <p>输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * <p>输出：6
 * <p>解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>示例 2：
 * <p>
 * <p>输入：height = [4,2,0,3,2,5]
 * <p>输出：9
 *
 * @author zhou.xu
 * @since 2020/10/27 17:59
 */
public class Trap {

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        int max = height[0];
        int temp = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] >= max) {
                res += temp;
                temp = 0;
                max = height[i];
            } else {
                temp += max - height[i];
            }
        }
        return res;
    }

    public void test(int... height) {
        System.out.println("height = " + Arrays.toString(height));
        int trap = trap(height);
        System.out.println("trap = " + trap);
    }

    @Test
    public void test() {
        test(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1);
        test(4, 2, 0, 3, 2, 5);
    }
}
