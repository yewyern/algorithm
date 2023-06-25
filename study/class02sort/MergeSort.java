package class02sort;

import utils.RandomUtils;

import java.util.Arrays;

/**
 * @author zhou.xu
 * @since 2023/6/24 23:01
 */
public class MergeSort {

    public static int[] mergeSort(int[] nums) {
        // 归并排序，分治算法
        return process(nums, 0, nums.length - 1);
    }

    public static int[] process(int[] nums, int l, int r) {
        if (l == r) {
            return new int[]{nums[l]};
        }
        int m = l + (r - l) / 2;
        int[] left = process(nums, l, m);
        int[] right = process(nums, m + 1, r);
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int N1 = left.length;
        int N2 = right.length;
        int[] res = new int[N1 + N2];
        int i = 0, p = 0, q = 0;
        while (p < N1 && q < N2) {
            if (left[p] <= right[q]) {
                res[i++] = left[p++];
            } else {
                res[i++] = right[q++];
            }
        }
        while (p < N1) {
            res[i++] = left[p++];
        }
        while (q < N2) {
            res[i++] = right[q++];
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] nums = RandomUtils.generateRandomLengthNoEmptyArray(1000, 10000);
            int[] sort = mergeSort(nums);
            Arrays.sort(nums);
            if (!Arrays.equals(nums, sort)) {
                System.out.println("sort = " + Arrays.toString(sort));
                System.out.println("nums = " + Arrays.toString(nums));
                break;
            }
        }
    }
}
