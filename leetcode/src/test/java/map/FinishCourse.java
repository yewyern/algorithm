package map;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * <p>你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * <p>
 * <p>在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * <p>
 * <p>给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 * <p>示例 1:
 * <p>输入: 2, [[1,0]]
 * <p>输出: true
 * <p>解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * <p>示例 2:
 * <p>输入: 2, [[1,0],[0,1]]
 * <p>输出: false
 * <p>解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成 课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * <p>
 * <p>提示：
 * <p>
 * <p>输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * <p>你可以假定输入的先决条件中没有重复的边。
 * <p>1 <= numCourses <= 10^5
 * <p>
 *
 * @author zhou.xu
 * @date 2020/8/4 13:44
 */
public class FinishCourse {

    private Map<Integer, Set<Integer>> map;

    int[] visited;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        map = Arrays.stream(prerequisites).parallel()
            .collect(Collectors.groupingBy(nums -> nums[0], Collectors.mapping(nums -> nums[1], Collectors.toSet())));
        for (Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if (visited[entry.getKey()] == 0) {
                if (dfs(entry.getKey())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int curr) {
        visited[curr] = 1;
        for (Integer i : map.get(curr)) {
            if (visited[i] == 1) {
                return true;
            } else if (visited[i] == 0) {
                if (!map.containsKey(i)) {
                    visited[i] = 2;
                } else if (dfs(i)) {
                    return true;
                }
            }
        }
        visited[curr] = 2;
        return false;
    }

    private void test(int numCourses, int[][] prerequisites) {
        System.out.println("numCourses = " + numCourses);
        System.out.println("prerequisites = " + Arrays.deepToString(prerequisites));
        boolean canFinish = canFinish(numCourses, prerequisites);
        System.out.println("canFinish = " + canFinish);
        System.out.println("-----------------------");
    }

    @Test
    public void test() {
        test(2, new int[][]{{1, 0}});
        test(2, new int[][]{{1, 0}, {0, 1}});
        test(3, new int[][]{{1, 2}, {2, 0}, {0, 1}});
        test(4, new int[][]{{0, 1}, {3, 1}, {1, 3}, {3, 2}});
    }
}
