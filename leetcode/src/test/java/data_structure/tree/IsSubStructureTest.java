package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/description/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 26. 树的子结构</a>
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * <p>
 * 3
 * <p>
 * / \
 * <p>
 * 4   5
 * <p>
 * / \
 * <p>
 * 1   2
 * 给定的树 B：
 * <p>
 * 4
 * <p>
 * /
 * <p>
 * 1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 10000
 *
 * @author xuzhou
 * @since 2023/7/6 17:01
 */
public class IsSubStructureTest {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return isSame(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSame(TreeNode a, TreeNode b) {
        // 题目中有个隐藏条件是A里面有子节点，但是B没有子节点时，也是子树
        if (a == b || b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSame(a.left, b.left) && isSame(a.right, b.right);
    }
}
