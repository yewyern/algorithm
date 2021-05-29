package sort;

import java.util.Arrays;

/**
 * <p>给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * <p>
 * <p>对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * <p>
 * <p>你可以返回任何满足上述条件的数组作为答案。
 * <p>
 * <p>示例：
 * <p>
 * <p>输入：[4,2,5,7]
 * <p>输出：[4,5,2,7]
 * <p>解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * @author zhou.xu
 * @date 2020/11/12 16:40
 */
public class SortArrayByParityII {

    public static int[] sortArrayByParityII1(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int N = A.length;
        int even = 0;// even - 当前偶数索引位置不是偶数的
        int odd = 1; // odd - 当前奇数索引位置不是奇数的
        while (odd < N && even < N) {
            while (even < N && A[even] % 2 == 0) {
                even += 2;
            }
            while (odd < N && A[odd] % 2 == 1) {
                odd += 2;
            }
            if (odd < N && even < N) {
                int temp = A[odd];
                A[odd] = A[even];
                A[even] = temp;
            }
        }
        return A;
    }

    public static int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if ((A[i] & 1) == 1) {
                while ((A[j] & 1) == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    public static void swap(int[] A, int i, int j) {
        A[i] ^= A[j];
        A[j] ^= A[i];
        A[i] ^= A[j];
    }

    public static void test(int... A) {
        System.out.println("before = " + Arrays.toString(A));
        System.out.println("After = " + Arrays.toString(sortArrayByParityII(A)));
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        test(4, 2, 5, 7);
    }
}
