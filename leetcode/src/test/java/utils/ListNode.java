package utils;

public class ListNode {

    public final int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode setNext(int x) {
        this.next = new ListNode(x);
        return next;
    }

    @Override
    public String toString() {
        return val + (next != null ? " -> " + next : "");
    }
}