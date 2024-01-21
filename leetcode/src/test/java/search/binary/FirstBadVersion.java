package search.binary;

/**
 * <a href="https://leetcode.cn/problems/first-bad-version">278. 第一个错误的版本</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 18:21
 */
public class FirstBadVersion implements VersionControl {

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l <= r) {
            int m = ((r - l) >> 1) + l;
            if (isBadVersion(m)) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    @Override
    public boolean isBadVersion(int version) {
        return Math.random() < 0.5;
    }
}

interface VersionControl {
    boolean isBadVersion(int version);
}
