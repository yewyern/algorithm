package data_structure.tree;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 36. 二叉搜索树与双向链表</a>
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * <p>
 * <p>
 * <p>
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 * <p>
 * <p><img src="https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png"/>
 * <p>
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * <p>
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 * <p>
 * <p><img src="https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png"/>
 * <p>
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 * <p>
 * 注意：本题与主站 426 题相同：<a href="https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/">...</a>
 * <p>
 * 注意：此题对比原题有改动。
 *
 * @author xuzhou
 * @since 2023/7/12 15:21
 */
public class BinarySearchTreeToSortedDoubleLinkedListTest {

    @Test
    public void test() {
        // 4,2,5,1,3
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(2, node1, node2);
        Node node4 = new Node(5);
        Node node5 = new Node(4, node3, node4);
        Node node = treeToDoublyList(node5);
        System.out.println("node = " + node);
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            root.left = root;
            root.right = root;
            return root;
        }
        Node left = treeToDoublyList(root.left);
        Node right = treeToDoublyList(root.right);
        root.left = null;
        root.right = null;
        if (left != null && right != null) {
            link(left, root);
            link(left, right);
            return left;
        } else if (left != null) {
            link(left, root);
            return left;
        } else {
            link(root, right);
            return right;
        }
    }

    private void link(Node left, Node right) {
        Node leftTail = left.left == null ? left : left.left;
        Node rightTail = right.left == null ? right : right.left;
        left.left = rightTail;
        rightTail.right = left;
        right.left = leftTail;
        leftTail.right = right;
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        Set<Node> set = new HashSet<>();
        Node curr = this.right;
        while (curr != null && !set.contains(curr)) {
            sb.append("->").append(curr.val);
            set.add(curr);
            curr = curr.right;
        }
        return sb.toString();
    }
}
