package data_structure.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii">117. 填充每个节点的下一个右侧节点指针 II</a>
 *
 * @author xuzhou
 * @since 2023/11/7 15:01
 */
public class PopulateNextRightPointerTest {

    @Test
    public void test() {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        node.left = node1;
        node.right = node2;
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        node1.left = node3;
        node1.right = node4;
//        Node node5 = new Node(6);
        Node node6 = new Node(7);
//        node2.left = node5;
        node2.right = node6;
        connect(node);
        System.out.println(node);
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.right != null) {
            root.right.next = process(root.next);
        }
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = process(root.next);
            }
        }
        connect(root.right);
        connect(root.left);
        return root;
    }

    private Node process(Node node) {
        while (node != null && node.left == null && node.right == null) {
            node = node.next;
        }
        if (node != null) {
            return node.left != null ? node.left : node.right;
        }
        return null;
    }

    public Node connect2(Node root) {
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

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
