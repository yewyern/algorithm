package sort;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/h-index">274. H 指数</a>
 * @author xuzhou
 * @since 2023/11/16 10:30
 */
public class HIndexTest {

    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1]; // i = 引用次数，count[i] = 引用次数为i的论文数
        for (int citation : citations) {
            if (citation >= n) {
                count[n]++;
            } else {
                count[citation]++;
            }
        }
        int hIndex = 0;
        for (int i = n; i >= 0; i--) {
            hIndex += count[i];
            if (hIndex >= i) {
                return i;
            }
        }
        return hIndex;
    }

    public int hIndex1(int[] citations) {
        // 对citations排序，从大到小计算hIndex，当hIndex要超过论文篇数时返回
        int n = citations.length;
        Arrays.sort(citations);
        int hIndex = 0;
        for (int i = n - 1; i >= 0 && citations[i] > hIndex; i--) {
            hIndex++;
        }
        return hIndex;
    }

    public int hIndex2(int[] citations) {
        // 暴力解法：求出所有的hIndex
        int n = citations.length;
        int[] hCount = new int[n];
        for (int citation : citations) {
            int m = Math.min(n, citation);
            for (int i = 0; i < m; i++) {
                hCount[i]++;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (hCount[i] > i) {
                return i + 1;
            }
        }
        return 0;
    }
}
