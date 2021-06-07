package presum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import utils.RandomArray;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 *
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 *
 * @author xuzhou
 * @date 2021/6/3 9:45
 */
public class FindMaxLength {

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(sum)) {
                len = Math.max(len, i - map.get(sum));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public int findMaxLengthComparison(int[] nums) {
        // 暴力方法，O(n^2)
        int N = nums.length;
        int sum = 0;
        int len = 0;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nums[i] == 0 ? -1 : 1;
            sum += arr[i];
        }
        if (sum == 0) {
            return N;
        }
        if (sum == N || sum == -N) {
            return 0;
        }
        for (int i = 0; i < N; i++) {
            int temp = sum;
            for (int n = N - 1; n > i; n--) {
                if (temp == 0) {
                    // 因为从i开头的，到n结尾能满足，就没必要再看到n-1是不是满足了
                    // 但是从i+1开头的，不一定比i开头的结果差
                    len = Math.max(len, n - i + 1);
                    break;
                }
                temp -= arr[n];
            }
            sum -= arr[i];
        }
        return len;
    }

    public int findMaxLengthComparison2(int[] nums) {
        // 双指针往中间收缩，有bug，两边的数字一样的时候无法取舍
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int sum = 0;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = nums[i] == 0 ? -1 : 1;
            sum += arr[i];
        }
        int l = 0, r = len - 1;
        while (l < r) {
            if (sum == 0) {
                return r - l + 1;
            }
            //0011111110000
            if (sum > 0) {
                if (arr[l] > 0) {
                    sum--;
                    l++;
                } else if (arr[r] > 0) {
                    sum--;
                    r--;
                } else {
                    int l1 = l;
                    int r1 = r;
                    while (l1 < r && arr[l1] < 0) {
                        l1++;
                    }
                    while (r1 > l && arr[r1] < 0) {
                        r1--;
                    }
                    if (l1 - l > r - r1) {
                        sum += r - r1;
                        r = r1;
                    } else {
                        sum += l1 - l;
                        l = l1;
                    }
                }
            } else {
                if (arr[l] < 0) {
                    sum++;
                    l++;
                } else if (arr[r] < 0) {
                    sum++;
                    r--;
                } else {
                    int l1 = l;
                    int r1 = r;
                    int suml1 = 0;
                    int sumr1 = 0;
                    while (l1 < r && arr[l1] > 0) {
                        suml1 += arr[l1++];
                    }
                    while (r1 > l && arr[r1] > 0) {
                        sumr1 += arr[r1--];
                    }
                    if (l1 - l > r - r1) {
                        sum -= sumr1;
                        r = r1;
                    } else {
                        sum -= suml1;
                        l = l1;
                    }
                }
            }
        }
        return 0;
    }

    @Test
    public void testByComparison() {
        int test = 10000;
        int maxLen = 10000;
        for (int i = 0; i < test; i++) {
            int[] nums = RandomArray.generateRandomLengthNoEmptyArray(maxLen, 2);
            testComparison(nums);
        }
        int[] nums = new int[maxLen];
        Arrays.fill(nums, 1);
        testComparison(nums);
        Arrays.fill(nums, 0);
        testComparison(nums);
    }

    private void testComparison(int[] nums) {
        int maxLength = findMaxLength(nums);
        int maxLengthComparison = findMaxLengthComparison(nums);
        if (maxLength != maxLengthComparison) {
            System.out.println("nums = " + Arrays.toString(nums));
            System.out.println("maxLength = " + maxLength);
            System.out.println("maxLengthComparison = " + maxLengthComparison);
            System.out.println("----------------------------------------");
        }
    }

    @Test
    public void test() {
        int maxLength = findMaxLengthComparison(new int[]{0, 1});
        System.out.println("maxLength = " + maxLength);
        int maxLength1 = findMaxLengthComparison(new int[]{0, 1, 0});
        System.out.println("maxLength1 = " + maxLength1);
        int maxLength2 = findMaxLengthComparison(new int[]{0, 0, 0, 1, 1});
        System.out.println("maxLength2 = " + maxLength2);
        int maxLength3 = findMaxLengthComparison(new int[]{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0});
        System.out.println("maxLength2 = " + maxLength3);
    }

}
