package data_structure.stack;

import org.junit.Test;

import java.util.LinkedList;


/**
 * <a href="https://leetcode.cn/problems/basic-calculator/">224. 基本计算器</a>
 * @author xuzhou
 * @since 2024/1/19 16:43
 */
public class BasicCalculatorTest {

    @Test
    public void test() {
        System.out.println(calculate(" 2-1 + 2 "));
    }

    char[] str;
    int n;
    int i;
    public int calculate(String s) {
        str = s.toCharArray();
        n = str.length;
        i = 0;
        return calc();
    }

    private int calc() {
        int res = 0;
        boolean add = true;
        while (i < n) {
            if (str[i] >= '0' && str[i] <= '9') {
                int d = str[i++] - '0';
                while (i < n && str[i] >= '0' && str[i] <= '9') {
                    d = d * 10 + str[i++] - '0';
                }
                res += add ? d : -d;
            } else if (str[i] == '+') {
                add = true;
                i++;
            } else if (str[i] == '-') {
                add = false;
                i++;
            } else if (str[i] == '(') {
                i++;
                int calc = calc();
                res += add ? calc : -calc;
            } else if (str[i] == ')') {
                i++;
                break;
            } else {
                i++;
            }
        }
        return res;
    }

    private static final long ADD = 1L << 33;
    private static final long SUB = 1L << 34;
    private static final long LEFT = 1L << 35;
    private Long[] stack = new Long[100];
    private int size = 0;
    public int calculate3(String s) {
        size = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int i = 0;
        while (i < n) {
            if (str[i] >= '0' && str[i] <= '9') {
                long d = str[i++] - '0';
                while (i < n && str[i] >= '0' && str[i] <= '9') {
                    d = d * 10 + str[i++] - '0';
                }
                stack[size++] = d;
                calc3(false);
                i--;
            } else if (str[i] == '+') {
                stack[size++] = ADD;
            } else if (str[i] == '-') {
                stack[size++] = SUB;
            } else if (str[i] == '(') {
                stack[size++] = LEFT;
            } else if (str[i] == ')') {
                calc3( true);
            }
            i++;
        }
        calc3(false);
        return Math.toIntExact(stack[0]);
    }

    private void calc3(boolean hasRight) {
        if (size == 0) {
            return;
        }
        long res = 0;
        while (size > 0 && stack[size - 1] != LEFT) {
            Long num = stack[--size];
            if (size > 0 && stack[size - 1] != LEFT) {
                Long operator = stack[--size];
                res += operator == ADD ? num : -num;
            } else {
                res += num;
            }
        }
        if (hasRight && size > 0 && stack[size - 1] == LEFT) {
            stack[size - 1] = res;
        } else {
            stack[size++] = res;
        }
    }

    public int calculate1(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        LinkedList<Long> stack = new LinkedList<>();
        int i = 0;
        while (i < n) {
            if (str[i] >= '0' && str[i] <= '9') {
                long d = str[i++] - '0';
                while (i < n && str[i] >= '0' && str[i] <= '9') {
                    d = d * 10 + str[i++] - '0';
                }
                stack.push(d);
                calc1(stack, false);
                i--;
            } else if (str[i] == '+') {
                stack.push(ADD);
            } else if (str[i] == '-') {
                stack.push(SUB);
            } else if (str[i] == '(') {
                stack.push(LEFT);
            } else if (str[i] == ')') {
                calc1(stack, true);
            }
            i++;
        }
        calc1(stack, false);
        return Math.toIntExact(stack.poll());
    }

    private void calc1(LinkedList<Long> stack, boolean hasRight) {
        if (stack.isEmpty()) {
            return;
        }
        long res = 0;
        while (!stack.isEmpty() && stack.peek() != LEFT) {
            Long num = stack.pop();
            if (!stack.isEmpty() && stack.peek() != LEFT) {
                Long operator = stack.pop();
                res += operator == ADD ? num : -num;
            } else {
                res += num;
            }
        }
        if (hasRight && !stack.isEmpty() && stack.peek() == LEFT) {
            stack.pop();
            stack.push(res);
        } else {
            stack.push(res);
        }
    }

    public int calculate2(String s) {
        int res = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        LinkedList<Long> stack = new LinkedList<>();
        int i = 0;
        while (i < n) {
            if (str[i] >= '0' && str[i] <= '9') {
                long d = str[i++] - '0';
                while (i < n && str[i] >= '0' && str[i] <= '9') {
                    d = d * 10 + str[i++] - '0';
                }
                stack.push(d);
                i--;
            } else if (str[i] == '+') {
                stack.push(ADD);
            } else if (str[i] == '-') {
                stack.push(SUB);
            } else if (str[i] == '(') {
                stack.push(LEFT);
            } else if (str[i] == ')') {
                long ans = 0;
                int pre = 0;
                while (!stack.isEmpty()) {
                    long temp = stack.pop();
                    if (LEFT == temp) {
                        break;
                    } else if (ADD == temp) {
                        ans += pre;
                        pre = 0;
                    } else if (SUB == temp) {
                        ans -= pre;
                        pre = 0;
                    } else {
                        pre = (int) temp;
                    }
                }
                ans += pre;
                stack.push(ans);
            }
            i++;
        }
        long pre = 0;
        while (!stack.isEmpty()) {
            long temp = stack.pop();
            if (ADD == temp) {
                res += pre;
                pre = 0;
            } else if (SUB == temp) {
                res -= pre;
                pre = 0;
            } else {
                pre = temp;
            }
        }
        return (int) (res + pre);
    }
}
