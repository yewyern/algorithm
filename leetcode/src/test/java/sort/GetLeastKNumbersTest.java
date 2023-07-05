package sort;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/description/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 40. 最小的k个数</a>
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 * @author xuzhou
 * @since 2023/6/26 16:46
 */
public class GetLeastKNumbersTest {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        int N = arr.length;
        if (k == N) {
            return arr;
        }
        if (k <= (N >> 1)) {
            sort(arr, k, false);
            return Arrays.copyOfRange(arr, 0, k);
        } else {
            sort(arr, N - k, true);
            return Arrays.copyOfRange(arr, N - k, N);
        }
    }

    private void sort(int[] nums, int k, boolean reverse) {
        int N = nums.length;
        for (int i = 0; i < k; i++) {
            int curr = i;
            for (int j = i + 1; j < N; j++) {
                if (reverse) {
                    curr = nums[j] > nums[curr] ? j : curr;
                } else {
                    curr = nums[j] < nums[curr] ? j : curr;
                }
            }
            swap(nums, i, curr);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] getLeastNumbersComparison(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    @Test
    public void test() {
        int tests = 10000;
        int maxLen = 1000;
        int max = 10000;
        for (int i = 0; i < tests; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(maxLen, max);
            int k = RandomUtils.nextInt(nums.length);
            int[] leastNumbers = getLeastNumbers(Arrays.copyOf(nums, nums.length), k);
            int[] leastNumbersComparison = getLeastNumbersComparison(Arrays.copyOf(nums, nums.length), k);
            if (!equals(leastNumbersComparison, leastNumbers)) {
                getLeastNumbers(Arrays.copyOf(nums, nums.length), k);
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("k = " + k);
                System.out.println("leastNumbers = " + Arrays.toString(leastNumbers));
                System.out.println("leastNumbersComparison = " + Arrays.toString(leastNumbersComparison));
                break;
            }
        }
    }

    private boolean equals(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }
}
