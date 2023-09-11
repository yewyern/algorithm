package data_structure.linkedlist;

import utils.ListNode;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/reorder-list">143. 重排链表</a>
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 提示：
 * <p>
 * 链表的长度范围为 [1, 5 * 10^4]
 * 1 <= node.val <= 1000
 *
 * @author xuzhou
 * @since 2023/9/11 15:18
 */
public class ReorderListTest {

    private static final ListNode[] nodes = new ListNode[50000];

    public void reorderList(ListNode head) {
        // 1、求长度
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            nodes[n] = cur;
            cur = cur.next;
            nodes[n].next = null;
            n++;
        }
        // 2、双指针生成结果
        int l = 0, r = n - 1;
        while (l <= r) {
            if (r > l) {
                nodes[l].next = nodes[r];
            }
            if (cur != null) {
                cur.next = nodes[l];
            }
            cur = nodes[r];
            l++;
            r--;
        }
    }

    public void reorderList2(ListNode head) {
        // 1、求长度
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            n++;
        }
        // 2、复制到数组
        ListNode[] nodes = new ListNode[n];
        cur = head;
        for (int i = 0; i < n; i++) {
            nodes[i] = cur;
            cur = cur.next;
            nodes[i].next = null;
        }
        // 3、双指针生成结果
        cur = null;
        int l = 0, r = n - 1;
        while (l <= r) {
            if (r > l) {
                nodes[l].next = nodes[r];
            }
            if (cur != null) {
                cur.next = nodes[l];
            }
            cur = nodes[r];
            l++;
            r--;
        }
    }

    public void reorderList3(ListNode head) {
        LinkedList<ListNode> list = new LinkedList<>();
        while (head != null) {
            list.addLast(head);
            head = head.next;
            list.peekLast().next = null;
        }
        head = list.pollFirst();
        boolean tail = true;
        while (!list.isEmpty()) {
            head.next = tail ? list.pollLast() : list.pollFirst();
            tail = !tail;
            head = head.next;
        }
    }

}
