package search;

/**
 * <a href="https://leetcode.cn/problems/B1IidL/">LCR 069. 山脉数组的峰顶索引</a>
 * <a href="https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/">852. 山脉数组的峰顶索引</a>
 *
 * @author xuzhou
 * @since 2023/9/27 10:53
 */
public class PeakIndexInMountainArrayTest {

    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] > arr[m - 1] && arr[m] > arr[m + 1]) {
                return m;
            } else if (arr[m] < arr[m - 1]) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }

    public int peakIndexInMountainArray2(int[] arr) {
        int n = arr.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (arr[max] < arr[i]) {
                max = i;
            }
        }
        return max;
    }
}
