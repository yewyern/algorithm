package utils;

import java.util.Random;

/**
 * @author zhou.xu
 * @date 2020/2/24 19:20
 */
public class ArrayUtils {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static int[] newRandomArray(int len, int max) {
        return newRandomArray(len, 0, max);
    }

    public static int[] newRandomArray(int len, int min, int max) {
        int[] nums = new int[len];
        randomFill(nums, min, max);
        return nums;
    }

    public static void randomFill(int[] nums, int min, int max) {
        for (int i = 0; i < nums.length; i++) {
            int i1 = RANDOM.nextInt(max - min);
            nums[i] = i1 + min;
        }
    }

    public static void randomFill(int[] nums, int max) {
        randomFill(nums, 0, max);
    }

    public static <T> String toString(int[] objs) {
        if (objs == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < objs.length; i++) {
            if (i == objs.length - 1) {
                sb.append(objs[i]);
            } else {
                sb.append(objs[i] + ", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static <T> String toString(T[] objs) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < objs.length; i++) {
            if (i == objs.length - 1) {
                sb.append(objs[i]);
            } else {
                sb.append(objs[i] + ", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String toString(ListNode head) {
        if (head == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(head.val);
        ListNode curr = head.next;
        while (curr != null) {
            sb.append(" -> " + curr.val);
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

}
