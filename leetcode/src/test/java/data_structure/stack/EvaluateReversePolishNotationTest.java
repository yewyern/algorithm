package data_structure.stack;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/evaluate-reverse-polish-notation">150. 逆波兰表达式求值</a>
 * @author xuzhou
 * @since 2023/12/25 10:13
 */
public class EvaluateReversePolishNotationTest {

    public int evalRPN(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[n];
        int size = 0;
        for (String token : tokens) {
            int curr = 0;
            if ("+-*/".contains(token)) {
                int second = stack[--size];
                int first = stack[--size];
                switch (token) {
                    case "+":
                        curr = first + second;
                        break;
                    case "-":
                        curr = first - second;
                        break;
                    case "*":
                        curr = first * second;
                        break;
                    case "/":
                        curr = first / second;
                        break;
                }
            } else {
                curr = Integer.parseInt(token);
            }
            stack[size++] = curr;
        }
        return stack[0];
    }

    @Test
    public void test() {
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));
    }
}
