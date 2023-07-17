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

    public static int[] mergeSort2(int[] nums) {
        // 循环实现归并排序
        // 利用步长伸缩，步长表示待合并的左右子数组长度，每次调整步长*2
        // 当步长超过整个数组长度时停止
        // 步长变化次数logN次，每个步长需要循环整个数组进行合并，所以总时间复杂度O(NlogN)
        int N = nums.length;
        int step = 1;
        while (step < N) {
            int l = 0;
            while (l < N) {
                int m = l + step;
                if (m >= N) {
                    break;
                }
                int r = Math.min(m + step, N);
                merge(nums, l, m, r);
                l = r;
            }
            if (step > (N >> 1)) {
                break;
            }
            step <<= 1;
        }
        return process(nums, 0, nums.length - 1);
    }

    private static void merge(int[] nums, int l, int m, int r) {
        int[] helper = new int[r - l];
        int i = 0, p = l, q = m;
        while (p < m && q < r) {
            helper[i++] = nums[p] <= nums[q] ? nums[p++] : nums[q++];
        }
        while (p < m) {
            helper[i++] = nums[p++];
        }
        while (q < r) {
            helper[i++] = nums[q++];
        }
        for (int num : helper) {
            nums[l++] = num;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] nums = RandomUtils.generateRandomLengthNoEmptyArray(1000, 10000);
            int[] sort = mergeSort2(nums);
            Arrays.sort(nums);
            if (!Arrays.equals(nums, sort)) {
                System.out.println("sort = " + Arrays.toString(sort));
                System.out.println("nums = " + Arrays.toString(nums));
                break;
            }
        }
    }
}
