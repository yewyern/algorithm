package data_structure.linkedlist;

import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/merge-two-sorted-lists">合并两个有序链表</a>
 * <a href="https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/?envType=study-plan-v2&envId=coding-interviews">合并两个有序链表</a>
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * <p>
 * 示例1：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 1000
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
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(Integer.MIN_VALUE);
        ListNode pre = root;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 != null ? l1 : l2;
        return root.next;
    }

    public static ListNode mergeTwoListsComparison(ListNode l1, ListNode l2) {
        // 1、都放到1个数组里面O(n)
        List<Integer> list = new ArrayList<>();
        while (l1 != null) {
            list.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            list.add(l2.val);
            l2 = l2.next;
        }
        // 2、数组排序 O(nlogn)
        list.sort(Integer::compareTo);
        // 3、转成链表 O(n)
        ListNode head = new ListNode(-1);
        ListNode curr = head;
        for (Integer val : list) {
            ListNode temp = new ListNode(val);
            curr.next = temp;
            curr = temp;
        }
        return head.next;
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            ListNode l1 = ListUtils.newRandomLengthSortedList(RandomUtils.nextInt(100), 100);
            ListNode l2 = ListUtils.newRandomLengthSortedList(RandomUtils.nextInt(100), 100);
            ListNode r1 = mergeTwoLists(l1, l2);
            ListNode r2 = mergeTwoListsComparison(l1, l2);
            if (ListUtils.isNotEqual(r1, r2)) {
                print(l1);
                print(l2);
                print(r1);
                print(r2);
                System.out.println("------------------------------");
            }
        }
    }

    public static void print(ListNode l) {
        System.out.println(ListUtils.toString(l));
    }
}
