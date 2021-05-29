package data_structure.linkedlist;

import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;

/**
 * <p>给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * <p>
 * <p>如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * <p>您可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * <p>
 * <p>示例：
 * <p>
 * <p>输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * <p>输出：7 -> 0 -> 8
 * <p>原因：342 + 465 = 807
 *
 * @author zhou.xu
 * @date 2020/8/4 16:56
 */
public class AddTwoNumberLinkedList {

    public ListNode addTwoNumbersComparison(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode curr = null;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int res = l1.val + l2.val + carry;
            carry = res >= 10 ? 1 : 0;
            res %= 10;
            if (head == null) {
                head = new ListNode(res);
                curr = head;
            } else {
                curr.next = new ListNode(res);
                curr = curr.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        l1 = l1 != null ? l1 : l2;
        while (l1 != null && carry > 0) {
            int res = l1.val + carry;
            carry = res >= 10 ? 1 : 0;
            res %= 10;
            curr.next = new ListNode(res);
            curr = curr.next;
            l1 = l1.next;
        }
        if (l1 != null && curr != null) {
            curr.next = l1;
        }
        if (carry == 1) {
            curr.next = new ListNode(1);
        }
        return head;
    }

    public void test(int[] nums1, int[] nums2) {
        ListNode l1 = ListUtils.toListNodes(nums1);
        ListNode l2 = ListUtils.toListNodes(nums2);
        System.out.println("l1 = " + ListUtils.toString(l1));
        System.out.println("l2 = " + ListUtils.toString(l2));
        System.out.println("rs = " + ListUtils.toString(addTwoNumbersComparison(l1, l2)));
        System.out.println("------------------------");
    }

    @Test
    public void test() {
        test(new int[]{2, 4, 3}, new int[]{5, 6, 4});
        test(new int[]{5}, new int[]{5});
        test(new int[]{1}, new int[]{9, 9, 9, 9, 8, 9, 9, 9});
    }
}
