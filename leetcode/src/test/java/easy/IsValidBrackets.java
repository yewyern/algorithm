package easy;

import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/valid-parentheses">20. 有效的括号</a>
 */
public class IsValidBrackets {

    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    //
    //有效字符串需满足：
    //
    //左括号必须用相同类型的右括号闭合。
    //左括号必须以正确的顺序闭合。
    //注意空字符串可被认为是有效字符串。
    //
    //示例 1:
    //
    //输入: "()"
    //输出: true
    //示例 2:
    //
    //输入: "()[]{}"
    //输出: true
    //示例 3:
    //
    //输入: "(]"
    //输出: false
    //示例 4:
    //
    //输入: "([)]"
    //输出: false
    //示例 5:
    //
    //输入: "{[]}"
    //输出: true

    public static boolean isValid(String s) {
        if (s == null || s.length() % 2 == 1) {
            return false;
        }
        int stackCapcity = s.length() / 2;
        char[] stack = new char[stackCapcity];
        int topIndex = 0;
        // toCharArray遍历比charAt快
        for (char c : s.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                if (topIndex >= stackCapcity) {
                    return false;
                }
                stack[topIndex++] = c;
            } else {
                if (topIndex == 0) {
                    return false;
                }
                if (c == '}' && stack[--topIndex] != '{') {
                    return false;
                } else if (c == ']' && stack[--topIndex] != '[') {
                    return false;
                } else if (c == ')' && stack[--topIndex] != '(') {
                    return false;
                }
            }
        }
        return topIndex == 0;
    }

    public static boolean isValid1(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '}' || c == ']' || c == ')') {
                if (stack.isEmpty() || !isPair(stack.pop(), c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValid2(String s) {
        if (s == null || s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        // toCharArray遍历比charAt快
        for (char c : s.toCharArray()) {
            if (c == '}' || c == ']' || c == ')') {
                if (stack.isEmpty() || !isPair(stack.pop(), c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    private static boolean isPair(char left, char right) {
        if (left == '{') {
            return right == '}';
        } else if (left == '[') {
            return right == ']';
        } else if (left == '(') {
            return right == ')';
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isValid("(()"));
    }
}