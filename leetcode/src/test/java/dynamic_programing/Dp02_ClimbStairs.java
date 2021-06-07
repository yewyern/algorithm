package dynamic_programing;

import org.junit.Test;

/**
 * <p>假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * <p>每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * <p>注意：给定 n 是一个正整数。
 * <p>
 * <p>示例 1：
 * <p>
 * <p>输入： 2
 * <p>输出： 2
 * <p>解释： 有两种方法可以爬到楼顶。
 * <p>1.  1 阶 + 1 阶
 * <p>2.  2 阶
 * <p>示例 2：
 * <p>
 * <p>输入： 3
 * <p>输出： 3
 * <p>解释： 有三种方法可以爬到楼顶。
 * <p>1.  1 阶 + 1 阶 + 1 阶
 * <p>2.  1 阶 + 2 阶
 * <p>3.  2 阶 + 1 阶
 * <p>
 */
public class Dp02_ClimbStairs {

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int cur = 1, pre = 1;
        for (int i = 1; i < n; i++) {
            cur = cur + pre;
            pre = cur - pre;
        }
        return cur;
    }

    /**
     * 递归解法，由于没有保存之前的计算状态，容易重复计算，造成超时
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n <= 1) {
            return 1;
        }
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }

    @Test
    public void test() {
        for (int i = 0; i < 1000; i++) {
            long l = System.nanoTime();
            int i1 = climbStairs(i);
            long l1 = System.nanoTime();
            System.out.println("climbStairs.climbStairs(" + i + ") = " + i1 + ", spent: " + (l1 - l));
            int i2 = climbStairs2(i);
            long l2 = System.nanoTime();
            System.out.println("climbStairs.climbStairs2(" + i + ") = " + i2 + ", spent: " + (l2 - l1));
            System.out.println("----------------------------");
        }
    }
}