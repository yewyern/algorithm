package data_structure.tree;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/average-of-levels-in-binary-tree">637. 二叉树的层平均值</a>
 * @author xuzhou
 * @since 2023/12/28 16:44
 */
public class AverageOfLevels {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Data> levels = new ArrayList<>();
        process(root, 0, levels);
        List<Double> ans = new ArrayList<>();
        for (Data level : levels) {
            ans.add(level.sum / level.count);
        }
        return ans;
    }

    private void process(TreeNode node, int level, List<Data> levels) {
        if (node == null) {
            return;
        }
        if (level == levels.size()) {
            levels.add(new Data());
        }
        Data data = levels.get(level);
        data.sum += node.val;
        data.count++;
        process(node.left, level + 1, levels);
        process(node.right, level + 1, levels);
    }

    private static class Data {
        double sum;
        int count;
    }
}
