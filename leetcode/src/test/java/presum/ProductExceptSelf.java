package presum;

/**
 * <a href="https://leetcode.cn/problems/product-of-array-except-self">238. 除自身以外数组的乘积</a>
 * @author xuzhou
 * @since 2023/11/17 17:17
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int product = 1;
        for (int i = n - 1; i > 0; i--) {
            product *= nums[i];
            ans[i] = product;
        }
        product = 1;
        for (int i = 0; i < n - 1; i++) {
            ans[i] = ans[i + 1] * product;
            product *= nums[i];
        }
        ans[n - 1] = product;
        return ans;
    }
}
