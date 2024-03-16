package greedy_algorithm.rob;

/**
 * <a href="https://leetcode.cn/problems/house-robber/">198. 打家劫舍</a>
 *
 * @author zhou.xu
 * @since 2023/11/15 22:15
 */
public class RobTest {

    public int rob(int[] nums) {
        int a0 = 0, a1 = 0;
        for (int num : nums) {
            int temp = a1;
            a1 = Math.max(a0 + num, a1);
            a0 = temp;
        }
        return a1;
    }
}
