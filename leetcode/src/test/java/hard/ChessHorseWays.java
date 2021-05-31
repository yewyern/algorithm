package hard;

import org.junit.Test;

/**
 * <p>象棋棋盘，10条横线，从上到下依次为0-9，9条纵线，从左到右依次为0-8</p>
 * <p>求棋盘上任意点（x,y）到(0,0)点，必须走k步的方法有多少种
 */
public class ChessHorseWays {

    public int ways(int x, int y, int n) {
        if (n == 0) {
            // 没步数了，如果到了(0,0)，就是1种可能性，反之没有可能性
            return x == 0 && y == 0 ? 1 : 0;
        }
        // 动态规划，
        int[][][] dp = new int[9][10][n + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // n的层级从1到n
            for (int j = 0; j <= 8; j++) {
                // x坐标j
                for (int k = 0; k <= 9; k++) {
                    // y坐标
                    int t = i - 1;
                    dp[j][k][i] = getValue(dp, j + 2, k + 1, t)
                        + getValue(dp, j + 2, k - 1, t)
                        + getValue(dp, j + 1, k + 2, t)
                        + getValue(dp, j + 1, k - 2, t)
                        + getValue(dp, j - 1, k + 2, t)
                        + getValue(dp, j - 1, k - 2, t)
                        + getValue(dp, j - 2, k + 1, t)
                        + getValue(dp, j - 2, k - 1, t);
                }

            }
        }
        return dp[x][y][n];
    }

    public int getValue(int[][][] dp, int x, int y, int n) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        return dp[x][y][n];
    }

    public int waysRecur(int x, int y, int n) {
        if (n == 0) {
            // 没步数了，如果到了(0,0)，就是1种可能性，反之没有可能性
            return x == 0 && y == 0 ? 1 : 0;
        }
        // 出界了，可能性为0
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        n--;
        return waysRecur(x + 2, y + 1, n)
            + waysRecur(x + 2, y - 1, n)
            + waysRecur(x + 1, y + 2, n)
            + waysRecur(x + 1, y - 2, n)
            + waysRecur(x - 1, y + 2, n)
            + waysRecur(x - 1, y - 2, n)
            + waysRecur(x - 2, y + 1, n)
            + waysRecur(x - 2, y - 1, n);
    }

    public void test(int x, int y, int n) {
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("n = " + n);
        System.out.println("ways(x, y, n) = " + ways(x, y, n));
        System.out.println("waysRecur(x, y, n) = " + waysRecur(x, y, n));
        System.out.println("--------------------------");
    }

    @Test
    public void test() {
        test(0, 0, 0);
        test(0, 0, 0);
        test(0, 0, 1);
        test(2, 1, 0);
        test(2, 1, 1);
        test(3, 1, 2);
        test(7, 7, 10);
//        test(3, 1, 1);
    }
}
