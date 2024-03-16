package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/permutations/">46. 全排列</a>
 * <p>给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * <p>示例:
 * <p>
 * <p>输入: [1,2,3]
 * <p>输出:
 * <p>[
 * <p>  [1,2,3],
 * <p>  [1,3,2],
 * <p>  [2,1,3],
 * <p>  [2,3,1],
 * <p>  [3,1,2],
 * <p>  [3,2,1]
 * <p>]
 *
 * @author zhou.xu
 * @since 2020/8/17 15:00
 */
public class PermutationsTest {

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        if (nums.length == 1) {
            return Collections.singletonList(Collections.singletonList(nums[0]));
        }
        List<List<Integer>> res = new ArrayList<>();
//        Integer[] a = new Integer[nums.length];
//        Set<Integer> set = new HashSet<>(nums.length);
//        backTrack(nums, res, a, 0, set);
        List<Integer> output = new ArrayList<>(nums.length);
        for (int num : nums) {
            output.add(num);
        }
        int first = 0;
        backTrack(res, output, first, nums.length);
        return res;
    }

    private void backTrack(List<List<Integer>> res, List<Integer> output, int first, int n) {
        if (first == n) {
            res.add(new ArrayList<>(output));
        } else {
            for (int i = first; i < n; i++) {
                Collections.swap(output, first, i);
                backTrack(res, output, first + 1, n);
                Collections.swap(output, first, i);
            }
        }
    }

    private void backTrack(int[] nums, List<List<Integer>> res, Integer[] a, int depth,
        Set<Integer> set) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!set.contains(num)) {
                a[depth] = num;
                if (depth == nums.length - 1) {
                    List<Integer> ints = new ArrayList<>(Arrays.asList(a));
                    res.add(ints);
                    return;
                } else {
                    set.add(num);
                    backTrack(nums, res, a, depth + 1, set);
                    set.remove(num);
                }
            }
        }
    }

    @Test
    public void testPermute() {
        testPermute(new int[]{1, 2, 3});
    }

    public void testPermute(int[] nums) {
        System.out.println(Arrays.toString(nums));
        List<List<Integer>> permute = permute(nums);
        System.out.println(permute);
    }
}
