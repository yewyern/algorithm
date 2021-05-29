package data_structure.queue;

import java.util.Stack;

/**
 * <p>使用栈实现队列的下列操作：
 * <p>
 * <p>push(x) -- 将一个元素放入队列的尾部。
 * <p>pop() -- 从队列首部移除元素。
 * <p>peek() -- 返回队列首部的元素。
 * <p>empty() -- 返回队列是否为空。
 * <p>
 * <p>示例:
 * <p>
 * <p>MyQueue queue = new MyQueue();
 * <p>
 * <p>queue.push(1);
 * <p>queue.push(2);
 * <p>queue.peek();  // 返回 1
 * <p>queue.pop();   // 返回 1
 * <p>queue.empty(); // 返回 false
 * <p> 
 * <p>
 * <p>说明:
 * <p>
 * <p>你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * <p>你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * <p>假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 * @author zhou.xu
 * @date 2020/10/9 13:23
 */
public class MyQueueByStack {

    /**
     * 存放正序
     */
    private Stack<Integer> s1;
    /**
     * 存放逆序
     */
    private Stack<Integer> s2;
    private int front;

    /**
     * Initialize your data structure here.
     */
    public MyQueueByStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (s1.isEmpty()) {
            front = x;
        }
        s1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (s2.isEmpty()) {
            return front;
        }
        return s2.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
