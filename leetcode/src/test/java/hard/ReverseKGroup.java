package hard;

import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;

/**
 * <p>给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>k 是一个正整数，它的值小于或等于链表的长度。
 * <p>如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * <p>
 * <p>示例：
 * <p>
 * <p>给你这个链表：1->2->3->4->5
 * <p>当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * <p>说明：
 * <p>
 * <p>你的算法只能使用常数的额外空间。
 * <p>你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author zhou.xu
 * @date 2020/10/23 10:43
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        int count = 0;
        ListNode top = new ListNode(0);
        top.next = head;
        ListNode curr = head;
        ListNode tail = top;
        while (curr != null) {
            curr = curr.next;
            count++;
            if (count == k) {
                ListNode prev = curr;
                curr = tail.next;
                ListNode nextTail = curr;
                for (int i = 0; i < count; i++) {
                    ListNode nextTemp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = nextTemp;
                }
                tail.next = prev;
                tail = nextTail;
                curr = tail.next;
                count = 0;
            }
        }
        return top.next;
    }

    @Test
    public void test() {
        ListNode node = ListUtils.toListNodes(1, 2, 3, 4, 5);
        test(node, 2);
        node = ListUtils.toListNodes(1, 2, 3, 4, 5);
        test(node, 3);
    }

    public void test(ListNode head, int k) {
        System.out.println("head = " + head);
        ListNode node = reverseKGroup(head, k);
        System.out.println("res = " + node);
    }
}
