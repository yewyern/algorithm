package sort;

import utils.RandomArray;

import java.util.Arrays;

/**
 * @author zhou.xu
 * @since 2023/6/18 21:54
 */
public class InsertionSort {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] nums = RandomArray.generateRandomLengthNoEmptyArray(100, 1000);
            int[] copied = Arrays.copyOf(nums, nums.length);
            insertionSort(nums);
            Arrays.sort(copied);
            if (!Arrays.equals(nums, copied)) {
                System.out.println(Arrays.toString(nums));
                System.out.println(Arrays.toString(copied));
                break;
            }
        }
    }

    public static void insertionSort(int[] nums) {
        // 插入排序算法
        // 每次插入一张牌，当索引向右更新的时候，判断该索引的数字是否比前面的位置小，如果小，往前换，直到找到合适的位置
        int N = nums.length;
        for (int i = 1; i < N; i++) {
            int j = i;
            while (j > 0 && nums[j] < nums[j - 1]) {
                swap(nums, j, j - 1);
                j--;
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
