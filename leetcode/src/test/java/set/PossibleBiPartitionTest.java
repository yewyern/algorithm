package set;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import utils.TestUtils;

/**
 * <a href="https://leetcode.cn/problems/possible-bipartition/">886. 可能的二分法</a>
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * <p>
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 * 示例 2：
 * <p>
 * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 104
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * dislikes 中每一组都 不同
 *
 * @author zhou.xu
 * @since 2022/10/16 18:10
 */
public class PossibleBiPartitionTest {

    public boolean possibleBiPartition(int n, int[][] dislikes) {
        if (dislikes.length == 0) {
            return true;
        }
        Set<Integer> set = new HashSet<>(dislikes.length);
        for (int i = 0; i < dislikes.length; i++) {
            set.add(i);
        }
        Set<Integer> a = new HashSet<>(n);
        Set<Integer> b = new HashSet<>(n);
        do {
            boolean newPartition = false;
            Set<Integer> temp = new HashSet<>(dislikes.length);
            for (int i : set) {
                int[] dislike = dislikes[i];
                int p = dislike[0], q = dislike[1];
                if (a.isEmpty() && b.isEmpty()) {
                    a.add(p);
                    b.add(q);
                    newPartition = true;
                    continue;
                }
                if (!a.contains(p) && !b.contains(p) && !a.contains(q) && !b.contains(q)) {
                    temp.add(i);
                    continue;
                }
                if (a.contains(p) && a.contains(q)
                    || (b.contains(p) && b.contains(q))) {
                    return false;
                }
                if (a.contains(p)) {
                    b.add(q);
                } else if (a.contains(q)) {
                    b.add(p);
                } else if (b.contains(p)) {
                    a.add(q);
                } else if (b.contains(q)) {
                    a.add(p);
                }
                newPartition = true;
            }
            set = temp;
            if (!newPartition) {
                a.clear();
                b.clear();
            }
        } while (!set.isEmpty());
        return true;
    }

    @Test
    public void test() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
//        TestUtils.test(getClass(), "possibleBiPartition", true, 4, new int[][]{{1, 2}, {1, 3}, {2, 4}});
//        TestUtils.test(getClass(), "possibleBiPartition", false, 3, new int[][]{{1, 2}, {1, 3}, {2, 3}});
//        TestUtils.test(getClass(), "possibleBiPartition", false, 5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}});
        TestUtils.test(getClass(), "possibleBiPartition", false, 5, new int[][]{{1, 2}, {3, 4}, {4, 5}, {3, 5}});
        TestUtils.test(getClass(), "possibleBiPartition", false, 4, new int[][]{{1, 2}, {3, 4}, {1, 3}, {1, 4}});
    }
}
