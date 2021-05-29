package normal;

import java.util.Arrays;
import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;
import utils.TreeNode;
import utils.TreeUtils;

/**
 * <p>给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * <p>本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * <p>示例:
 * <p>
 * <p>给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * <p>一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * <p>      0
 * <p>     / \
 * <p>   -3   9
 * <p>   /   /
 * <p> -10  5
 * <p>
 *
 * @author zhou.xu
 * @date 2020/8/18 16:32
 */
public class SortedListToBSTTest {

    ListNode globalHead;

    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        int len = getLen(head);
        return buildTree(0, len - 1);
    }

    private TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

    private int getLen(ListNode head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        return count;
    }

    @Test
    public void test() {
        test(-10, -3, 0, 5, 9);
        test(-10, -3, -2, 0, 5, 7, 9);
    }

    public void test(int... nums) {
        ListNode listNode = ListUtils.toListNodes(nums);
        TreeNode treeNode = sortedListToBST(listNode);
        System.out.println("treeNode = " + treeNode);
        int[] treeNodes = TreeUtils.toListWideFirst(treeNode);
        System.out.println("treeNodes = " + Arrays.toString(treeNodes));
    }

}
