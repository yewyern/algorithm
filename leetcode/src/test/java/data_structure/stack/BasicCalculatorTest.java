package data_structure.stack;

import org.junit.Test;


/**
 * <a href="https://leetcode.cn/problems/basic-calculator/">224. 基本计算器</a>
 * @author xuzhou
 * @since 2024/1/19 16:43
 */
public class BasicCalculatorTest {

    @Test
    public void test() {
        System.out.println(calculate("1 + (2 + 3)"));
    }

    private Object[] stack = new Object[30001];

    public int calculate(String s) {
        //todo
        int size = 0;
        int res = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int i = 0;
        while (i < n) {
            if (str[i] >= '0' && str[i] <= '9') {
                int d = str[i++] - '0';
                while (i < n && str[i] >= '0' && str[i] <= '9') {
                    d = d * 10 + str[i++] - '0';
                }
                stack[size++] = d;
                i--;
            } else if (str[i] == '+') {
                stack[size++] = "+";
            } else if (str[i] == '-') {
                stack[size++] = "-";
            } else if (str[i] == '(') {
                stack[size++] = "(";
            } else if (str[i] == ')') {
                int ans = 0;
                int pre = 0;
                while (size > 0) {
                    Object temp = stack[--size];
                    if ("(".equals(temp)) {
                        break;
                    } else if ("+".equals(temp)) {
                        ans += pre;
                        pre = 0;
                    } else if ("-".equals(temp)) {
                        ans -= pre;
                        pre = 0;
                    } else {
                        pre = (int) temp;
                    }
                }
                ans += pre;
                stack[size++] = ans;
            }
            i++;
        }
        int pre = 0;
        while (size > 0) {
            Object temp = stack[--size];
            if ("+".equals(temp)) {
                res += pre;
                pre = 0;
            } else if ("-".equals(temp)) {
                res -= pre;
                pre = 0;
            } else {
                pre = (int) temp;
            }
        }
        return res + pre;
    }
}
