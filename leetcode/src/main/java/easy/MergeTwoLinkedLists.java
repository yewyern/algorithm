package easy;

import utils.ListNode;

/**
 * 合并两个有序链表
 *
 * @author xzer
 */
public class MergeTwoLinkedLists {

    //将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
    //
    //示例：
    //
    //输入：1->2->4, 1->3->4
    //输出：1->1->2->3->4->4

    //    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if (l1 == null) {
//            return l2;
//        }
//        if (l2 == null) {
//            return l1;
//        }
//        ListNode resHead;
//        if (l1.val <= l2.val) {
//            resHead = l1;
//            l1 = l1.next;
//        } else {
//            resHead = l2;
//            l2 = l2.next;
//        }
//        ListNode next = resHead;
//        while (true) {
//            if (l1 == null && l2 == null) {
//                return resHead;
//            } else {
//                if (l1 == null || (l2 != null && l1.val > l2.val)) {
//                    next.next = l2;
//                    l2 = l2.next;
//                } else {
//                    next.next = l1;
//                    l1 = l1.next;
//                }
//                next = next.next;
//            }
//        }
//    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prevNode = new ListNode(-1);
        ListNode pre = prevNode;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                pre.next = l2;
                l2 = l2.next;
            } else {
                pre.next = l1;
                l1 = l1.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return prevNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = generateList(1, 2, 4, 5, 6);
        ListNode l2 = generateList(1, 3, 4);
        print(l1);
        print(l2);
        print(mergeTwoLists(l1, l2));
    }

    private static ListNode generateList(int... arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode next = head;
        for (int i = 1; i < arr.length; i++) {
            next.next = new ListNode(arr[i]);
            next = next.next;
        }
        return head;
    }

    public static void print(ListNode l) {
        System.out.print(l.val);
        while (l.next != null) {
            l = l.next;
            System.out.print(" -> " + l.val);
        }
        System.out.println();
    }
}
