package sort;

import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

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
            int[] array = RandomArray.generateRandomLengthNoEmptyArray(40, 100);
            int[] copied = Arrays.copyOf(array, array.length);
            bubbleSort(array);
            Arrays.sort(copied);
            if (!Arrays.equals(array, copied)) {
                System.out.println("copied = " + Arrays.toString(copied));
                System.out.println("array = " + Arrays.toString(array));
                return;
            }
        }
        System.out.println("bubbleSort  = " + (System.nanoTime() - start));
        start = System.nanoTime();
        for (int i = 0; i < test; i++) {
            int[] array = RandomArray.generateRandomLengthNoEmptyArray(40, 100);
            int[] copied = Arrays.copyOf(array, array.length);
            bubbleSort2(array);
            Arrays.sort(copied);
            if (!Arrays.equals(array, copied)) {
                System.out.println("copied = " + Arrays.toString(copied));
                System.out.println("array = " + Arrays.toString(array));
                return;
            }
        }
        System.out.println("bubbleSort2 = " + (System.nanoTime() - start));
        start = System.nanoTime();
        for (int i = 0; i < test; i++) {
            int[] array = RandomArray.generateRandomLengthNoEmptyArray(40, 100);
            int[] copied = Arrays.copyOf(array, array.length);
            bubbleSort3(array);
            Arrays.sort(copied);
            if (!Arrays.equals(array, copied)) {
                System.out.println("copied = " + Arrays.toString(copied));
                System.out.println("array = " + Arrays.toString(array));
                return;
            }
        }
        System.out.println("bubbleSort3 = " + (System.nanoTime() - start));
    }

    public static void bubbleSort(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort2(int[] nums) {
        // 冒泡排序改进1：flag表示是否有交换，如果循环过程中，1次交换都没有，即已经有序，退出排序
        int N = nums.length;
        for (int i = 0; i < N - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < N - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    flag = true;
                    swap(nums, j, j + 1);
                }
            }
            if (!flag) {
                return;
            }
        }
    }

    public static void bubbleSort3(int[] nums) {
        // 冒泡排序改进1：flag表示最右边有交换的位置，如果循环过程钟，1次交换都没有，即已经有序，退出排序
        int N = nums.length;
        while (N > 0) {
            int last = 0;
            for (int i = 1; i < N; i++) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i, i - 1);
                    last = i;
                }
            }
            N = last;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
