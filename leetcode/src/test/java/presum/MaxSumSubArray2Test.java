package presum;

import org.junit.Test;
import utils.ArrayUtils;
import utils.RandomArray;

import java.util.Arrays;

/**
 * 剑指 Offer 42. 连续子数组的最大和 修改，返回最大值所在的子数组
 * @author xuzhou
 * @since 2023/6/19 17:50
 */
public class MaxSumSubArray2Test {

    public int[] maxSubArray(int[] nums) {
        int start = 0, end = 0;
        int pre = 0, max = nums[0];
        int lastStart = 0;
        for (int i = 0; i < nums.length; i++) {
            if (pre <= 0) {
                pre = nums[i];
                lastStart = i;
            } else {
                pre += nums[i];
            }
            if (max <= pre) {
                max = pre;
                start = lastStart;
                end = i;
            }
        }
        return Arrays.copyOfRange(nums, start , end + 1);
    }

    public int[] maxSubArrayComparison(int[] nums) {
        // 暴力方法
        int start = 0, end = 0;
        int max = Integer.MIN_VALUE;
        int N = nums.length;
        for (int l = 0; l < N; l++) {
            for (int r = l; r < N; r++) {
                int sum = 0;
                for (int i = l; i <= r; i++) {
                    sum += nums[i];
                }
                if (sum > max) {
                    max = sum;
                    start = l;
                    end = r;
                }
            }
        }
        return Arrays.copyOfRange(nums, start , end + 1);
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1, 100, -1000, 1000);
            int[] sum1 = maxSubArray(nums);
            int[] sum2 = maxSubArrayComparison(nums);
            if (ArrayUtils.sum(sum1) != ArrayUtils.sum(sum2)) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("sum1 = " + Arrays.toString(sum1));
                System.out.println("sum2 = " + Arrays.toString(sum2));
                break;
            }
        }
    }

}
