package array;

import org.junit.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/equal-row-and-column-pairs/">2352. 相等行列对</a>
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 * <p>
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 * <p>
 * 示例 1：
 * <img src="https://assets.leetcode.com/uploads/2022/06/01/ex1.jpg" />
 * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
 * 输出：1
 * 解释：存在一对相等行列对：
 * - (第 2 行，第 1 列)：[2,7,7]
 * 示例 2：
 * <img src="https://assets.leetcode.com/uploads/2022/06/01/ex2.jpg" />
 * 输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * 输出：3
 * 解释：存在三对相等行列对：
 * - (第 0 行，第 0 列)：[3,1,2,2]
 * - (第 2 行, 第 2 列)：[2,4,2,2]
 * - (第 3 行, 第 2 列)：[2,4,2,2]
 *
 * @author zhou.xu
 * @since 2023/6/6 21:53
 */
public class EqualRowAndColumnPairsTest {

    @Test
    public void test() {
        check(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}}, 1);
        check(new int[][]{{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}}, 1);
    }

    private void check(int[][] grid, int expected) {
        int actual = equalPairs(grid);
        if (expected != actual) {
            System.out.println("grid = " + Arrays.deepToString(grid) + ", expected = " + expected + ", actual = " + actual);
        }
    }

    public int equalPairs(int[][] grid) {
        int N = grid.length;
        if (N == 1) {
            return 1;
        }
        int count = 0;
        int level = 0;
        Map<Integer, Set<Integer>> xMap = new HashMap<>();
        Map<Integer, Set<Integer>> yMap = new HashMap<>();
        boolean flag = true;
        for (int i = level; i < N; i++) {
            if (grid[level][i] != grid[i][level]) {
                flag = false;
            }
            // 保存第level行中数字与索引的映射
            Set<Integer> xSet = xMap.getOrDefault(grid[level][i], new HashSet<>());
            xSet.add(i);
            xMap.put(grid[level][i], xSet);

            // 保存第level列中数字与索引的映射
            Set<Integer> ySet = yMap.getOrDefault(grid[i][level], new HashSet<>());
            ySet.add(i);
            yMap.put(grid[i][level], ySet);
        }
        count += flag ? 1 : 0;
        return 0;
    }
}
