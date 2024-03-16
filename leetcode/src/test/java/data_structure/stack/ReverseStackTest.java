package data_structure.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 将栈内的元素顺序翻转，要求不能使用额外空间，可以递归
 *
 * @author zhou.xu
 * @since 2023/11/11 11:22
 */
public class ReverseStackTest {

    @Test
    public void test() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        reverse(stack);
        System.out.println(stack);
    }

    private <E> void reverse(Stack<E> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 1、把最底下的那个值拿到
        E e = dfs(stack);
        // 2、翻转剩下的栈
        reverse(stack);
        // 3、把最底下放到最上面
        stack.push(e);
    }

    private <E> E dfs(Stack<E> stack) {
        E pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        }
        E e = dfs(stack);
        stack.push(pop);
        return e;
    }

    private <E> void reverse2(Stack<E> stack) {
        int n = stack.size();
        while (n > 1) {
            E e = dfs(stack, stack.pop(), n - 1);
            stack.push(e);
            n--;
        }
    }

    private <E> E dfs(Stack<E> stack, E val, int n) {
        if (n == 1) {
            E pop = stack.pop();
            stack.push(val);
            return pop;
        }
        E pop = stack.pop();
        E e = dfs(stack, val, n - 1);
        stack.push(e);
        return pop;
    }
}
