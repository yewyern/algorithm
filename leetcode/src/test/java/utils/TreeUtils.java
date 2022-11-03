package utils;

import java.util.LinkedList;

/**
 * @author zhou.xu
 * @since 2020/3/31 14:15
 */
public class TreeUtils {

    public static TreeNode toTreeNodeDeepFirst(Integer... nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(nums[0]);
        TreeNode curr = head;
        for (int i = 1; i < nums.length; i++) {
            Integer num = nums[i++];
            if (null != num) {
                curr.left = new TreeNode(num);
            }
            if (i < nums.length && null != nums[i]) {
                curr.right = new TreeNode(nums[i]);
            }
            curr = curr.left;
        }
        return head;
    }

    public static TreeNode toTreeNodeWideFirst(Integer... nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(nums[0]);
        TreeNode curr;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(head);
        int i = 1;
        while (!list.isEmpty() && i < nums.length) {
            curr = list.poll();
            Integer num = nums[i++];
            if (null != num) {
                curr.left = new TreeNode(num);
                list.addLast(curr.left);
            }
            if (i < nums.length && null != nums[i]) {
                curr.right = new TreeNode(nums[i++]);
                list.addLast(curr.right);
            } else {
                i++;
            }
        }
        return head;
    }

    public static int[] toListWideFirst(TreeNode root) {
        if (root == null) {
            return null;
        }
        int[] res = {root.val};
        TreeNode[] temp = {root};
        toListWideFirst(temp);
        return res;
    }

    public static TreeNode[] toListWideFirst(TreeNode[] nodes) {
        TreeNode[] temp = new TreeNode[nodes.length * 2];
        boolean flag = false;
        for (int i = 0; i < nodes.length; i++) {
            if (null != nodes[i]) {
                temp[i * 2] = nodes[i].left;
                temp[i * 2 + 1] = nodes[i].right;
                flag = true;
            }
        }
        if (flag) {
            TreeNode[] res = new TreeNode[nodes.length + temp.length];

        }
        return temp;
    }

    public static boolean isSameNode(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        return p != null && q != null && p.val == q.val;
    }

    public static void main(String[] args) {
        TreeNode treeNode3 = TreeUtils.toTreeNodeWideFirst(9, -42, -42, null, 76, 76, null, null, 13, null, 13);
        System.out.println("treeNode3 = " + treeNode3);
    }

    public static <T> void copy(T[] copyFrom, int begin, int end, T[] copyTo, int firstIndex, int lastIndex) {
        
    }
}
