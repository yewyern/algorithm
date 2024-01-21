package math;

public class MySqrt {

    /**
     * <a href="https://leetcode.cn/problems/sqrtx">69. x 的平方根</a>
     * <p>计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * <p>示例 1:
     * <p>输入: 4
     * <p>输出: 2
     *
     * <p>示例 2:
     * <p>输入: 8
     * <p>输出: 2
     *
     * <p>说明: 8 的平方根是 2.82842...,
     * 由于返回类型是整数，小数部分将被舍去。
     * <p>
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        if (x < 4) {
            return 1;
        }
        if (x < 9) {
            return 2;
        }
        return binarySearch(1, x / 2, x);
    }

    private int binarySearch(int left, int right, int x) {
        if (left == right || left == right - 1) {
            return left;
        }
        int center = (right + left) / 2;
        if (x / center < center) {
            return binarySearch(left, center, x);
        }
        return binarySearch(center, right, x);
    }

    /**
     * 递归+位操作
     * <p>
     * sqrt(x) = 2 * sqrt(x/4) = sqrt(x>>2)<<1
     */
    public int mySqrt2(int x) {
        if (x < 2) {
            return x;
        }
        int left = mySqrt2(x >> 2) << 1;
        int right = left + 1;
        if (x / right < right) {
            return left;
        }
        return right;
    }

    /**
     * 计算平方根，最好和使用最多的方法是牛顿法，这里使用不带种子修剪版本的牛顿法简化此问题。查看更多关于种子修剪的知识。
     * <p>
     * 不讨论其数学证明，直接使用牛顿法结论。 x1 = (x0 + x / x0) / 2
     * <p>
     * 如果 x0 = x，则收敛到 sqrt{x}
     * <p>
     * 最后当误差小于 1 时结束迭代。
     */
    public int mySqrt3(int x) {
        if (x < 2) {
            return x;
        }
        double x0 = x;
        double x1 = (x0 + x / x0) / 2;
        while (Math.abs(x1 - x0) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2;
        }
        return (int) x1;
    }

    public int mySqrt4(int x) {
        long r = 1, s = 1;
        while (s < x) {
            r <<= 1;
            s = r * r;
        }
        if (s == x) {
            return (int) r;
        }
        long l = r >> 1;
        while (l <= r) {
            long m = (l + r) >> 1;
            s = m * m;
            if (s == x) {
                return (int) m;
            } else if (s > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) (l - 1);
    }
}