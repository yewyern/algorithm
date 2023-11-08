package data_structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii">117. 填充每个节点的下一个右侧节点指针 II</a>
 * @author xuzhou
 * @since 2023/11/7 15:01
 */
public class PopulateNextRightPointerTest {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        List<Node> curr = new ArrayList<>();
        curr.add(root);
        while (!curr.isEmpty()) {
            int size = curr.size();
            for (int i = 0; i < size - 1; i++) {
                curr.get(i).next = curr.get(i + 1);
            }
            List<Node> next = new ArrayList<>();
            for (Node node : curr) {
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            curr = next;
        }
        return root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
