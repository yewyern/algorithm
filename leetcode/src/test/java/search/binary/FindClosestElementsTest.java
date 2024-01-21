package search.binary;

import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/find-k-closest-elements">658. 找到 K 个最接近的元素</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 22:12
 */
public class FindClosestElementsTest {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // todo
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (arr[m] == x) {
                l = m;
                break;
            } else if (arr[m] > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return null;
    }
}
