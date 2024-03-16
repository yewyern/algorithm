package data_structure.tree;

/**
 * <a href="https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree">2673. 使二叉树所有路径值相等的最小代价</a>
 * @author xuzhou
 * @since 2024/3/11 14:32
 */
public class MinIncrementsTest {

    public int minIncrements(int n, int[] cost) {
        // 由叶节点往上进行平衡，当子节点是平衡的，只要再平衡父节点
        int ans = 0;
        while (n > 1) {
            int start = n >> 1; // 当前第一个叶子位置，最左子节点
            while (start < n) {
                int max = Math.max(cost[start], cost[start + 1]);
                int min = Math.min(cost[start], cost[start + 1]);
                ans += max - min;
                cost[start >> 1] += max; // 修改父节点的值
                start += 2;
            }
            n >>= 1;
        }
        return ans;
    }
}
