package dfs;

import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/hanota-lcci">面试题 08.06. 汉诺塔问题</a>
 *
 * @author zhou.xu
 * @since 2023/11/19 20:27
 */
public class HanoiTest {

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        moveCurrToTarget(A, C, B, A.size());
    }

    private void moveCurrToTarget(List<Integer> curr, List<Integer> target, List<Integer> other, int n) {
        if (n == 1) {
            target.add(curr.remove(curr.size() - 1));
            return;
        }
        moveCurrToTarget(curr, other, target, n - 1);
        moveCurrToTarget(curr, target, other, 1);
        moveCurrToTarget(other, target, curr, n - 1);
    }
}
