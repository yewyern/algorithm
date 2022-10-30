package dfs;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/combination-sum/">39. 组合总和</a>
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 * <p>
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * <p>
 * 输入: candidates = [2], target = 1
 * 输出: []
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 *
 * @author zhou.xu
 * @since 2022/10/29 23:20
 */
public class CombinationSumTest {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 排序并去除大于target的值
        int count = 0;
        boolean[] set = new boolean[40];
        for (int candidate : candidates) {
            if (candidate <= target) {
                set[candidate] = true;
                count++;
            }
        }
        if (count == 0) {
            return res;
        }
        int[] resolvedCandidates = new int[count];
        count = 0;
        for (int i = 0; i < set.length; i++) {
            if (set[i]) {
                resolvedCandidates[count++] = i;
            }
        }
        List<Integer> state = new ArrayList<>();
        dfs(res, resolvedCandidates, target, 0, 0, state);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] candidates, int target, int preSum, int index, List<Integer> state) {
        for (; index < candidates.length; index++) {
            int candidate = candidates[index];
            int sum = preSum + candidate;
            if (sum == target) {
                state.add(candidate);
                res.add(new ArrayList<>(state));
                state.remove(state.size() - 1);
                break;
            } else if (sum < target) {
                state.add(candidate);
                dfs(res, candidates, target, sum, index, state);
                state.remove(state.size() - 1);
            } else {
                break;
            }
        }
    }

    public void testCombinationSum(int target, int... candidates) {
        List<List<Integer>> combinationSum = combinationSum(candidates, target);
        System.out.println("combinationSum = " + combinationSum);
    }

    @Test
    public void test() {
        testCombinationSum(7, 2, 3, 6, 7);
        testCombinationSum(8, 2, 3, 5);
        testCombinationSum(1, 2);
    }
}
