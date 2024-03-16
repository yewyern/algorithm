package dynamic_programing;

/**
 * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/">300. 最长递增子序列</a>
 * @author xuzhou
 * @since 2024/1/11 22:44
 */
public class LongestIncreasingSubsequenceTest {

    public int lengthOfLIS(int[] nums) {
        // 有序链表
        int max = 0;
        Node head = new Node(Integer.MIN_VALUE, 0);
        for (int num : nums) {
            int len = 1;
            Node prev = head;
            Node curr = head.next;
            while (curr != null && curr.end < num) {
                len = Math.max(len, curr.len + 1);
                prev = curr;
                curr = curr.next;
            }
            if (curr != null && curr.next != null && curr.next.end == num) {
                curr = curr.next;
                curr.len = Math.max(curr.len, len);
            } else {
                prev.next = new Node(num, len);
                prev.next.next = curr;
            }
            max = Math.max(max, len);
        }
        return max;
    }

    class Node {
        int end;
        int len;
        Node next;

        public Node(int end, int len) {
            this.end = end;
            this.len = len;
        }

    }

    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        count[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            int len = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    len = Math.max(len, count[j]);
                }
            }
            count[i] = len + 1;
            max = Math.max(max, count[i]);
        }
        return max;
    }
}
