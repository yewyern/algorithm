package data_structure.queue;


/**
 * <a href="https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 30. 包含min函数的栈</a>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * <p>
 * <p>
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 * <p>
 * <p>
 * 提示：
 * <p>
 * 各函数的调用总次数不超过 20000 次
 * <p>
 * <p>
 * 注意：本题与主站 155 题相同：<a href="https://leetcode-cn.com/problems/min-stack/">155. 最小栈</a>
 *
 * @author xuzhou
 * @since 2023/6/15 16:00
 */
public class MinStack {

    private Node root;

    public MinStack() {

    }

    public void push(int x) {
        if (root == null) {
            root = new Node(x, x, null);
        } else {
            root = new Node(x, Math.min(x, root.min), root);
        }
    }

    public void pop() {
        if (root != null) {
            root = root.pre;
        }
    }

    public int top() {
        return root.val;
    }

    public int min() {
        return root.min;
    }

    private static class Node {
        final int val;
        final int min;
        final Node pre;

        public Node(int val, int min, Node pre) {
            this.val = val;
            this.min = min;
            this.pre = pre;
        }
    }
}
