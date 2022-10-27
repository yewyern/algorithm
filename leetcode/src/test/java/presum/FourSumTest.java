package presum;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import utils.RandomArray;

/**
 * <a href="https://leetcode.cn/problems/4sum/">18. 四数之和</a>
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 * @author zhou.xu
 * @since 2022/10/27 22:48
 */
public class FourSumTest {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) {
            return res;
        }
        if (nums.length == 4) {
            List<Integer> a = new ArrayList<>();
            int sum = 0;
            for (int num : nums) {
                sum += num;
                a.add(num);
            }
            if (sum == target) {
                res.add(a);
            }
            return res;
        }

        return null;
    }

    @Test
    public void test() {
        int[] nums = RandomArray.generateRandomLengthArray(1, 109, -109, 109);
    }
}
