package data_structure.linkedlist;

import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;

/**
 * @author zhou.xu
 * @since 2020/10/9 14:08
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public ListNode reverseListRecursive(ListNode head) {
        return head != null ? reverseListRecursive(head, null) : null;
    }

    public ListNode reverseListRecursive(ListNode curr, ListNode next) {
        ListNode nextTemp = curr.next;
        if (nextTemp != null) {
            nextTemp = reverseListRecursive(nextTemp, curr);
        } else {
            nextTemp = curr;
        }
        curr.next = next;
        return nextTemp;
    }

    public void test(int... nums) {
        ListNode head = ListUtils.toListNodes(nums);
        System.out.println("before = " + head);
        ListNode node = reverseListRecursive(head);
        System.out.println("after = " + node);
    }

    @Test
    public void test() {
        test(1, 2, 3, 4, 5);
        test(1, 2, 3, 4, 5, 7, 8);
    }
}
