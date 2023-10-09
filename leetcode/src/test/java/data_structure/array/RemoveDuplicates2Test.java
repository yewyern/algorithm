package data_structure.array;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/">80. 删除有序数组中的重复项 II</a>
 * @author xuzhou
 * @since 2023/10/9 16:16
 */
public class RemoveDuplicates2Test {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return n;
        }
        int size = 2;
        for (int i = 2; i < n; i++) {
            if (nums[i] != nums[size - 1] || nums[i] != nums[size - 2]) {
                nums[size++] = nums[i];
            }
        }
        return size;
    }
}
