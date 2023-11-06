package data_structure.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array">421. 数组中两个数的最大异或值</a>
 *
 * @author xuzhou
 * @since 2023/11/6 15:07
 */
public class FindMaximumXORTest {

    private static final int[] ONE_BITS = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824};

    @Test
    public void test() {
        System.out.println(findMaximumXOR(new int[] {14,70,53,83,49,91,36,80,92,51,66,70}));
    }

    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int max = 0;
        int[][] buckets = new int[31][];
        int[] size = new int[31];
        // 按最高位进行分桶
        for (int num : nums) {
            for (int i = 30; i >= 0; i--) {
                if ((ONE_BITS[i] & num) != 0) {
                    if (buckets[i] == null) {
                        buckets[i] = new int[n];
                    }
                    buckets[i][size[i]++] = num;
                    break;
                }
            }
        }
        // 找到最大值
        int[] maxBucket = null;
        for (int i = 30; i >= 0; i--) {
            if (size[i] > 0) {
                maxBucket = Arrays.copyOfRange(buckets[i], 0, size[i]);
                break;
            }
        }
        if (maxBucket != null) {
            Arrays.sort(maxBucket);
            for (int num : maxBucket) {

            }
        }
        return max;
    }

    public int findMaximumXOR2(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(nums[i] ^ nums[j], max);
            }
        }
        return max;
    }
}
