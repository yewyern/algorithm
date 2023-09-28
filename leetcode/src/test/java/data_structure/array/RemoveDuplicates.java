package data_structure.array;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array">26. 删除有序数组中的重复项</a>
 */
public class RemoveDuplicates {

    //给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
    //不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
    //
    //示例 1:
    //给定数组 nums = [1,1,2],
    //函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
    //
    //示例 2:
    //给定 nums = [0,0,1,1,1,2,2,3,3,4],
    //函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

    /**
     * 删除排序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length < 2) {
            return nums.length;
        }
        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[len]) {
                len++;
                nums[len] = nums[i];
            }
        }
        return len + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        removeDuplicates.test(new int[]{0, 1, 2, 3, 4});
        removeDuplicates.test(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
    }

    private void test(int[] a) {
        int len = removeDuplicates(a);
        System.out.println("len = " + len);
        for (int i = 0; i < len; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();
    }
}