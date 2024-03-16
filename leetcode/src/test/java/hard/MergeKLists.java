package hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;

/**
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists">23. 合并 K 个升序链表</a>
 * <p>给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * <p>请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * <p>示例 1：
 * <p>
 * <p>输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * <p>输出：[1,1,2,3,4,4,5,6]
 * <p>解释：链表数组如下：
 * <p>[
 * <p>  1->4->5,
 * <p>  1->3->4,
 * <p>  2->6
 * <p>]
 * <p>将它们合并到一个有序链表中得到。
 * <p>1->1->2->3->4->4->5->6
 * <p>示例 2：
 * <p>
 * <p>输入：lists = []
 * <p>输出：[]
 * <p>示例 3：
 * <p>
 * <p>输入：lists = [[]]
 * <p>输出：[]
 * <p>
 *
 * @author zhou.xu
 * @since 2020/10/22 17:59
 */
public class MergeKLists {

    /**
     * 归并排序
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return partition(lists, 0, lists.length - 1);
    }

    private ListNode partition(ListNode[] lists, int l, int r) {
        if (r == l) {
            return lists[l];
        }
        if (r == l + 1) {
            return merge(lists[l], lists[r]);
        }
        int m = (l + r) >> 1;
        return merge(partition(lists, l, m), partition(lists, m + 1, r));
    }

    /**
     * 递归合并
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode merge(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        if (a.val < b.val) {
            a.next = merge(a.next, b);
            return a;
        } else {
            b.next = merge(a, b.next);
            return b;
        }
    }

    /**
     * 优先队列，入队排序，
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists0(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null) {
                queue.offer(tail.next);
            }
        }
        return head.next;
    }

    /**
     * 数组内的链表一一合并
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            if (lists[i] != null) {
                res = mergeLoop(res, lists[i]);
            }
        }
        return res;
    }

    /**
     * 循环合并
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode mergeLoop(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (a != null && b != null) {
            if (a.val < b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = a != null ? a : b;
        return head.next;
    }

    /**
     * <p> 对数组循环，查找最小的一个
     * <p> 时间复杂度为数组长度*最大链表长度，即m*n,O(n^2)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode head = new ListNode(0);
        ListNode curr = head;
        boolean end;
        do {
            ListNode min = null;
            int minIndex = 0;
            end = true;
            for (int i = 0; i < lists.length; i++) {
                if (min == null) {
                    min = lists[i];
                    minIndex = i;
                } else if (lists[i] != null) {
                    if (lists[i].val < min.val) {
                        min = lists[i];
                        minIndex = i;
                    }
                    end = false;
                }
            }
            if (min != null) {
                curr.next = min;
                curr = min;
                lists[minIndex] = min.next;
            } else {
                break;
            }
        } while (!end);
        return head.next;
    }

    public void test(int[][] nums) {
        int length = nums.length;
        ListNode[] nodes = new ListNode[length];
        for (int i = 0; i < length; i++) {
            int[] num = nums[i];
            ListNode node = ListUtils.toListNodes(num);
            nodes[i] = node;
        }
        ListNode listNode = mergeKLists(nodes);
        System.out.println("listNode = " + listNode);
    }

    @Test
    public void test() {
        test(new int[0][]);
        test(new int[1][]);
        test(new int[][]{{1, 2, 3}});
        test(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}});
        test(new int[][]{{-2, -1, -1, -1}, {}});
    }
}
