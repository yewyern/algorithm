package str;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/multiply-strings/">43. 字符串相乘</a>
 *
 * @author xuzhou
 * @since 2022/11/10 9:55
 */
public class MultiplyStringsTest {

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if ("1".equals(num1)) {
            return num2;
        }
        if ("1".equals(num2)) {
            return num1;
        }
        int[] target = toIntArrayAndReverse(num1);
        int[] multiplier = toIntArrayAndReverse(num2);
        int n = target.length;
        int m = multiplier.length;
        int[] res = new int[n + m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int k = i + j;
                res[k] = res[k] + target[i] * multiplier[j];
                while (res[k] > 9) {
                    res[k + 1] += res[k] / 10;
                    res[k] = res[k] % 10;
                    k++;
                }
            }
        }
        return toIntString(reverse(res));
    }

    private int[] toIntArrayAndReverse(String num1) {
        char[] cs = num1.toCharArray();
        int n = cs.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[n - i - 1] = (short) (cs[i] - '0');
        }
        return res;
    }

    private int[] reverse(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[n - i - 1] = nums[i];
        }
        return res;
    }

    private String toIntString(int[] nums) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < nums.length && nums[i] == 0) {
            i++;
        }
        for (; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            int a = RandomUtil.randomInt(0, 10000000);
            int b = RandomUtil.randomInt(0, 10000000);
            long res = (long) a * b;
            String multiply = multiply(String.valueOf(a), String.valueOf(b));
            if (!String.valueOf(res).equals(multiply)) {
                System.out.println("a = " + a + ", b = " + b + ", multiply = " + multiply + ", res = " + res);
            }
        }
    }
}
