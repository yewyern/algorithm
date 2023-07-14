package data_structure.stack;

/**
 * @author xuzhou
 * @since 2023/7/14 18:25
 */
public class MyLinkedStack {

    private Node head = null;

    public MyLinkedStack() {
    }

    public void push(int a) {
        head = new Node(a, head);
    }

    public int pop() {
        int val = head.val;
        head = head.next;
        return val;
    }

    public int peek() {
        return head.val;
    }

    public boolean isEmpty() {
        return head == null;
    }

    static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
