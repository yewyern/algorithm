package dynamic_programing;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/reducing-dishes">1402. 做菜顺序</a>
 *
 * @author xuzhou
 * @since 2023/10/23 10:37
 */
public class MaxSatisfactionTest {

    public int maxSatisfaction(int[] satisfaction) {
        // 先排序
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int pre = satisfaction[n - 1];
        if (pre < 0) {
            return 0; // 最大的都是负数
        }
        // 对已排序的数组如：[-9,-8,-1,0,5]
        // 可以生成如下矩阵：
        // [-9,-8,-1,0,5]
        // [-9,-8,-1,0,5]
        // [-9,-8,-1,0,5]
        // [-9,-8,-1,0,5]
        // [-9,-8,-1,0,5]
        // like-time 系数 总和 可以看作是由右上角开始的三角形内所有数的和
        // 如：5
        // 如：5 + (0 + 5)
        // 如：5 + (0 + 5) + (-1 + 0 + 5)
        // 如：5 + (0 + 5) + (-1 + 0 + 5) + (-8 + -1 + 0 + 5)
        int max = pre;
        for (int i = n - 2; i >= 0; i--) {
            pre += satisfaction[i];
            if (pre < 0) {
                break;
            }
            max += pre;
        }
        return max;
    }
}
