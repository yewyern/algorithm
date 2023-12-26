package data_structure.linkedlist;

import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;

/**
 * <a href="https://leetcode.cn/problems/reverse-linked-list-ii">92. 反转链表 II</a>
 * @author xuzhou
 * @since 2023/12/26 11:05
 */
public class ReverseLinkedList2Test {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        if (left == 1) {
            return reverseKNodes(head, right);
        }
        ListNode prev = null;
        ListNode start = head;
        int i = 1;
        while (i < left) {
            prev = start;
            start = start.next;
            i++;
        }
        prev.next = reverseKNodes(start, right - left + 1);
        return head;
    }

    private ListNode reverseKNodes(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        while (k > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            k--;
        }
        head.next = curr;
        return prev;
    }

    @Test
    public void test() {
        ListNode head = ListUtils.toListNodes(1, 2, 3, 4, 5);
        System.out.println(head);
        ListNode listNode = reverseBetween(head, 2, 4);
        System.out.println(listNode);
    }
}
