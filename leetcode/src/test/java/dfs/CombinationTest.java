package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/combinations/">77. 组合</a>
 * @author xuzhou
 * @since 2024/1/5 17:56
 */
public class CombinationTest {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<>(), 1, n, k);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> state, int start, int n, int k) {
        if (k == 0) {
            ans.add(new ArrayList<>(state));
            return;
        }
        int end = n - k + 1;
        for (int i = start; i <= end; i++) {
            state.add(i);
            dfs(ans, state, i + 1, n, k - 1);
            state.remove(state.size() - 1);
        }
    }

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                List<Integer> a = new ArrayList<>();
                a.add(i);
                ans.add(a);
            }
            return ans;
        }
        for (int i = k; i <= n; i++) {
            List<List<Integer>> list = combine(i - 1, k - 1);
            for (List<Integer> a : list) {
                a.add(i);
            }
            ans.addAll(list);
        }
        return ans;
    }
}
