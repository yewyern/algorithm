package data_structure.tree;

import utils.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof">剑指 Offer 32 - I. 从上到下打印二叉树</a>
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回：
 * <p>
 * [3,9,20,15,7]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 节点总数 <= 1000
 *
 * @author zhou.xu
 * @since 2023/7/5 22:26
 */
public class LevelOrderPrintTest {

    public int[] levelOrder(TreeNode root) {
        int[] nums = new int[1000];
        int N = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            if (node == null) {
                continue;
            }
            queue.addLast(node.left);
            queue.addLast(node.right);
            nums[N] = node.val;
            N++;
        }
        return Arrays.copyOf(nums, N);
    }
}
