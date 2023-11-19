package dynamic_programing;

import org.junit.Test;
import utils.RandomUtils;

/**
 * 假设有N个位置，记作[1,N]
 * 开始机器人位于M位置
 * 机器人每次只能向左或向右移动一步，如果是在边界位置，只能往回走
 * 指定目标位置P，行走步数K
 * 请返回机器人能到达目标位置的可能的路径数
 *
 * @author zhou.xu
 * @since 2023/11/19 21:28
 */
public class RobotWalkTest {

    public int ways(int N, int M, int P, int K) {
        if (M < 1 || M > N) {
            return 0;
        }
        if (K == 0) {
            return M == P ? 1 : 0;
        }
        return ways(N, M + 1, P, K - 1) + ways(N, M - 1, P, K - 1);
    }

    public int waysDp(int N, int M, int P, int K) {
        int[][] dp = new int[K + 1][N + 2];
        dp[0][P] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j + 1] + dp[i - 1][j - 1];
            }
        }
        return dp[K][M];
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            int N = RandomUtils.nextInt(1, 100);
            int M = RandomUtils.nextInt(1, N + 1);
            int P = RandomUtils.nextInt(1, N + 1);
            int K = RandomUtils.nextInt(20);
            assert ways(N, M, P, K) == waysDp(N, M, P, K);
        }
    }
}
