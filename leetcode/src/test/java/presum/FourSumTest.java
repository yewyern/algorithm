package presum;

import com.google.common.base.Stopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * <a href="https://leetcode.cn/problems/4sum/">18. 四数之和</a>
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 * @author zhou.xu
 * @since 2022/10/27 22:48
 */
public class FourSumTest {

    public List<List<Integer>> fourSumComparison(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int N = nums.length;
        if (N < 4) {
            return res;
        }
        if (N == 4) {
            if (nums[0] + nums[1] + nums[2] + nums[3] == target) {
                res.add(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
            }
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < N - 3; ) {
            for (int j = i + 1; j < N - 2; ) {
                for (int k = j + 1; k < N - 1; ) {
                    for (int l = k + 1; l < N; l++) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target) {
                            distinctAdd(res, Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                            break;
                        } else if (sum > target) {
                            break;
                        }
                    }
                    k++;
                    while (k < N - 1 && nums[k] == nums[k - 1]) {
                        k++;
                    }
                }
                j++;
                while (j < N - 2 && nums[j] == nums[j - 1]) {
                    j++;
                }
            }
            i++;
            while (i < N - 3 && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return res;
    }

    public void distinctAdd(List<List<Integer>> lists, List<Integer> list) {
        for (List<Integer> temp : lists) {
            if (equals(temp, list)) {
                return;
            }
        }
        lists.add(list);
    }

    public boolean equals(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!Objects.equals(a.get(i), b.get(i))) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int N = nums.length;
        if (N < 4) {
            return res;
        }
        if (N == 4) {
            if (nums[0] + nums[1] + nums[2] + nums[3] == target) {
                res.add(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
            }
            return res;
        }
        Map<Integer, List<List<Integer>>> last = new HashMap<>();
        for (int num : nums) {
            Map<Integer, List<List<Integer>>> curr = new HashMap<>(last);
            for (Entry<Integer, List<List<Integer>>> entry : last.entrySet()) {
                Integer preSum = entry.getKey();
                List<List<Integer>> value = entry.getValue();
                Map<Boolean, List<List<Integer>>> listMap = value.stream().collect(Collectors.groupingBy(l -> l.size() == 3));
                List<List<Integer>> threeNumLists = listMap.get(true);
                List<List<Integer>> otherLists = listMap.get(false);
                preSum = preSum + num;
                if (preSum == target) {
                    addNumToLists(threeNumLists, num);
                    if (threeNumLists != null) {
                        res.addAll(threeNumLists);
                    }
                }
                addNumToLists(otherLists, num);
                putMap(curr, preSum, otherLists);
            }
//            putMap(curr, num, newList(newList(num)));
            System.out.println("curr = " + curr);
            last = curr;
        }
        return res;
    }

    private void putMap(Map<Integer, List<List<Integer>>> map, int key, List<List<Integer>> value) {
        if (value == null || value.size() == 0) {
            return;
        }
        if (map.containsKey(key)) {
            map.get(key).addAll(value);
        } else {
            map.put(key, value);
        }
    }

    private List<List<Integer>> addNumToLists(List<List<Integer>> lists, int num) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> temp = new ArrayList<>(list);
            temp.add(num);
            result.add(temp);
        }
        return result;
    }

    private void fourSumTest(int target, int... nums) {
        List<List<Integer>> lists = fourSumComparison(nums, target);
        System.out.println("lists = " + lists);
    }

    @Test
    public void test() {
        fourSumTest(0, 1, 0, -1, 0, -2, 2);
        fourSumTest(8, 2, 2, 2, 2, 2);
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1, 110, -109, 110);
            int target = RandomUtils.nextInt(-109, 110);
            List<List<Integer>> lists = fourSumComparison(nums, target);

        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("elapsed = " + elapsed);
    }
}
