package data_structure.linkedlist;

import utils.ListNode;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * @author : zhou.xu
 * @date : 2020/5/28 9:19
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        boolean firstA = true, firstB = true;
        while (true) {
            if (pa == null) {
                if (firstA) {
                    pa = headB;
                    firstA = false;
                } else {
                    return null;
                }
            }
            if (pb == null) {
                if (firstB) {
                    pb = headA;
                    firstB = false;
                } else {
                    return null;
                }
            }
            if (pa == pb && pa.val != 0) {
                return pa;
            }
            pa = pa.next;
            pb = pb.next;
        }
    }

    public static void main(String[] args) {
        GetIntersectionNode node = new GetIntersectionNode();
        IntersectionNodeGenerator.generate(new int[]{4, 1, 8, 4, 5}, new int[]{5, 0, 1, 8, 4, 5}, 2, 3);
        ListNode listNode = node
            .getIntersectionNode(IntersectionNodeGenerator.headA, IntersectionNodeGenerator.headB);
        System.out.println("listNode = " + listNode);
    }

    private static class IntersectionNodeGenerator {

        private static ListNode headA;
        private static ListNode headB;

        public static void generate(int[] listA, int[] listB, int skipA, int skipB) {
            ListNode currA = generate(listA, skipA, true);
            ListNode currB = generate(listB, skipB, false);
            for (int i = skipA, j = skipB; i < listA.length && j < listB.length; i++, j++) {
                ListNode listNode = new ListNode(listA[i]);
                if (currA != null) {
                    currA.next = listNode;
                }
                if (currB != null) {
                    currB.next = listNode;
                }
                currA = listNode;
                currB = listNode;
            }
            System.out.println("headA = " + headA);
            System.out.println("headB = " + headB);
        }

        private static ListNode generate(int[] list, int skip, boolean isHeadA) {
            ListNode curr = null;
            for (int i = 0; i < skip && i < list.length; i++) {
                ListNode listNode = new ListNode(list[i]);
                if (curr != null) {
                    curr.next = listNode;
                } else if (isHeadA) {
                    headA = listNode;
                } else {
                    headB = listNode;
                }
                curr = listNode;
            }
            return curr;
        }


    }
}