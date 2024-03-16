package greedy_algorithm.jump;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import org.junit.Test;
import utils.RandomArray;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/jump-game-iv/">1345. 跳跃游戏 IV</a>
 * <p>
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 * <p>
 * 每一步，你可以从下标 i 跳到下标 i + 1 、i - 1 或者 j ：
 * <p>
 * i + 1 需满足：i + 1 < arr.length
 * i - 1 需满足：i - 1 >= 0
 * j 需满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 * <p>
 * 注意：任何时候你都不能跳到数组外面。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 * 示例 2：
 * <p>
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 * 示例 3：
 * <p>
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 *
 * @author zhou.xu
 * @since 2022/11/11 17:48
 */
public class JumpGame4Test {

    public int minJumps(int[] arr) {
        int N = arr.length;
        if (N < 3) {
            return N - 1;
        }
        if (arr[0] == arr[N - 1]) {
            return 1;
        }
        if (N == 3 || arr[0] == arr[N - 2] || arr[1] == arr[N - 1]) {
            return 2;
        }
        if (arr[1] == arr[N - 2]) {
            return 3;
        }
        // 根据数值对索引分组
        Map<Integer/*数值*/, List<Integer/*索引*/>> map = new HashMap<>();
        int maxSize = 1;
        for (int i = 0; i < N; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new LinkedList<>());
            list.add(i);
            map.put(arr[i], list);
            maxSize = Math.max(maxSize, list.size());
        }
        if (maxSize == 1) {
            return N - 1;
        }
        int step = 1;
        boolean[] visited = new boolean[N];
        visited[0] = true;
        Queue<Integer> curr = new LinkedList<>();
        curr.add(0);
        while (!curr.isEmpty()) {
            Queue<Integer> next = new LinkedList<>();
            for (Integer i : curr) {
                if (i == N - 2) {
                    return step;
                }
                visited[i] = true;
            }
            for (Integer i : curr) {
                // 相邻位置跳转
                if (i > 0 && !visited[i - 1]) {
                    next.add(i - 1);
                }
                if (i < N - 2 && !visited[i + 1]) {
                    next.add(i + 1);
                }
                // 相同值位置跳转
                List<Integer> list = map.remove(arr[i]);
                if (list == null) {
                    continue;
                }
                for (Integer j : list) {
                    if (j == N - 1) {
                        return step;
                    }
                    if (!visited[j]) {
                        next.add(j);
                    }
                }
            }
            curr = next;
            step++;
        }
        return step;
    }

    public int minJumps2(int[] arr) {
        int N = arr.length;
        if (N < 3) {
            return N - 1;
        }
        if (arr[0] == arr[N - 1]) {
            return 1;
        }
        // 根据数值对索引分组
        Map<Integer/*数值*/, Set<Integer/*索引*/>> map = new HashMap<>();
        int maxSize = 1;
        for (int i = 0; i < N; i++) {
            Set<Integer> set = map.getOrDefault(arr[i], new HashSet<>());
            set.add(i);
            map.put(arr[i], set);
            maxSize = Math.max(maxSize, set.size());
        }
        if (maxSize == 1) {
            return N - 1;
        }
        int step = 1;
        boolean[] visited = new boolean[N];
        visited[0] = true;
        Set<Integer> curr = new HashSet<>();
        curr.add(0);
        while (!curr.isEmpty()) {
            Set<Integer> next = new HashSet<>();
            for (Integer i : curr) {
                visited[i] = true;
            }
            for (Integer i : curr) {
                if (map.containsKey(arr[i])) {
                    // 相同值位置跳转
                    Set<Integer> set = map.get(arr[i]);
                    if (set.contains(N - 1)) {
                        return step;
                    }
                    for (Integer j : set) {
                        if (!visited[j]) {
                            next.add(j);
                        }
                    }
                    map.remove(arr[i]);
                }
                // 相邻位置跳转
                if (i > 0 && !visited[i - 1]) {
                    next.add(i - 1);
                }
                if (i < N - 2 && !visited[i + 1]) {
                    next.add(i + 1);
                }
            }
            curr = next;
            step++;
            if (curr.contains(N - 2)) {
                return step;
            }
        }
        return step;
    }

    @Test
    public void test() {
        //示例 1：
        //输入：arr = [100,-23,-23,404,100,23,23,23,3,404] 输出：3 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。 示例 2：
        //输入：arr = [7] 输出：0 解释：一开始就在最后一个元素处，所以你不需要跳跃。 示例 3：
        //输入：arr = [7,6,9,6,9,6,9,7] 输出：1 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
        //提示：
        //1 <= arr.length <= 5 * 10^4 -10^8 <= arr[i] <= 10^8
        System.out.println(minJumps(new int[]{7, 7, 2, 1, 7, 7, 7, 3, 4, 1})); // 3
        System.out.println(minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404})); // 3
        System.out.println(minJumps(new int[]{7})); // 0
        System.out.println(minJumps(new int[]{7, 6, 9, 6, 9, 6, 9, 7})); // 1
        System.out.println(minJumps(new int[]{0, 4, 3, 9})); // 3
        System.out.println(minJumps(new int[]{-12, -86, 27, -61, -4})); // 4
        int[] nums = new int[50000];
        Arrays.fill(nums, 7);
        System.out.println(minJumps(nums)); // 1
    }

    @Test
    public void readFileTest() {
        List<String> lines = FileUtil.readLines("./jumpgame/test", StandardCharsets.UTF_8);
        Splitter splitter = Splitter.on(",").trimResults();
        List<Integer> list = lines.stream()
                .map(splitter::splitToList)
                .flatMap(Collection::stream)
                .filter(StrUtil::isNotBlank)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        System.out.println(minJumps(arr));//9
    }

    @Test
    public void press() {
        //示例 1：
        //输入：arr = [100,-23,-23,404,100,23,23,23,3,404] 输出：3 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。 示例 2：
        //输入：arr = [7] 输出：0 解释：一开始就在最后一个元素处，所以你不需要跳跃。 示例 3：
        //输入：arr = [7,6,9,6,9,6,9,7] 输出：1 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
        //提示：
        //1 <= arr.length <= 5 * 10^4 -10^8 <= arr[i] <= 10^8
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateRandomLengthArray(1, 50000, -100000000, 100000000);
            minJumps(nums);
        }
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
