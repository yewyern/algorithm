package search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/3sum/">15. 三数之和</a>
 *
 * @author xuzhou
 * @since 2024/1/23 21:55
 */
public class ThreeSumTest {

    @Test
    public void test() {
        System.out.println(threeSum(new int[]{0, 0, 0, 0}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = n - 1;
            while (l < r && nums[l] <= -nums[i]) {
                int target = -nums[l] - nums[i];
                while (r > l && nums[r] > target) {
                    r--;
                }
                if (r > l && nums[r] == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                }
                while (l < n - 1 && nums[l] == nums[l + 1]) {
                    l++;
                }
                l++;
            }
        }
        return res;
    }
}
