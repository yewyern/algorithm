package data_structure.tree.serializeanddeserialize;

import org.junit.Test;
import utils.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/serialize-and-deserialize-bst">449. 序列化和反序列化二叉搜索树</a>
 * @author xuzhou
 * @since 2023/9/15 10:06
 */
public class Codec3 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        process(root, sb);
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.pollFirst();
//            sb.append(node.val);
//            sb.append(",");
//            if (node.left != null) {
//                queue.addLast(node.left);
//            }
//            if (node.right != null) {
//                queue.addLast(node.right);
//            }
//        }
        return sb.toString();
    }

    private void process(TreeNode node, StringBuilder sb) {
        sb.append(node.val).append(',');
        if (node.left != null) {
            process(node.left, sb);
        }
        if (node.right != null) {
            process(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] sps = data.split(",");
        int n = sps.length;
        TreeNode root = new TreeNode(Integer.parseInt(sps[0]));
        for (int i = 1; i < n; i++) {
            TreeNode node = new TreeNode(Integer.parseInt(sps[i]));
            add(root, node);
        }
        return root;
    }

    private void add(TreeNode root, TreeNode node) {
        if (root.val < node.val) {
            if (root.right == null) {
                root.right = node;
                return;
            }
            add(root.right, node);
        } else {
            if (root.left == null) {
                root.left = node;
                return;
            }
            add(root.left, node);
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        String serialize = serialize(root);
        System.out.println(serialize);
        TreeNode deserialize = deserialize(serialize);
        System.out.println(deserialize);
    }
}
