package normal;

import utils.ListNode;

/**
 * <p>给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * <p>示例：
 * <p>
 * <p>给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * <p>当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>说明：
 * <p>
 * <p>给定的 n 保证是有效的。
 * <p>
 * <p>进阶：
 * <p>
 * <p>你能尝试使用一趟扫描实现吗？
 *
 * @author zhou.xu
 * @since 2020/10/14 10:18
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }
        if (fast == null) {
            return head.next;
        }
        ListNode low = head;
        while (fast.next != null) {
            fast = fast.next;
            low = low.next;
        }
        low.next = low.next.next;
        return head;
    }
}
