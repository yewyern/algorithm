package search.binary;

/**
 * <a href="https://leetcode.cn/problems/h-index-ii/">275. H 指数 II</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 21:15
 */
public class HIndex2Test {

    public int hIndex(int[] citations) {
        // search target: citations[i] >= n - i
        int n = citations.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (citations[m] >= n - m) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return n - l;
    }
}
