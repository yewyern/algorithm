package search.binary;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/find-k-closest-elements">658. 找到 K 个最接近的元素</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 22:12
 */
public class FindClosestElementsTest {

    @Test
    public void test() {
        System.out.println(findClosestElements(new int[]{0, 0, 0, 1, 3, 5, 6, 7, 8, 8}, 2, 2));
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int l = 0, r = n - k;
        while (l < r) {
            int m = (l + r) >> 1;
            if (x - arr[m] > arr[m + k] - x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        int start = l;
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return arr[start + index];
            }

            @Override
            public int size() {
                return k;
            }
        };
    }

    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        int n = arr.length;
        if (k == n || arr[0] >= x) {
            return toList(arr, 0, k);
        }
        if (arr[n - 1] <= x) {
            return toList(arr, n - k, n);
        }
        int l = 0, r = n;
        x = x << 1;
        while (r - l > k) {
            int target = x - arr[l];
            r = binarySearch(arr, l + k, r, target);
            if (r - l > k) {
                target = x - arr[r - 1];
                l = binarySearch(arr, l, r - k, target);
            }
        }
        return toList(arr, l, r);
    }

    public int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int l = fromIndex, r = toIndex - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (a[m] >= key) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private List<Integer> toList(int[] arr, int startInclusive, int endExclusive) {
        List<Integer> res = new ArrayList<>(endExclusive - startInclusive);
        for (int i = startInclusive; i < endExclusive; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int n = arr.length;
        if (k == n) {
            return toList(arr, 0, n);
        }
        // 双指针
        int l = 0, r = n - 1;
        int d1 = Math.abs(arr[l] - x);
        int d2 = Math.abs(arr[r] - x);
        while (r - l >= k) {
            if (d1 == d2 || d1 < d2) {
                d2 = Math.abs(arr[--r] - x);
            } else {
                d1 = Math.abs(arr[++l] - x);
            }
        }
        return toList(arr, l, r + 1);
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int n = arr.length;
        if (k == n) {
            return toList(arr, 0, n);
        }
        // 先找x
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
        r = Math.min(l + k, n);
        l = Math.max(l - k, 0);
        // 双指针
        int d1 = Math.abs(arr[l] - x);
        int d2 = Math.abs(arr[r] - x);
        while (r - l >= k) {
            if (d1 == d2 || d1 < d2) {
                d2 = Math.abs(arr[--r] - x);
            } else {
                d1 = Math.abs(arr[++l] - x);
            }
        }
        return toList(arr, l, r + 1);
    }
}
