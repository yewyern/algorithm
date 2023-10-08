package data_structure.stack;


/**
 * <a href="https://leetcode.cn/problems/online-stock-span">901. 股票价格跨度</a>
 * @author xuzhou
 * @since 2023/10/8 15:00
 */
public class StockSpanner {

//    private final Stack<Node> stack = new Stack<>();
//    private static final Node[] stack = new Node[10000];
    private static final int LOWER = (1 << 14) - 1;
    private static final int[] stack = new int[10000];
    private int i;
    public StockSpanner() {
        i = -1;
    }

    public int next(int price) {
        // 单调栈
        // 方法3：除符号位外，前17位表示价格，后14位表示跨度
        int node = (price << 14) + 1;
        while (i >= 0 && (stack[i] >> 14) <= price) {
            node += stack[i--] & LOWER;
        }
        stack[++i] = node;
        return node & LOWER;
        // 方法1：使用java自带的栈
//        Node node = new Node(price);
//        while (!stack.isEmpty() && stack.peek().val <= price) {
//            node.span += stack.pop().span;
//        }
//        stack.push(node);
//        return node.span;
        // 方法2：用数组代替栈
//        Node node = new Node(price);
//        while (size > 0 && stack[size - 1].val <= price) {
//            node.span += stack[--size].span;
//        }
//        stack[size++] = node;
//        return node.span;
    }

//    private static class Node {
//        int val;
//        int span = 1;
//
//        public Node(int val) {
//            this.val = val;
//        }
//    }
}
