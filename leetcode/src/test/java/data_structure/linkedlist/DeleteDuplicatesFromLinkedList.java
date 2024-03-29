package data_structure.linkedlist;

import utils.ListNode;
import utils.ListUtils;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list">83. 删除排序链表中的重复元素</a>
 * <p>给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: 1->1->2
 * <p>输出: 1->2
 * <p>示例 2:
 * <p>
 * <p>输入: 1->1->2->3->3
 * <p>输出: 1->2->3
 */
public class DeleteDuplicatesFromLinkedList {

    public static void main(String[] args) {
        ListNode head = ListUtils.toListNodes(1, 1, 2, 3, 3);
        System.out.println("head = " + ListUtils.toString(head));
        DeleteDuplicatesFromLinkedList deleteLinkedList = new DeleteDuplicatesFromLinkedList();
        ListNode listNode = deleteLinkedList.deleteDuplicates(head);
        System.out.println("after delete head = " + ListUtils.toString(listNode));

    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}
