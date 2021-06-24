package sort;

import java.util.Arrays;
import org.junit.Test;
import utils.ArrayUtils;
import utils.RandomArray;

/**
 * @author xuzhou
 * @date 2021/6/24 15:39
 */
public class MergeSort {

    /**
     * 循环方案
     *
     * @param arr
     */
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int step = 1;
        while (step < N) {
            int L = 0;
            int M = step - 1;
            while (M < N) {
                int R = Math.min(N - 1, M + step);
                merge(arr, L, M, R);
                L = R + 1;
                M = L + step - 1;
            }
            if (step > N / 2) {
                // 防止整形溢出
                break;
            }
            step <<= 1;
        }
    }

    /**
     * 递归方案
     *
     * @param arr
     */
    public void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    public void mergeSort(int[] arr, int L, int R) {
        // [L...R]
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        mergeSort(arr, L, M);
        mergeSort(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    public void merge(int[] arr, int L, int M, int R) {
        // [L...R]
        int p1 = L;
        int p2 = M + 1;
        int[] temp = new int[R - L + 1];
        int i = 0;
        while (p1 <= M && p2 <= R) {
            if (arr[p1] <= arr[p2]) {
                temp[i++] = arr[p1++];
            } else {
                temp[i++] = arr[p2++];
            }
        }
        while (p1 <= M) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= R) {
            temp[i++] = arr[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[L + i] = temp[i];
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1000, 2000);
            mergeSort(nums);
            boolean b = ArrayUtils.checkSort(nums);
            if (!b) {
                System.out.println("nums = " + Arrays.toString(nums));
            }
        }
    }

    @Test
    public void test2() {
        int[] a = RandomArray.generateRandomLengthArray(1000, 2000);
        System.out.println("a = " + Arrays.toString(a));
        mergeSort(a);
        System.out.println("a = " + Arrays.toString(a));
    }

    @Test
    public void test3() {
        // 数据量太大，无法测试 java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        int[] a = RandomArray.generate(Integer.MAX_VALUE, Integer.MAX_VALUE);
        mergeSort(a);
        boolean b = ArrayUtils.checkSort(a);
        System.out.println("b = " + b);
    }
}
