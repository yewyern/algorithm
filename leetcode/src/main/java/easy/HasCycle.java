package easy;

import java.util.HashSet;
import java.util.Set;
import utils.ListNode;

/**
 * <p>给定一个链表，判断链表中是否有环。
 * <p>为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * <p>示例 1：
 * <p>输入：head = [3,2,0,-4], pos = 1
 * <p>输出：true
 * <p>解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * <p>示例 2：
 * <p>输入：head = [1,2], pos = 0
 * <p>输出：true
 * <p>解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * <p>示例 3：
 * <p>输入：head = [1], pos = -1
 * <p>输出：false
 * <p>解释：链表中没有环。
 *
 * <p>进阶：
 * <p>你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class HasCycle {

    public boolean hasCycleWithSet(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        HasCycle a = new HasCycle();
        ListNode head = generate(1, 3, 2, 0, -4);
        System.out.println("head = " + head);
        System.out.println("hasCycle = " + a.hasCycle(head));
        ListNode head2 = generate(0, 1, 2);
        System.out.println("hasCycle = " + a.hasCycle(head2));
        ListNode head3 = generate(-1, 1);
        System.out.println("hasCycle = " + a.hasCycle(head3));
    }

    public static ListNode generate(int pos, int... heads) {
        ListNode head = null, cycleNode = null, curr = null;
        for (int i = 0; i < heads.length; i++) {
            if (i == 0) {
                head = new ListNode(heads[i]);
                curr = head;
            } else {
                curr = curr.setNext(heads[i]);
            }
            if (pos == i) {
                cycleNode = curr;
            }
        }
        if (curr != null) {
            curr.next = cycleNode;
        }
        return head;
    }
}