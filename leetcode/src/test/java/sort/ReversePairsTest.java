package sort;

import org.junit.Test;
import utils.RandomArray;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/description/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 51. 数组中的逆序对</a>
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,5,6,4]
 * 输出: 5
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 *
 * @author xuzhou
 * @since 2023/7/17 14:19
 */
public class ReversePairsTest {

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1000, 10000);
            int[] nums1 = Arrays.copyOf(nums, nums.length);
            int[] copied = Arrays.copyOf(nums, nums.length);
            int res = reversePairs(nums);
            int res2 = reversePairs2(nums1);
            if (res != res2) {
                System.out.println("nums = " + Arrays.toString(copied));
                System.out.println("res = " + res);
                System.out.println("res2 = " + res2);
                break;
            }
        }
    }

    public int reversePairs(int[] nums) {
        // 归并法
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return partition(nums, 0, nums.length);
    }

    private int partition(int[] nums, int l, int r) {
        if (r - l == 1) {
            return 0;
        }
        int count = 0;
        int m = (l + r) >> 1;
        count += partition(nums, l, m);
        count += partition(nums, m, r);
        return count + merge(nums, l, m, r);
    }

    private int merge(int[] nums, int l, int m, int r) {
        int N = r - l;
        int[] temp = new int[N];
        int count = 0;
        int i = 0, p = l, q = m;
        while (p < m && q < r) {
            if (nums[p] <= nums[q]) {
                temp[i++] = nums[p++];
            } else {
                temp[i++] = nums[q++];
                count += m - p;
            }
        }
        while (p < m) {
            temp[i++] = nums[p++];
        }
        while (q < r) {
            temp[i++] = nums[q++];
        }
        System.arraycopy(temp, 0, nums, l, N);
        return count;
    }

    public int reversePairs2(int[] nums) {
        // 冒泡排序改进
        int N = nums.length;
        boolean allReversed = true;
        for (int i = 0; i < N - 1; i++) {
            if (nums[i] <= nums[i + 1]) {
                allReversed = false;
            }
        }
        if (allReversed) {
            return (N * (N - 1)) >> 1;
        }
        int count = 0;
        while (N > 0) {
            int last = 0;
            for (int i = 1; i < N; i++) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i, i - 1);
                    last = i;
                    count++;
                }
            }
            N = last;
        }
        return count;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int reversePairs3(int[] nums) {
        // 暴力法
        int N = nums.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
        }
        return count;
    }
}
