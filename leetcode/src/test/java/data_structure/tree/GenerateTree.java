package data_structure.tree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import utils.TreeNode;

public class GenerateTree {

    /**
     * <a href="https://leetcode.cn/problems/unique-binary-search-trees">96. 不同的二叉搜索树</a>
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * 1 <= n <= 8
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        // 全排列问题
        Set<Integer> set = new HashSet<>(8);
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }
        return generateTrees(set);
    }

    private List<TreeNode> generateTrees(Set<Integer> set) {
        // todo

        return null;
    }

    @Test
    public void test() {
        for (int i = 1; i < 9; i++) {
            generateTrees(i);
        }
    }
}
