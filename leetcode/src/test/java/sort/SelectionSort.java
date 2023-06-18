package sort;

import utils.ArrayUtils;
import utils.RandomArray;

import java.util.Arrays;

/**
 * 选择排序 O(n^2)
 *
 * @author zhou.xu
 * @since 2023/6/18 21:39
 */
public class SelectionSort {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] nums = RandomArray.generateRandomLengthNoEmptyArray(50, 100);
            selectionSort(nums);
            if (!ArrayUtils.checkSort(nums)) {
                System.out.println("nums = " + Arrays.toString(nums));
                break;
            }
        }
    }

    public static void selectionSort(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < N - 1; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            if (i != min) {
                swap(nums, i, min);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
