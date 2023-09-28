package data_structure.linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;

/**
 * <a href="https://leetcode.cn/problems/palindrome-linked-list">234. 回文链表</a>
 * <a href="https://leetcode.cn/problems/aMhZSa">LCR 027. 回文链表</a>
 * <p>请判断一个链表是否为回文链表。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: 1->2
 * <p>输出: false
 * <p>示例 2:
 * <p>
 * <p>输入: 1->2->2->1
 * <p>输出: true
 * <p>进阶：
 * <p>你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author zhou.xu
 * @since 2020/10/9 13:48
 */
public class IsPalindromeList {

    /**
     * <p>思路1：转成数组，首尾双指针比较
     * <p>时间复杂度O(n)
     * <p>空间复杂度O(n)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        // 双指针取长度
        int len = getLen(head);
        // 转数组
        int[] nums = new int[len];
        int i = convert2Array(head, nums);
        // 首尾双指针比较
        for (int j = 0, k = i - 1; j < k; j++, k--) {
            if (nums[j] != nums[k]) {
                return false;
            }
        }
        return true;
    }

    private int convert2Array(ListNode head, int[] nums) {
        ListNode curr = head;
        int i = 0;
        while (curr != null) {
            nums[i++] = curr.val;
            curr = curr.next;
        }
        return i;
    }

    private int getLen(ListNode head) {
        int len = 0;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            len++;
        }
        len *= 2;
        if (fast != null) {
            len++;
        }
        return len;
    }

    public boolean isPalindromeByLinkedList(ListNode head) {
        if (head == null) {
            return true;
        }
        // 链表
        LinkedList<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        while (list.size() >= 2) {
            if (!list.pollFirst().equals(list.pollLast())) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeByArrayList(ListNode head) {
        if (head == null) {
            return true;
        }
        // list
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>思路2：快慢指针取中间节点，翻转后半部分节点，再遍历比较两个链表
     * <p>时间复杂度O(n)
     * <p>空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 取中点
        ListNode center = getCenter(head);
        // 翻转链表
        ListNode reverseList = reverseList(center);
        while (head != null && reverseList != null) {
            if (head.val != reverseList.val) {
                return false;
            }
            head = head.next;
            reverseList = reverseList.next;
        }
        return true;
    }

    private ListNode getCenter(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

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

    public void test(int... nums) {
        ListNode head = ListUtils.toListNodes(nums);
        System.out.println("head = " + head);
        System.out.println("head = " + head + ", " + isPalindrome2(head));
    }

    @Test
    public void test() {
        test(-129, -129);
        test(1, 2);
        test(1, 2, 3, 2, 1);
        test(1, 2, 2, 1);
    }
}
