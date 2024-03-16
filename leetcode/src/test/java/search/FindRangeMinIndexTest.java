package search;

import java.util.Arrays;
import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * 在一个未排序的数组中，任意相邻的2个数均不相等，查找任意一个区域内最小值的索引
 * 定义：区域最小值，即a[i]满足：，
 * 当a.len = 0时，i = -1
 * 当a.len = 1时，i = 0
 * 当i = 0时，只需a[0] < a[1]
 * 当i = a.len -1时，只需a[i] < a[i-1]
 *
 * @author zhou.xu
 * @since 2022/10/26 21:37
 */
public class FindRangeMinIndexTest {

    public int findRangeMinIndex(int[] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        if (a.length == 1) {
            return 0;
        }
        int L = 0, R = a.length - 1;
        while (L < R - 1) {
            int mid = (L + R) >> 1;
            if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) {
                return mid;
            }
            if (a[mid] > a[mid - 1]) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return a[L] < a[R] ? L : R;
    }

    public boolean check(int[] a, int i) {
        if (a == null || a.length == 0) {
            return i == -1;
        }
        int N = a.length;
        if (i == 0) {
            return N == 1 || a[0] < a[1];
        }
        if (i == N - 1) {
            return a[N - 1] < a[N - 2];
        }
        return a[i] < a[i - 1] && a[i] < a[i + 1];
    }


    @Test
    public void test() {
        System.out.println("测试开始");
        for (int i = 0; i < 10000; i++) {
            int[] nums = RandomArray.generateAdjacentNonEqualArray(RandomUtils.nextInt(100), 0, 1000);
            int rangeMinIndex = findRangeMinIndex(nums);
            if (!check(nums, rangeMinIndex)) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("rangeMinIndex = " + rangeMinIndex);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
