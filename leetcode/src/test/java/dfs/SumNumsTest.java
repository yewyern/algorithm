package dfs;

/**
 * <a href="https://leetcode.cn/problems/qiu-12n-lcof/description/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 64. 求1+2+…+n</a>
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 * <p>
 * 输入: n = 9
 * 输出: 45
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 10000
 *
 * @author xuzhou
 * @since 2023/7/6 16:48
 */
public class SumNumsTest {

    public int sumNums(int n) {
        // n * (n + 1) >> 1
        // 利用位运算实现乘法，int位数最多32位，可以将循环写死为32次
        // 本题中最大10000，只需要14次
        return multiply(n, n + 1) >> 1;
    }


    public static int multiply(int a, int b) {
        int ans = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                ans = ans + a;
            }
            a <<= 1;
            // >> 如果b是负数，左移时，左边符号位不变
            // >>> 如果b是负数，左移时，左边符号位右移
            b >>>= 1;
        }
        return ans;
    }

    public int sumNums2(int n) {
        // && 只有当左边为true，才执行右边的语句
        boolean flag = (n > 0) && (n += sumNums2(n - 1)) > 0;
        return n;
    }

    public int sumNumsComparison(int n) {
        // 利用数组下标异常跳出递归
        int[] sum = new int[n + 1];
        process(sum, 1);
        return sum[n];
    }

    private void process(int[] sum, int n) {
        try {
            sum[n] = sum[n - 1] + n;
            process(sum, n + 1);
        } catch (Exception e) {
        }
    }
}
