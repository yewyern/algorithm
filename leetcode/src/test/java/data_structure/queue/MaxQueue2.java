package data_structure.queue;

import java.util.StringJoiner;

/**
 * @author zhou.xu
 * @since 2023/6/17 13:25
 */
public class MaxQueue2 {

    private Node head;
    private Node tail;
    private Node max;


    public MaxQueue2() {
    }

    public int max_value() {
        return max != null ? max.val : -1;
    }

    public void push_back(int value) {
        if (tail == null) {
            tail = new Node(value);
            head = tail;
            max = tail;
        } else {
            tail.next = new Node(value);
            tail = tail.next;
            // 如果大于当前的最大值，更新最大值
            if (max != null && tail.val > max.val) {
                max = tail;
            }
        }
    }

    public int pop_front() {
        if (head == null) {
            return -1;
        }
        int res = head.val;
        // 最后一个
        if (head == tail) {
            head = null;
            tail = null;
            max = null;
            return res;
        }
        // max要出队列
        if (head == max) {
            max = head.next;
            // 遍历所有的节点，判断下一个最大值
            Node next = max != null ? max.next : null;
            while (next != null) {
                if (next.val >= max.val) {
                    max = next;
                }
                next = next.next;
            }
        }
        head = head.next;
        return res;
    }

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("val=" + val)
                    .add("next=" + next)
                    .toString();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MaxQueue2.class.getSimpleName() + "[", "]")
                .add("head=" + head)
                .add("tail=" + tail)
                .add("max=" + max)
                .toString();
    }
}
