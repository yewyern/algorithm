package data_structure.stack;

/**
 * @author xuzhou
 * @since 2023/7/14 18:23
 */
public class MyStack {

    private int len = 0;
    private final int[] arr;

    public MyStack(int capacity) {
        this.arr = new int[capacity];
    }

    public void push(int a) {
        arr[len++] = a;
    }

    public int pop() {
        return arr[--len];
    }

    public int peek() {
        return arr[len - 1];
    }

    public boolean isEmpty() {
        return len == 0;
    }
}
