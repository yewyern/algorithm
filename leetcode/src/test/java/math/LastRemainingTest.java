package math;

import org.junit.Test;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/">剑指 Offer 62. 圆圈中最后剩下的数字</a>
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * <p>
 * 输入: n = 10, m = 17
 * 输出: 2
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 *
 * @author xuzhou
 * @since 2023/8/9 11:07
 */
public class LastRemainingTest {

    public int lastRemaining(int n, int m) {
        return 0;
    }

    public int lastRemaining2(int n, int m) {
        List<Integer> remaining = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            remaining.add(i);
        }
        int last = 0;
        while (n > 1) {
            int cur = (last + m - 1) % n;
            remaining.remove(cur);
//            System.out.println(remaining.remove(cur));
            last = cur;
            n--;
        }
        return remaining.get(0);
    }

    @Test
    public void test() {
        lastRemaining(96, 173);
        for (int i = 0; i < 10000; i++) {
            int n = RandomUtils.nextInt(1, 10000);
            int m = RandomUtils.nextInt(1, 100000);
            int res1 = lastRemaining(n, m);
            int res2 = lastRemaining2(n, m);
            if (res1 != res2) {
                System.out.println("n = " + n);
                System.out.println("m = " + m);
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                break;
            }
        }
    }
}
