package data_structure.stack;

import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/">剑指 Offer 31. 栈的压入、弹出序列</a>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 * <p>
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 * 注意：本题与主站 946 题相同：<a href="https://leetcode-cn.com/problems/validate-stack-sequences/">946. 验证栈序列</a>
 *
 * @author xuzhou
 * @since 2023/6/16 15:55
 */
public class ValidateStackSequencesTest {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || pushed.length == 0) {
            return true;
        }
        int N = pushed.length;
        int[] stack = new int[N];
        int size = 0;
        int popIndex = 0;
        for (int a : pushed) {
            if (a == popped[popIndex]) {
                popIndex++;
            } else {
                stack[size++] = a;
                continue;
            }
            while (size > 0 && stack[size - 1] == popped[popIndex]) {
                size--;
                popIndex++;
            }
        }
        return size == 0;
    }

    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        if (pushed == null || pushed.length == 0) {
            return true;
        }
        int N = pushed.length;
        int[] stack = new int[N];
        int size = 0;
        int pushIndex = 0, popIndex = 0;
        while (pushIndex < N && popIndex < N) {
            if (pushed[pushIndex] == popped[popIndex]) {
                pushIndex++;
                popIndex++;
            } else if (size > 0 && stack[size - 1] == popped[popIndex]){
                size--;
                popIndex++;
            } else {
                stack[size++] = pushed[pushIndex++];
            }
        }
        while (size > 0 && stack[size - 1] == popped[popIndex]) {
            size--;
            popIndex++;
        }
        return size == 0;
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        if (pushed == null || pushed.length == 0) {
            return true;
        }
        int N = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0, popIndex = 0;
        while (pushIndex < N && popIndex < N) {
            if (pushed[pushIndex] == popped[popIndex]) {
                pushIndex++;
                popIndex++;
            } else if (!stack.isEmpty() && stack.peek() == popped[popIndex]){
                stack.pop();
                popIndex++;
            } else {
                stack.push(pushed[pushIndex++]);
            }
        }
        while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
            stack.pop();
            popIndex++;
        }
        return stack.isEmpty();
    }
}
