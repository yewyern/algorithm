package data_structure.graph;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting">2127. 参加会议的最多员工数</a>
 *
 * @author zhou.xu
 * @since 2023/11/4 20:47
 */
public class MaximumInvitationsTest {
    @Test
    public void test() {
        System.out.println(maximumInvitations(new int[]{2, 2, 1, 2}));
    }

    public int maximumInvitations(int[] favorite) {
        // 1、一定是要有环的，无环图，无法邀请任何一个人
        // 2、一个点可以有多个入度，但只有一个出度
        // 3、如果2个点成环，可以额外邀请一个跟环联通的
        // 4、如果是3个点及以上成环，不能再加
        int n = favorite.length;
        int max = 0;
        boolean[] visited = new boolean[n]; // 已经试过一次的点
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            boolean[] temp = new boolean[n]; // 当前出发点邀请的员工
            int count = 0;
            int curr = i;
            while (!visited[curr] && !temp[curr]) {
                temp[curr] = true;
                visited[curr] = true;
                curr = favorite[curr];
                count++;
            }
            if (visited[curr] && !temp[curr]) {
                // 这个点走过了，不需要再走一次
                continue;
            }
            if (count <= 3) {
                max = Math.max(max, count);
                continue;
            }
            int circleCount = 1;
            int start = curr;
            while (favorite[curr] != start) {
                curr = favorite[curr];
                circleCount++;
            }
            max = Math.max(max, circleCount == 2 ? 3 : circleCount);
        }
        return max;
    }
}
