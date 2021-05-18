package easy;

/**
 * <p>给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * <p>示例 1:
 * <p>
 * <p>输入: 3
 * <p>输出: 0
 * <p>解释: 3! = 6, 尾数中没有零。
 * <p>示例 2:
 * <p>
 * <p>输入: 5
 * <p>输出: 1
 * <p>解释: 5! = 120, 尾数中有 1 个零.
 * <p>说明: 你算法的时间复杂度应为 O(log n) 。
 *
 * @author : zhou.xu
 * @date : 2020/6/4 16:20
 */
public class TrailingZeroes {

    private static final TrailingZeroes INSTANCE = new TrailingZeroes();

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            n = n / 5;
            count += n;
        }
        return count;
    }

    private static int test(int n) {
        return INSTANCE.trailingZeroes(n);
    }

    public static void main(String[] args) {
        System.out.println("3 = " + test(3));
        System.out.println("5 = " + test(5));
        System.out.println("10 = " + test(10));
        System.out.println("25 = " + test(25));
        System.out.println("125 = " + test(125));
    }
}