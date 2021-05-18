package normal;

import org.junit.Test;
import utils.ArrayUtils;
import utils.ListNode;

/**
 * 两两交换链表中的节点
 * <p>给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * <p>你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p> 
 * <p>
 * <p>示例:
 * <p>
 * <p>给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 *
 * @author zhou.xu
 * @date 2020/9/27 9:11
 */
public class SwapPairsTest {

    public ListNode swapPairs(ListNode head) {
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode curr = root;
        while (curr.next != null && curr.next.next != null) {
            ListNode next = curr.next;
            ListNode next1 = next.next;
            ListNode next2 = next1.next;
            curr.next = next1;
            next1.next = next;
            next.next = next2;
            curr = next;
        }
        return root.next;
    }

    public void test(int... nums) {
        ListNode head = ArrayUtils.toListNodes(nums);
        ListNode node = swapPairs(head);
        System.out.println("node = " + node);
    }

    @Test
    public void test() {
        test(1, 2, 3, 4);
    }
}
