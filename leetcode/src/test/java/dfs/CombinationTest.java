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
