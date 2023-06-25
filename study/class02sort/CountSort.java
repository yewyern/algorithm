package class02sort;

import utils.RandomUtils;

import java.util.Arrays;

/**
 * 计数排序
 *
 * @author zhou.xu
 * @since 2023/6/25 22:21
 */
public class CountSort {

    public static int[] countSort(int[] nums) {
        int N = nums.length;
        int[] res = new int[N];
        int min = nums[0], max = nums[0];
        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        int[] count = new int[max - min + 1];
        for (int x : nums) {
            count[x - min]++;
        }
        int i = 0;
        for (int j = 0; j < count.length; j++) {
            for (int k = 0; k < count[j]; k++) {
                res[i++] = j + min;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] nums = RandomUtils.generateRandomLengthNoEmptyArray(1000, 10000);
            int[] sort = countSort(nums);
            Arrays.sort(nums);
            if (!Arrays.equals(nums, sort)) {
                System.out.println("sort = " + Arrays.toString(sort));
                System.out.println("nums = " + Arrays.toString(nums));
                break;
            }
        }
    }
}
