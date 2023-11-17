package greedy_algorithm;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/candy">135. 分发糖果</a>
 * @author xuzhou
 * @since 2023/11/17 17:40
 */
public class GiveCandyTest {

    public int candy(int[] ratings) {
        // todo
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
                if (i - maxIndex >= max - 1) {
                    // 到最大值都要加1
                    ans += i - maxIndex;
                } else {
                    // 除了最大值的都加1
                    ans += i - maxIndex - 1;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(candy(new int[]{1,2,2}));
    }
}
