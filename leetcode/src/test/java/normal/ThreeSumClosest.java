package normal;

import java.util.Arrays;

/**
 * <p> 最接近的三数之和
 * <p> 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p> 示例：
 * <p>
 * <p> 输入：nums = [-1,2,1,-4], target = 1
 * <p> 输出：2
 * <p> 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 *
 * @author zhou.xu
 * @date 2020/10/12 16:08
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        int min = Integer.MAX_VALUE;
        boolean changed = true;
        for (int i = 0; i < nums.length && changed; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            changed = false;
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                } else {
                    int temp = Math.abs(sum - target);
                    if (temp < min) {
                        ans = sum;
                        min = temp;
                        changed = true;
                    }
                    if (sum > target) {
                        while (j < k && nums[k - 1] == nums[k]) {
                            k--;
                        }
                        k--;
                    } else {
                        while (j < k && nums[j + 1] == nums[j]) {
                            j++;
                        }
                        j++;
                    }
                }
            }
        }
        return ans;
    }

}
