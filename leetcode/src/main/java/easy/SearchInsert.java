package easy;

class SearchInsert {

    //给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    //你可以假设数组中无重复元素。
    //
    //示例 1:
    //输入: [1,3,5,6], 5
    //输出: 2

    //示例 2:
    //输入: [1,3,5,6], 2
    //输出: 1

    //示例 3:
    //输入: [1,3,5,6], 7
    //输出: 4

    //示例 4:
    //输入: [1,3,5,6], 0
    //输出: 0

    public int searchInsert(int[] nums, int target) {
        int i = 0;
        for (; i < nums.length && nums[i] < target; i++) {
        }
        return i;
    }

    public static void main(String[] args) {
        SearchInsert searchInsert = new SearchInsert();
        searchInsert.test(new int[]{1, 3, 5, 6}, 5);
        searchInsert.test(new int[]{1, 3, 5, 6}, 2);
        searchInsert.test(new int[]{1, 3, 5, 6}, 7);
        searchInsert.test(new int[]{1, 3, 5, 6}, 6);
        searchInsert.test(new int[]{1, 3, 5, 6}, 0);
    }

    public void test(int[] nums, int target) {
        System.out.print("nums = [");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(i + ",");
        }
        System.out.println("], target = " + target + ", index = " + searchInsert(nums, target));
    }
}