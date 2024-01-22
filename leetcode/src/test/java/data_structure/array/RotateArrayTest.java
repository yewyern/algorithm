package data_structure.array;

/**
 * <a href="https://leetcode.cn/problems/rotate-array">189. 轮转数组</a>
 *
 * @author xuzhou
 * @since 2024/1/22 22:21
 */
public class RotateArrayTest {

    public void rotate(int[] nums, int k) {
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
