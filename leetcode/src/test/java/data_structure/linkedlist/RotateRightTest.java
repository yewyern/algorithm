package data_structure.linkedlist;

import utils.ListNode;

/**
 * <a href="https://leetcode.cn/problems/rotate-list/">61. 旋转链表</a>
 * @author xuzhou
 * @since 2023/12/26 15:51
 */
public class RotateRightTest {

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        ListNode[] nodes = new ListNode[500];
        int size = 0;
        while (head != null) {
            nodes[size++] = head;
            head = head.next;
        }
        int newHeadIndex = size - (k % size);
        if (newHeadIndex == 0 || newHeadIndex == size) {
            return nodes[0];
        }
        nodes[size - 1].next = nodes[0];
        nodes[newHeadIndex - 1].next = null;
        return nodes[newHeadIndex];
    }

}
