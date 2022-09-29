package data_structure.stack;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * <p>使用队列实现栈的下列操作：
 * <p>
 * <p>push(x) -- 元素 x 入栈
 * <p>pop() -- 移除栈顶元素
 * <p>top() -- 获取栈顶元素
 * <p>empty() -- 返回栈是否为空
 * <p>注意:
 * <p>
 * <p>你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * <p>你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * <p>你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 * <p>
 *
 * @author zhou.xu
 * @since 2020/10/9 9:31
 */
public class MyStackByQueue {

    private final Queue<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public MyStackByQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return Objects.requireNonNull(queue.poll());
    }

    /**
     * Get the top element.
     */
    public int top() {
        return Objects.requireNonNull(queue.peek());
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
