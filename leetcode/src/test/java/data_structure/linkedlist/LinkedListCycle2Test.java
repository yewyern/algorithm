package data_structure.linkedlist;

import utils.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/">142. 环形链表 II</a>
 *
 * @author xuzhou
 * @since 2023/9/11 17:34
 */
public class LinkedListCycle2Test {

    public ListNode detectCycle(ListNode head) {
        // 快慢指针，快指针走2步，慢指针走一步
        // 如果快指针赶上慢指针，快指针再从头走一次，再次赶上时，刚好是环形链表起始点
        // 证明：设链表环起点索引是m,环长度是n
        // 第一次赶上时
        // 慢指针走的路程是 m + an + c = m + x
        // 快指针走的路程是 m + bn + c = 2m + 2x
        // 用上面的公式相减，可得：m = bn - an - x
        // 快指针从头再走m时，
        // 慢指针走到的位置是 m + x + (bn - an - x) => m + bn - an
        // 其中 bn - an 是整数环，所以慢指针刚好走到环的起点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null) {
            if (fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return head;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return null;
    }
}
