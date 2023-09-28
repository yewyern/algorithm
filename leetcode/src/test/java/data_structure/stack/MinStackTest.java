package data_structure.stack;

/**
 * <a href="https://leetcode.cn/problems/min-stack">155. 最小栈</a>
 * <a href="https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof">LCR 147. 最小栈</a>
 * <p>设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * <p>push(x) —— 将元素 x 推入栈中。
 * <p>pop() —— 删除栈顶的元素。
 * <p>top() —— 获取栈顶元素。
 * <p>getMin() —— 检索栈中的最小元素。
 * <p>
 * <p>提示：pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * @author xzer
 */
public class MinStackTest {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("minStack.getMin() = " + minStack.getMin());
        System.out.println("minStack.top() = " + minStack.top());
        minStack.pop();
        System.out.println("minStack.top() = " + minStack.top());
        System.out.println("minStack.getMin() = " + minStack.getMin());
        minStack.pop();
        System.out.println("minStack.top() = " + minStack.top());
        System.out.println("minStack.getMin() = " + minStack.getMin());
    }
}

class MinStack {

    private Node top;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    /**
     * 将元素 x 推入栈中
     *
     * @param x
     */
    public void push(int x) {
        int min = top == null ? x : Math.min(top.min, x);
        top = new Node(x, min, top);
    }

    /**
     * 删除栈顶的元素
     */
    public void pop() {
        top = top.next;
    }

    /**
     * @return 获取栈顶元素
     */
    public int top() {
        return top.val;
    }

    /**
     * @return 检索栈中的最小元素
     */
    public int getMin() {
        return top.min;
    }

    private static class Node {

        final int val;
        final int min;
        final Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
