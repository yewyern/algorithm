package dfs;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/generate-parentheses/">22. 括号生成</a>
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例：
 * <p>
 * 输入：n = 3 输出：[ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 * <p>
 *
 * @author zhou.xu
 * @since 2020/10/22 15:03
 */
public class GenerateParenthesis {


    /**
     * <p> 括号的总长度，左右括号的总数都是固定的，
     * <p> 对于任意位置，只有两种可能，要么左括号，要么右括号
     * <p> 对于任意位置，右括号的数量永远小于等于左括号的数量
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char[] cs = new char[n * 2];
        backTrack(res, cs, 0, 0, n, 0);
        return res;
    }

    private void backTrack(List<String> res, char[] cs, int left, int right, int max, int pos) {
        if (left < max) {
            cs[pos] = '(';
            if (pos == cs.length - 1) {
                res.add(String.valueOf(cs));
            } else {
                backTrack(res, cs, left + 1, right, max, pos + 1);
            }
        }
        if (right < left) {
            cs[pos] = ')';
            if (pos == cs.length - 1) {
                res.add(String.valueOf(cs));
            } else {
                backTrack(res, cs, left, right + 1, max, pos + 1);
            }
        }
    }

    public void test(int n) {
        List<String> list = generateParenthesis(n);
        System.out.println(n + " = " + list);
    }

    @Test
    public void test() {
        test(1);
        test(2);
        test(3);
        test(4);
        test(5);
    }
}
