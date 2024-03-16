package data_structure.tree;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/path-sum-ii/">113. 路径总和 II</a>
 * <a href="https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 34. 二叉树中和为某一值的路径</a>
 * <p>给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * <p>说明: 叶子节点是指没有子节点的节点。
 * <p>
 * <p>示例:
 * <p>给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * <p>              5
 * <p>             / \
 * <p>            4   8
 * <p>           /   / \
 * <p>          11  13  4
 * <p>         /  \      \
 * <p>        7    2      1
 * <p>输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * <p>输出：[[5,4,11,2],[5,8,4,5]]
 *
 * @author xuzhou
 * @since 2023/7/12 14:29
 */
public class PathSumTest {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root != null) {
            process(root, targetSum, res, new LinkedList<>());
        }
        return res;
    }

    private void process(TreeNode root, int targetSum, List<List<Integer>> res, LinkedList<Integer> path) {
        if (root.val == targetSum) {
            if (root.left == null && root.right == null) {
                // 收集结果
                path.addLast(root.val);
//                res.add((List<Integer>) path.clone());
                res.add(new ArrayList<>(path));
                path.pollLast();
            }
        }
        path.addLast(root.val);
        if (root.left != null) {
            process(root.left, targetSum - root.val, res, path);
        }
        if (root.right != null) {
            process(root.right, targetSum - root.val, res, path);
        }
        path.pollLast();
    }
}
