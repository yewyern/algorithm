package dynamic_programing;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/triangle">120. 三角形最小路径和</a>
 * @author xuzhou
 * @since 2024/1/15 14:52
 */
public class MinPathInTriangleTest {

    @Test
    public void test() {
        System.out.println(minimumTotal(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4), Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8 , 3))));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int n = triangle.size();
        int[] total = new int[n];
        total[0] = triangle.get(0).get(0);
        for (int row = 1; row < n; row++) {
            List<Integer> list = triangle.get(row);
            total[row] = list.get(row) + total[row - 1];
            for (int i = row - 1; i > 0; i--) {
                total[i] = list.get(i) + Math.min(total[i], total[i - 1]);
            }
            total[0] = list.get(0) + total[0];
        }
        int min = total[0];
        for (int i : total) {
            min = Math.min(min, i);
        }
        return min;
    }
}
