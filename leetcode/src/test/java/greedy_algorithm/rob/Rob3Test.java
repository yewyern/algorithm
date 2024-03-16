package greedy_algorithm.rob;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/house-robber-iii">337. 打家劫舍 III</a>
 * @author xuzhou
 * @since 2023/9/18 16:18
 */
public class Rob3Test {

    public int rob(TreeNode root) {
        int[] res = process(root);
        return Math.max(res[0], res[1]);
    }

    private int[]/*[包含当前节点的结果，不包含当前节点的结果]*/ process(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }
        int[] left = process(node.left);
        int[] right = process(node.right);
        // 如果要选当前节点，则子节点的值肯定不能包含
        // 如果不包含当前节点，则取子节点最大值相加
        return new int[] {node.val + left[1] + right[1], Math.max(left[0], left[1]) + Math.max(right[0], right[1])};
    }
}
