package easy;

import utils.TreeNode;

/**
 * +
 * <p>翻转一棵二叉树。
 * <p>
 * <p>示例：
 * <p>
 * <p>输入：
 * <p>
 * <p>     4
 * <p>   /   \
 * <p>  2     7
 * <p> / \   / \
 * <p>1   3 6   9
 * <p>输出：
 * <p>
 * <p>     4
 * <p>   /   \
 * <p>  7     2
 * <p> / \   / \
 * <p>9   6 3   1
 * <p>备注:
 * <p>这个问题是受到 Max Howell 的 原问题 启发的 ：
 * <p>
 * <p>谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 * @author zhou.xu
 * @date 2020/10/9 9:47
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
}
