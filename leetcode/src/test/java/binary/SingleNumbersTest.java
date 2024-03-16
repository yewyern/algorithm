package binary;

/**
 * <a href="https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 56 - I. 数组中数字出现的次数</a>
 * <p>
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 2 <= nums.length <= 10000
 *
 * @author xuzhou
 * @since 2023/8/2 13:57
 */
public class SingleNumbersTest {

    public int[] singleNumbers(int[] nums) {
        int[] ans = new int[2];
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        // xor 的结果是ans[0] ^ ans[1]，即其中的1一定是2个数不一样的位置
        int flag = xor & (-xor); // 取最后一位的1
        for (int num : nums) {
            if ((num & flag) != 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        return ans;
    }
}
