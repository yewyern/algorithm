package search;

import utils.ArrayUtils;

/**
 * <a href="https://leetcode.cn/problems/kLl5u1">LCR 006. 两数之和 II - 输入有序数组</a>
 * <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted">167. 两数之和 II - 输入有序数组</a>
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * @author : zhou.xu
 * @since : 2020/6/3 16:04
 */
public class TwoSum2Test {

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }
        for (int i = 0, j = numbers.length - 1; i < j; ) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1}; // 下标从1开始的要加1，从0开始的不要加
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return null;
    }

    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }
        for (int i = 0; i < numbers.length - 1; i++) {
            int j = binarySearch(numbers, target - numbers[i], i + 1);
            if (j >= 0) {
                return new int[]{i + 1, j + 1}; // 下标从1开始的要加1，从0开始的不要加
            }
        }
        return null;
    }

    private int binarySearch(int[] numbers, int target, int start) {
        int end = numbers.length;
        int center = (start + end) / 2;
        while (start < center) {
            if (numbers[center] == target) {
                return center;
            }
            if (numbers[center] > target) {
                end = center;
            } else {
                start = center;
            }
            center = (start + end) / 2;
        }
        return numbers[start] == target ? start : -1;
    }

    public int[] test(int target, int... numbers) {
        return twoSum(numbers, target);
    }

    public static void main(String[] args) {
        TwoSum2Test sum = new TwoSum2Test();
        int[] test = sum.test(9, 2, 7, 11, 15);
        System.out.println(ArrayUtils.toString(test));
        int[] test2 = sum.test(-1, -1, 0);
        System.out.println(ArrayUtils.toString(test2));
    }
}