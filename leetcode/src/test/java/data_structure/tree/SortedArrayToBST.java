package data_structure.tree;

import utils.TreeNode;

/**
 * <p>将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * <p>本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * <p>示例:
 * <p>
 * <p>给定有序数组: [-10,-3,0,5,9],
 * <p>
 * <p>一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * <p>      0
 * <p>     / \
 * <p>   -3   9
 * <p>   /   /
 * <p> -10  5
 * <p>
 */
class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start == end - 1) {
            return new TreeNode(nums[start]);
        }
        int center = (start + end) / 2;
        TreeNode root = new TreeNode(nums[center]);
        if (start < center) {
            root.left = sortedArrayToBST(nums, start, center);
        }
        if (center + 1 < end) {
            root.right = sortedArrayToBST(nums, center + 1, end);
        }
        return root;
    }

    public static void main(String[] args) {
        SortedArrayToBST a = new SortedArrayToBST();
        TreeNode node = a.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println("node = " + node);
    }
}