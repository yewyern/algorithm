package data_structure.linkedlist;

import utils.ListNode;

/**
 * <a href="https://leetcode.cn/problems/sort-list">148. 排序链表</a>
 * @author xuzhou
 * @since 2024-01-05 22:25
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(fast));
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode head = new ListNode();
        ListNode prev = head;
        while (a != null && b != null) {
            if (a.val < b.val) {
                prev.next = a;
                a = a.next;
            } else {
                prev.next = b;
                b = b.next;
            }
            prev = prev.next;
        }
        prev.next = a != null ? a : b;
        return head.next;
    }
}
