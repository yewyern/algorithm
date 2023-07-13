package data_structure.tree;

import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/">235. 二叉搜索树的最近公共祖先</a>
 * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 68 - I. 二叉搜索树的最近公共祖先</a>
 * <p>给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * <p>例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * <p>示例 1:
 * <p>输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * <p>输出: 6
 * <p>解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * <p>
 * <p>示例 2:
 * <p>输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * <p>输出: 2
 * <p>解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * <p> 
 * <p>说明:
 * <p>所有节点的值都是唯一的。
 * <p>p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * @author zhou.xu
 * @since 2020/10/9 15:52
 */
public class LowestCommonAncestorInBinarySearchTreeTest {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        return process(root, o1, o2).val;
    }

    private TreeNode process(TreeNode root, int o1, int o2) {
        if (root == null) {
            return null;
        }
        if (root.val == o1 || root.val == o2) {
            return root;
        }
        TreeNode left = process(root.left, o1, o2);
        TreeNode right = process(root.right, o1, o2);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
