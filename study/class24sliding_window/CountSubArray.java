package class24sliding_window;


import utils.RandomUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个整形数组arr，和一个整数num
 * 某个数组的子数组sub，如果想达标，必须满足：
 * sub中最大值 - sub中最小值 <= num
 * 返回arr中达标子数组的数量
 *
 * @author zhou.xu
 * @since 2023/6/17 21:28
 */
public class CountSubArray {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int[] nums = RandomUtils.generateRandomLengthNoEmptyArray(100, 200);
            int maxDiff = RandomUtils.nextInt(200);
            long l = countSubArrays(nums, maxDiff);
            long l2 = countSubArraysComparison(nums, maxDiff);
            if (l != l2) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("maxDiff = " + maxDiff);
                System.out.println("l = " + l);
                System.out.println("l2 = " + l2);
                break;
            }
        }
    }

    public static long countSubArrays(int[] nums, int maxDiff) {
        // 滑动窗口法
        // 对任意范围l -> r如果满足条件，则其内部的所有子数组都满足条件
        // 对任意范围l -> r如果不满足条件，则包括该范围的所有子数组都不满足条件
        if (nums == null || nums.length == 0 || maxDiff < 0) {
            return 0;
        }
        long N = nums.length;
        int count = 0;
        LinkedList<Integer> maxQ = new LinkedList<>();
        LinkedList<Integer> minQ = new LinkedList<>();
        int r = 0;
        for (int l = 0; l < N; l++) {
            // [l..r)
            // r每次到第一次不达标的位置
            // l+1 -> r-1一定是达标的，不需要检查
            while (r < N) {
                while (!maxQ.isEmpty() && nums[maxQ.peekLast()] <= nums[r]) {
                    maxQ.pollLast();
                }
                maxQ.addLast(r);
                while (!minQ.isEmpty() && nums[minQ.peekLast()] >= nums[r]) {
                    minQ.pollLast();
                }
                minQ.addLast(r);
                if (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > maxDiff) {
                    break;
                }
                r++;
            }
            count += r - l;
            if (maxQ.peekFirst() == l) {
                maxQ.pollFirst();
            }
            if (minQ.peekFirst() == l) {
                minQ.pollFirst();
            }
        }
        return count;
    }

    public static long countSubArraysComparison(int[] nums, int maxDiff) {
        // 暴力方法
        if (nums == null || nums.length == 0 || maxDiff < 0) {
            return 0;
        }
        long count = 0;
        int N = nums.length;
        for (int l = 0; l < N; l++) {
            for (int r = l; r < N; r++) {
                int min = nums[l];
                int max = nums[l];
                for (int i = l + 1; i <= r; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
                if (max - min <= maxDiff) {
                    count++;
                }
            }
        }
        return count;
    }
}
