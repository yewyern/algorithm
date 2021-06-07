package data_structure.linkedlist;

import java.math.BigDecimal;
import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;
import utils.RandomArray;

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode curr = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum > 9 ? 1 : 0;
            sum %= 10;
            curr.next = new ListNode(sum);
            curr = curr.next;
        }
        if (carry == 1) {
            curr.next = new ListNode(1);
        }
        return head.next;
    }

    public ListNode addTwoNumbersComparison(ListNode l1, ListNode l2) {
        // TODO
        BigDecimal num1 = convertToBigDecimal(l1);
        BigDecimal num2 = convertToBigDecimal(l2);
        BigDecimal sum = num1.add(num2);
        ListNode tail = null;
        char[] cs = sum.toString().toCharArray();
        for (char c : cs) {
            ListNode head = new ListNode((int) (c - '0'));
            head.next = tail;
            tail = head;
        }
        return tail;
    }

    private BigDecimal convertToBigDecimal(ListNode l1) {
        StringBuilder str = new StringBuilder();
        while (l1 != null) {
            str.insert(0, l1.val);
            l1 = l1.next;
        }
        return new BigDecimal(str.toString());
    }

    public void test(int[] nums1, int[] nums2) {
        trimEndZero(nums1);
        ListNode l1 = ListUtils.toListNodes(nums1);
        ListNode l2 = ListUtils.toListNodes(nums2);
        ListNode res1 = addTwoNumbers(l1, l2);
        ListNode res2 = addTwoNumbersComparison(l1, l2);
        if (ListUtils.isNotEqual(res1, res2)) {
            System.out.println("l1 = " + ListUtils.toString(l1));
            System.out.println("l2 = " + ListUtils.toString(l2));
            System.out.println("rs = " + ListUtils.toString(res1));
            System.out.println("rs = " + ListUtils.toString(res2));
            System.out.println("------------------------");
        }
    }

    private void trimEndZero(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            
        }
    }

    @Test
    public void test() {
//        test(new int[]{2, 4, 3}, new int[]{5, 6, 4});
//        test(new int[]{5}, new int[]{5});
//        test(new int[]{1}, new int[]{9, 9, 9, 9, 8, 9, 9, 9});
        for (int i = 0; i < 10000; i++) {
            test(RandomArray.generateRandomLengthNoEmptyArray(100, 10), RandomArray.generateRandomLengthNoEmptyArray(100, 10));
        }
    }
}
