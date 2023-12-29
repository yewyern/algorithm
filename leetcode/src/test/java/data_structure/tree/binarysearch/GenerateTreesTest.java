package data_structure.tree.binarysearch;

import org.junit.Test;
import utils.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GenerateTreesTest {

    private static final List<TreeNode> NULL_NODES = Arrays.asList(new TreeNode[]{null});

    /**
     * <a href="https://leetcode.cn/problems/unique-binary-search-trees">96. 不同的二叉搜索树</a>
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * 1 <= n <= 8
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int l, int r) {
        if (l > r) {
            return NULL_NODES;
        }
        List<TreeNode> treeNodes = process(l, NULL_NODES, generateTrees(l + 1, r));
        for (int i = l + 1; i < r; i++) {
            treeNodes.addAll(process(i, generateTrees(l, i - 1), generateTrees(i + 1, r)));
        }
        if (l < r) {
            treeNodes.addAll(process(r, generateTrees(l, r - 1), NULL_NODES));
        }
        return treeNodes;
    }

    private List<TreeNode> process(int parentVal, List<TreeNode> leftNodes, List<TreeNode> rightNodes) {
        List<TreeNode> ans = new LinkedList<>();
        for (TreeNode left : leftNodes) {
            for (TreeNode right : rightNodes) {
                TreeNode parent = new TreeNode(parentVal);
                parent.left = left;
                parent.right = right;
                ans.add(parent);
            }
        }
        return ans;
    }

    @Test
    public void test() {
        List<TreeNode> treeNodes = generateTrees(8);
        for (TreeNode treeNode : treeNodes) {
            System.out.println(treeNode);
        }
    }
}
