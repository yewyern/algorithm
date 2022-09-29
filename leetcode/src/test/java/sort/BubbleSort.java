package sort;

import utils.ArrayUtils;

/**
 * 冒泡排序
 *
 * @author xuzhou
 * @since 2021/5/6 15:30
 */
public class BubbleSort {

    public static void main(String[] args) {
        int test = 10000;
        long start = System.nanoTime();
        for (int i = 0; i < test; i++) {
            int[] array = ArrayUtils.newRandomArray(40, 100);
            bubbleSort(array);
            ArrayUtils.checkSort(array);
        }
        System.out.println("bubbleSort  = " + (System.nanoTime() - start));
        start = System.nanoTime();
        for (int i = 0; i < test; i++) {
            int[] array = ArrayUtils.newRandomArray(40, 100);
            bubbleSort2(array);
            ArrayUtils.checkSort(array);
        }
        System.out.println("bubbleSort2 = " + (System.nanoTime() - start));
        start = System.nanoTime();
        for (int i = 0; i < test; i++) {
            int[] array = ArrayUtils.newRandomArray(40, 100);
            bubbleSort3(array);
            ArrayUtils.checkSort(array);
        }
        System.out.println("bubbleSort3 = " + (System.nanoTime() - start));
    }

    public static void bubbleSort(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort2(int[] nums) {
        // 冒泡排序改进1：flag表示是否有交换，如果循环过程中，1次交换都没有，即已经有序，退出排序
        int l = nums.length;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i < l; i++) {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i - 1);
                    flag = true;
                }
            }
            l--;
        }
    }

    public static void bubbleSort3(int[] nums) {
        // 冒泡排序改进1：flag表示最右边有交换的位置，如果循环过程钟，1次交换都没有，即已经有序，退出排序
        int l = nums.length;
        int flag = l;
        while (flag > 0) {
            flag = 0;
            for (int i = 1; i < l; i++) {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i - 1);
                    flag = i;
                }
            }
            l = flag;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
