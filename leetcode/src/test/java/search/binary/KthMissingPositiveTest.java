package search.binary;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/kth-missing-positive-number/">1539. 第 k 个缺失的正整数</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 20:27
 */
public class KthMissingPositiveTest {

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateNoRepeatSortedArray(100, 1, 1000);
            int k = RandomUtils.nextInt(1, 1000);
            int res1 = findKthPositive(nums, k);
            int res2 = findKthPositive2(nums, k);
            if (res1 != res2) {
                System.out.println("失败");
                System.out.println(Arrays.toString(nums));
                System.out.println(k);
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
    }

    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        if (arr[n - 1] < n + k) {
            return n + k;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (arr[m] <= m + k) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l == 0 ? k : k + l;
    }

    public int findKthPositive2(int[] arr, int k) {
        int ans = 1;
        for (int i : arr) {
            while (ans < i && --k > 0) {
                ans++;
            }
            if (k == 0) {
                return ans;
            }
            ans++;
        }
        while (--k > 0) {
            ans++;
        }
        return ans;
    }
}
