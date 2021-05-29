package utils;

/**
 * @author xuzhou
 * @date 2021/5/29 18:14
 */
public class ListUtils {

    public static String toString(ListNode head) {
        if (head == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(head.val);
        ListNode curr = head.next;
        while (curr != null) {
            sb.append(" -> ").append(curr.val);
            curr = curr.next;
        }
        return sb.toString();
    }

    public static ListNode toListNodes(int... nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode head = new ListNode(nums[0]);
        ListNode curr = head;
        for (int i = 1; i < nums.length; i++) {
            curr.next = new ListNode(nums[i]);
            curr = curr.next;
        }
        return head;
    }

    public static ListNode newRandomList(int len, int max) {
        return newRandomList(len, 0, max);
    }

    public static ListNode newRandomList(int len, int min, int max) {
        int[] nums = RandomArray.generate(len, min, max);
        return toListNodes(nums);
    }

    public static ListNode newRandomSortedList(int len, int max) {
        return newRandomSortedList(len, 0, max);
    }

    public static ListNode newRandomSortedList(int len, int min, int max) {
        int[] nums = RandomArray.generateSortedArray(len, min, max);
        return toListNodes(nums);
    }

    public static ListNode newRandomLengthSortedList(int maxLen, int max) {
        int[] nums = RandomArray.generateRandomLengthSortedArray(maxLen, max);
        return toListNodes(nums);
    }

    public static boolean isNotEqual(ListNode l1, ListNode l2) {
        return !isEqual(l1, l2);
    }

    public static boolean isEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null && l2 == null) {
            return true;
        }
        return false;
    }

}
