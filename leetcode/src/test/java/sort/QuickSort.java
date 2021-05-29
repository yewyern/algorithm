package sort;

import java.util.Arrays;
import utils.ArrayUtils;
import utils.RandomUtils;

/**
 * @author xuzhou
 * @date 2021/5/6 17:20
 */
public class QuickSort {

    public static void main(String[] args) {
        int test = 10000;
        int len = 40;
        int max = 100;
        long start = System.nanoTime();
        for (int i = 0; i < test; i++) {
            int[] a = ArrayUtils.newRandomArray(len, max);
            quickSort(a);
//            boolean b = ArrayUtils.checkSort(a);
//            if (!b) {
//                System.out.println("a = " + Arrays.toString(a));
//            }
        }
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        for (int i = 0; i < test; i++) {
            int[] a = ArrayUtils.newRandomArray(len, max);
            Arrays.sort(a);
//            boolean b = ArrayUtils.checkSort(a);
//            if (!b) {
//                System.out.println("a = " + Arrays.toString(a));
//            }
        }
        System.out.println(System.nanoTime() - start);
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length);
    }

    public static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] index = getIndex(nums, l, r);
        quickSort(nums, l, index[0]);
        quickSort(nums, index[1], r);
    }

    private static int[] getIndex(int[] nums, int l, int r) {
        int flag = nums[RandomUtils.nextInt(l, r)];
        int less = l - 1;
        while (l < r) {
            if (nums[l] < flag) {
                BubbleSort.swap(nums, ++less, l++);
            } else if (nums[l] > flag) {
                BubbleSort.swap(nums, --r, l);
            } else {
                l++;
            }
        }
        return new int[]{less + 1, r};
    }
}
