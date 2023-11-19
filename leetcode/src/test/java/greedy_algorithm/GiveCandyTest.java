package greedy_algorithm;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/candy">135. 分发糖果</a>
 *
 * @author xuzhou
 * @since 2023/11/17 17:40
 */
public class GiveCandyTest {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        candies[0] = 1;
        // 1、从左往右发（往右升序的处理）
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }
        // 2、从右往左发（往右是降序的处理）
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }
        // 3、统计结果
        int ans = 0;
        for (int candy : candies) {
            ans += candy;
        }
        return ans;
    }

    public int candy2(int[] ratings) {
        int n = ratings.length;
        int ans = 0;
        int max = 1, maxIndex = 0;
        int cur = 1;
        int pre = ratings[0];
        for (int i = 0; i < n; i++) {
            if (ratings[i] == pre) {
                // 跟左边一样，1颗糖
                ans++;
                cur = 1;
                max = 1;
                maxIndex = i;
            } else if (ratings[i] > pre) {
                // 比左边大，要比左边多一个
                cur++;
                ans += cur;
                max = cur;
                maxIndex = i;
            } else {
                // 比左边小
                cur = 1;
                ans++;
                if (i - maxIndex > max - 1) {
                    // 到最大值都要加1
                    ans += i - maxIndex;
                } else {
                    // 除了最大值的都加1
                    ans += i - maxIndex - 1;
                }
            }
            pre = ratings[i];
        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(candy(new int[]{1, 3, 2, 2, 1}));
    }
}
