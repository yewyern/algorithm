package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/combination-sum-ii/">40. 组合总和 II</a>
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 *
 *
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * 提示:
 *
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 *
 * @author xuzhou
 * @since 2022/11/9 17:30
 */
public class CombinationSum2Test {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int[] resolvedCandidates = Arrays.stream(candidates).filter(i -> i <= target).sorted().toArray();
        List<Integer> state = new ArrayList<>();
        dfs(res, resolvedCandidates, target, 0, 0, state);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] candidates, int target, int preSum, int index, List<Integer> state) {
        for (int i = index; i < candidates.length; i++) {
            while (i < candidates.length && i > index && candidates[i] == candidates[i - 1]) {
                i++;
            }
            if (i >= candidates.length) {
                break;
            }
            int candidate = candidates[i];
            int sum = preSum + candidate;
            if (sum == target) {
                state.add(candidate);
                res.add(new ArrayList<>(state));
                state.remove(state.size() - 1);
                break;
            } else if (sum < target) {
                state.add(candidate);
                dfs(res, candidates, target, sum, i + 1, state);
                state.remove(state.size() - 1);
            } else {
                break;
            }
        }
    }

    @Test
    public void test() {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }
}
