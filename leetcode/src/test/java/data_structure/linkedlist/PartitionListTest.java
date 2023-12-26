package data_structure.linkedlist;

import utils.ListNode;

/**
 * <a href="https://leetcode.cn/problems/partition-list/">86. 分隔链表</a>
 *
 * @author xuzhou
 * @since 2023/12/26 16:23
 */
public class PartitionListTest {

    public ListNode partition(ListNode head, int x) {
        ListNode lowHead = new ListNode();
        ListNode highHead = new ListNode();
        ListNode lowTail = lowHead;
        ListNode highTail = highHead;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = null;
            if (curr.val < x) {
                lowTail.next = curr;
                lowTail = curr;
            } else {
                highTail.next = curr;
                highTail = curr;
            }
            curr = next;
        }
        if (lowHead.next == null) {
            return highHead.next;
        }
        lowTail.next = highHead.next;
        return lowHead.next;
    }
}
