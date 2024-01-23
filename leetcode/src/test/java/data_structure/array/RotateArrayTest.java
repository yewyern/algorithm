package data_structure.array;

/**
 * <a href="https://leetcode.cn/problems/rotate-array">189. 轮转数组</a>
 *
 * @author xuzhou
 * @since 2024/1/22 22:21
 */
public class RotateArrayTest {

    public void rotate(int[] nums, int k) {
        // 翻转2遍
        int n = nums.length;
        if (n == 1) {
            return;
        }
        k = k % n;
        reverse(nums, 0, n);
        reverse(nums, 0, k);
        reverse(nums, k, n);
    }

    private void reverse(int[] nums, int start, int end) {
        end--;
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    public void rotate3(int[] nums, int k) {
        // 0->k,k->2k，循环
        int n = nums.length;
        if (n == 1) {
            return;
        }
        k = k % n;
        int count = 0;
        for (int i = 0; count < n; i++) {
            int curr = nums[i];
            int next;
            int j = i;
            do {
                j = (j + k) % n;
                next = nums[j];
                nums[j] = curr;
                curr = next;
                count++;
            } while (j != i);
        }
    }

    public void rotate2(int[] nums, int k) {
        // 递归写法
        int n = nums.length;
        if (n == 1) {
            return;
        }
        k = k % n;
        dfs(nums, k, 0);
    }

    private void dfs(int[] nums, int k, int i) {
        int n = nums.length;
        if (i == n) {
            return;
        }
        int temp = nums[i];
        dfs(nums, k, i + 1);
        nums[(i + k) % n] = temp;
    }


    public void rotate1(int[] nums, int k) {
        // 辅助数组，O(n)空间的算法
        int n = nums.length;
        if (n == 1) {
            return;
        }
        k = k % n;
        int[] helper = new int[n];
        copy(nums, helper, k);
        copy(helper, nums, 0);
    }

    private void copy(int[] source, int[] target, int offset) {
        int n = source.length;
        for (int j : source) {
            target[(offset++) % n] = j;
        }
    }
}
