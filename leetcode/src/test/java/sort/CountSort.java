package sort;

import utils.ArrayUtils;
import utils.RandomArray;

import java.util.Arrays;

/**
 * 计数排序，桶排序的一种，非比较排序，时间复杂度与样本空间k有关，如果k>nlogn，效率不如比较排序
 *
 * @author xuzhou
 * @since 2021/5/24 14:59
 */
public class CountSort {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthNoEmptyArray(100, 100);
            sort(nums);
            if (!ArrayUtils.checkSort(nums)) {
                System.out.println("nums = " + Arrays.toString(nums));
                break;
            }
        }
    }

    public static void sort(int[] nums) {
        int N = nums.length;
        // 限定样本空间k
        int k = 100;
        int[] count = new int[k];
        for (int i = 0; i < N; i++) {
            count[nums[i]]++;
        }
        for (int i = 0, p = 0; i < k; i++) {
            for (int j = 0; j < count[i]; j++) {
                nums[p++] = i;
            }
        }
    }

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
}
