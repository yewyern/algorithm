package data_structure.array;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/global-and-local-inversions/">775. 全局倒置与局部倒置</a>
 * <p>
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 * <p>
 * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 * <p>
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
 * <p>
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,2]
 * 输出：true
 * 解释：有 1 个全局倒置，和 1 个局部倒置。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：false
 * 解释：有 2 个全局倒置，和 1 个局部倒置。
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] < n
 * nums 中的所有整数 互不相同
 * nums 是范围 [0, n - 1] 内所有数字组成的一个排列
 *
 * @author zhou.xu
 * @since 2022/11/16 17:45
 */
public class GlobalAndLocalInversionsTest {

    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(nums[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdealPermutation1(int[] nums) {
        // 插入排序
        int local = localInversion(nums);
        return local == globalInversion(nums, local);
    }

    private int globalInversion(int[] nums, int local) {
        return partition(nums, 0, nums.length, local);
    }

    private int partition(int[] nums, int l, int r, int local) {
        if (r - l < 2) {
            return 0;
        }
        int m = (l + r) >> 1;
        int partition = partition(nums, l, m, local);
        if (partition > local) {
            return partition;
        }
        partition += partition(nums, m, r, local);
        if (partition > local) {
            return partition;
        }
        return partition + merge(nums, l, r, m, local);
    }

    private int merge(int[] nums, int l, int r, int m, int local) {
        int count = 0;
        int[] temp = new int[r - l];
        int i = l, j = m, p = 0;
        while (i < m && j < r) {
            if (nums[i] > nums[j]) {
                temp[p++] = nums[i++];
                count += r - j;
                if (count > local) {
                    return count;
                }
            } else {
                temp[p++] = nums[j++];
            }
        }
        while (i < m) {
            temp[p++] = nums[i++];
        }
        while (j < r) {
            temp[p++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, l, r - l);
        return count;
    }

    public boolean isIdealPermutation2(int[] nums) {
        // 归并求逆序对：O（nlogn)
        return localInversion(nums) == globalInversion(nums);
    }

    private int localInversion(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
            }
        }
        return count;
    }

    private int globalInversion(int[] nums) {
        return partition(nums, 0, nums.length);
    }

    private int partition(int[] nums, int l, int r) {
        if (r - l < 2) {
            return 0;
        }
        int m = (l + r) >> 1;
        return partition(nums, l, m) + partition(nums, m, r) + merge(nums, l, r, m);
    }

    private int merge(int[] nums, int l, int r, int m) {
        int count = 0;
        int[] temp = new int[r - l];
        int i = l, j = m, p = 0;
        while (i < m && j < r) {
            if (nums[i] > nums[j]) {
                temp[p++] = nums[i++];
                count += r - j;
            } else {
                temp[p++] = nums[j++];
            }
        }
        while (i < m) {
            temp[p++] = nums[i++];
        }
        while (j < r) {
            temp[p++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, l, r - l);
        return count;
    }

    public boolean isIdealPermutation3(int[] nums) {
        // 暴力解法：双重循环，O（n^2)
        int N = nums.length;
        int global = 0, local = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (nums[i] > nums[j]) {
                    global++;
                    if (i + 1 == j) {
                        local++;
                    }
                }
            }
        }
        return global == local;
    }

    @Test
    public void test() {
        System.out.println(isIdealPermutation(new int[]{1, 0, 2}));
        System.out.println(isIdealPermutation(new int[]{1, 2, 0}));
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10000; i++) {
            int len = RandomUtils.nextInt(1, 100000);
            int[] nums = RandomArray.generateNoRepeatSortedArray(len, 0, len);
            boolean idealPermutation = isIdealPermutation(nums);
            boolean idealPermutationComparison = isIdealPermutation2(nums);
            if (idealPermutation != idealPermutationComparison) {
                System.out.println("nums = " + Arrays.toString(nums) + ", idealPermutation = " + idealPermutation + ", idealPermutationComparison = " + idealPermutationComparison);
            }
        }
    }
}
