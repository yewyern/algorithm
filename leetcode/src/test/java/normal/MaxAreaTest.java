package normal;

import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i,
 * 0)。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且n的值至少为 2。
 * <p>
 * 示例：
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7] 输出：49
 *
 * @author zhou.xu
 * @date 2020/8/17 10:44
 */
public class MaxAreaTest extends TestCase {

    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1;
        int max = 0;
        while (start < end) {
            int hStart = height[start];
            int hEnd = height[end];
            max = Math.max(max, (end - start) * Math.min(hStart, hEnd));
            if (hStart < hEnd) {
                while (start < end && height[start] <= hStart) {
                    start++;
                }
            } else {
                while (start < end && height[end] <= hEnd) {
                    end--;
                }
            }
        }
        return max;
    }

    @Test
    public void testMaxArea() {
        test(new int[]{7, 8, 6, 2, 5, 4, 8, 3, 7}, 56);
        test(new int[]{1, 1, 6, 1, 8, 6, 2, 5, 4, 8, 3, 7}, 54);
        test(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49);
        test(new int[]{1, 1}, 1);
    }

    private void test(int[] heights, int excepted) {
        System.out.println(Arrays.toString(heights));
        int res = maxArea(heights);
        System.out.println(res);
        TestCase.assertEquals(res, excepted);
    }
}