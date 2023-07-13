package data_structure.tree;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/">297. 二叉树的序列化与反序列化</a>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 10^4] 内
 * -1000 <= Node.val <= 1000
 */
public class Codec2 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean hasNode = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                list.addLast(node);
                if (node != null) {
                    hasNode = true;
                    queue.addLast(node.left);
                    queue.addLast(node.right);
                }
            }
            if (!hasNode) {
                break;
            }
        }
        while (!list.isEmpty() && list.peekLast() == null) {
            list.pollLast();
        }
        return list.stream().map(this::toString).collect(Collectors.joining(",", "[", "]"));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        if (data.startsWith("[") && data.endsWith("]")) {
            data = data.substring(1, data.length() - 1);
        }
        if (data.length() == 0) {
            return null;
        }
        String[] sps = data.split(",");
        TreeNode root = toTreeNode(sps[0]);
        TreeNode[] parents = new TreeNode[1];
        parents[0] = root;
        int size = 2;
        int i = 1, N = sps.length;
        while (i < N) {
            TreeNode[] next = new TreeNode[size];
            int r = 0;
            for (int j = 0; j < size && i < N; i++, j++) {
                TreeNode node = toTreeNode(sps[i]);
                if (node != null) {
                    next[r++] = node;
                    if ((j & 1) == 0) {
                        parents[j >> 1].left = node;
                    } else {
                        parents[j >> 1].right = node;
                    }
                }
            }
            parents = next;
            size = r << 1;
        }
        return root;
    }

    private String toString(TreeNode node) {
        return node == null ? "null" : String.valueOf(node.val);
    }

    private TreeNode toTreeNode(String s) {
        return "null".equals(s) ? null : new TreeNode(Integer.parseInt(s));
    }
}