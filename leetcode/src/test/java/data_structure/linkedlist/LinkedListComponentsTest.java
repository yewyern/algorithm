package data_structure.linkedlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import utils.ListNode;
import utils.ListUtils;

/**
 * <a href="https://leetcode.cn/problems/linked-list-components/">817. 链表组件</a>
 * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表 nums，该列表是上述链表中整型值的一个子集。
 * <p>
 * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: head = [0,1,2,3], nums = [0,1,3] 输出: 2 解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入: head = [0,1,2,3,4], nums = [0,3,1,4] 输出: 2 解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 *
 * @author zhou.xu
 * @since 2022/10/12 22:38
 */
public class LinkedListComponentsTest {

    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int count = 0;
        boolean inSet = false;
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val)) {
                if (!inSet) {
                    count++;
                    inSet = true;
                }
            } else {
                inSet = false;
            }
            cur = cur.next;
        }
        return count;
    }

    private void test(int[] nodes, int[] nums, int expectedResult) {
        int res = numComponents(ListUtils.toListNodes(nodes), nums);
        if (res == expectedResult) {
            System.out.println(Arrays.toString(nodes) + ", " + Arrays.toString(nums) + ", 预期结果：" + expectedResult + ", 实际结果：" + res + ", 通过！");
        } else {
            System.out.println(Arrays.toString(nodes) + ", " + Arrays.toString(nums) + ", 预期结果：" + expectedResult + ", 实际结果：" + res + ", 不通过！");
        }
    }

    @Test
    public void test() {
        test(new int[]{0, 1, 2, 3}, new int[]{0, 1, 3}, 2);
        test(new int[]{0, 1, 2, 3, 4}, new int[]{0, 3, 1, 4}, 2);
    }
}
