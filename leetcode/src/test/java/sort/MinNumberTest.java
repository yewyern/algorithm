package sort;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 45. 把数组排成最小的数</a>
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 * <p>
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 * <p>
 * <p>
 * 提示:
 * <p>
 * 0 < nums.length <= 100
 * 说明:
 * <p>
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 * @author xuzhou
 * @since 2023/7/18 10:02
 */
public class MinNumberTest {

    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        int N = nums.length;
        String[] helper = new String[N];
        for (int i = 0; i < N; i++) {
            helper[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(helper, this::compare);
        StringBuilder sb = new StringBuilder();
        for (String s : helper) {
            sb.append(s);
        }
        return sb.toString();
    }

    private int compare(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        int m = cs1.length;
        int n = cs2.length;
        int N = m + n;
        int p = 0, q = 0;
        while (p < N && q < N) {
            char c1 = p < m ? cs1[p] : cs2[p - m];
            char c2 = q < n ? cs2[q] : cs1[q - n];
            if (c1 != c2) {
                return c1 - c2;
            }
            p++;
            q++;
        }
        return 0;
    }
}
