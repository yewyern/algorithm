package data_structure.graph;


/**
 * <a href="https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting">2127. 参加会议的最多员工数</a>
 * @author xuzhou
 * @since 2023/11/10 15:19
 */
public class MaximumInvitations3 {

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] in = new int[n];
        // 拓扑排序
        // 1、先计算每个点的入度
        // 2、从入度为0的点开始，将下一个点入度-1
        // 3、如果下一个点入度为0，继续深度优先遍历，直到入度>0（环上的点入度一定>0）
        for (int i : favorite) {
            in[i]++;
        }
        int[] count = new int[n];
        boolean[] visited = new boolean[n]; // 已经计算过的点，主要是深度遍历后又会多出一些入度为0的点，需要排除掉
        for (int i = 0; i < n; i++) {
            int j = i;
            while (!visited[j] && in[j] == 0) {
                visited[j] = true;
                count[favorite[j]] = Math.max(count[j] + 1, count[favorite[j]]);
                j = favorite[j];
                in[j]--;
            }
        }
        int max = 0; // 最终结果
        int two = 0; // 环是2个点组成的总计数
        for (int i = 0; i < n; i++) {
            if (in[i] > 0) {
                // 环上的点
                if (favorite[favorite[i]] == i) {
                    // 2个数的点
                    two += 2 + count[i] + count[favorite[i]];
                    in[i]--;
                    in[favorite[i]]--;
                } else {
                    int c = 0, j = i;
                    while (in[j] > 0) {
                        c++;
                        in[j]--;
                        j = favorite[j];
                    }
                    max = Math.max(max, c);
                }
            }
        }
        return Math.max(max, two);
    }
}
