package sort;


import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 61. 扑克牌中的顺子</a>
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: True
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [0,0,1,2,5]
 * 输出: True
 * <p>
 * <p>
 * 限制：
 * <p>
 * 数组长度为 5
 * <p>
 * 数组的数取值为 [0, 13] .
 *
 * @author xuzhou
 * @since 2023/7/17 13:47
 */
public class IsStraightTest {

    public boolean isStraight(int[] nums) {
        int min = 14, max = 1;
        int[] map = new int[14];
        for (int i = 0; i < 5; i++) {
            if (nums[i] > 0) {
                if (map[nums[i]] > 0) {
                    return false;
                }
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
                map[nums[i]]++;
            }
        }
        return max - min <= 4;
    }

    @Test
    public void test() {
        System.out.println(isStraight(new int[]{4, 7, 5, 9, 2}));
    }
}
