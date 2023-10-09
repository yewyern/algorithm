package data_structure.linkedlist;

import utils.ListNode;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii">82. 删除排序链表中的重复元素 II</a>
 * @author xuzhou
 * @since 2023/10/9 16:31
 */
public class DeleteDuplicatesFromLinkedList2Test {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode tempHead = new ListNode();
        tempHead.next = head;
        ListNode pre = tempHead, cur = head;
        while (cur != null) {
            boolean flag = false;
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                flag = true;
            }
            cur = cur.next;
            if (flag) {
                pre.next = cur;
            } else {
                pre = pre.next;
            }
        }
        return tempHead.next;
    }
}
