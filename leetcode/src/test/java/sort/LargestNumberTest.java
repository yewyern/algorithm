package sort;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/largest-number/description/">179. 最大数</a>
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * <p>
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 *
 * @author xuzhou
 * @since 2023/7/26 15:09
 */
public class LargestNumberTest {

    public String largestNumber(int[] nums) {
        int n = nums.length;
        boolean allZero = true;
        for (int num : nums) {
            if (num != 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) {
            return "0";
        }
        String[] helper = new String[n];
        for (int i = 0; i < n; i++) {
            helper[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(helper, this::compare);
        StringBuilder sb = new StringBuilder();
        for (String s : helper) {
            sb.append(s);
        }
        return sb.toString();
    }

    private int compare(String a, String b) {
        char[] cs1 = a.toCharArray();
        char[] cs2 = b.toCharArray();
        int m = cs1.length, n = cs2.length, l = m + n;
        for (int i = 0; i < l; i++) {
            char c1 = i < m ? cs1[i] : cs2[i - m];
            char c2 = i < n ? cs2[i] : cs1[i - n];
            if (c1 != c2) {
                return c2 - c1;
            }
        }
        return 0;
    }
}
