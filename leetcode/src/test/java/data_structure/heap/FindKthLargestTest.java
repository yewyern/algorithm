package data_structure.heap;

import org.junit.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array">215. 数组中的第K个最大元素</a>
 * @author xuzhou
 * @since 2024/1/8 15:30
 */
public class FindKthLargestTest {

    @Test
    public void test() {
        System.out.println(findKthLargest(new int[] {3,2,1,5,6,4}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int size = nums.length;
        heapify(nums, size);
        for (int i = 0; i < k; i++) {
            swap(nums, 0, --size);
            siftDown(nums, 0, size);
        }
        return nums[n - k];
    }

    private void heapify(int[] nums, int size) {
        // 大顶堆
        int i = (size >> 1) - 1;
        while (i >= 0) {
            siftDown(nums, i--, size);
        }
    }

    private void siftDown(int[] nums, int i, int size) {
        int m = size >> 1;
        while (i < m) {
            int left = (i << 1) + 1;
            int right = left + 1;
            if (right < size && nums[right] > nums[left]) {
                left = right;
            }
            if (nums[i] >= nums[left]) {
                break;
            }
            swap(nums, i, left);
            i = left;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int findKthLargest2(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }
}
