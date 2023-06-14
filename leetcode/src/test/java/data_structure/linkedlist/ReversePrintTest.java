package data_structure.linkedlist;

import utils.ListNode;

import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 06. 从尾到头打印链表</a>
 * <p>
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * <p>
 * 限制：
 * 0 <= 链表长度 <= 10000
 *
 * @author zhou.xu
 * @since 2023/6/11 22:22
 */
public class ReversePrintTest {

    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.add(cur.val);
            cur = cur.next;
        }
        int i = 0;
        int[] ans = new int[stack.size()];
        while (!stack.isEmpty()) {
            ans[i++] = stack.pop();
        }
        return ans;
    }
}
