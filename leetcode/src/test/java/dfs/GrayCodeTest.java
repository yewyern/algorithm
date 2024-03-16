package dfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/gray-code/">89. 格雷编码</a>
 *
 * @author xuzhou
 * @since 2023/10/9 18:27
 */
public class GrayCodeTest {

    @Test
    public void test() {
        for (int i = 1; i < 17; i++) {
            System.out.println(grayCode(i));
        }
    }

    private static final List<Integer> cache = new ArrayList<>(1 << 16);
    private static final boolean[] visited = new boolean[1 << 16];

    static {
        cache.add(0);
        visited[0] = true;
    }

    public List<Integer> grayCode(int n) {
        int size = 1 << n;
        if (cache.size() >= size) {
            return cache.stream().limit(size).collect(Collectors.toList());
        }
        dfs(n, size, size - 1);
        return cache;
    }

    private void dfs(int n, int size, int mark) {
        int last = cache.get(cache.size() - 1);
        for (int i = 0; i < n; i++) {
            int curr = last ^ (1 << i);
            if (!visited[curr]) {
                if ((curr & (curr - 1)) == 0) {
                    mark ^= curr;
                    if (mark == 0 && cache.size() != size - 1) {
                        mark |= curr;
                        continue;
                    }
                }
                visited[curr] = true;
                cache.add(curr);
                dfs(n, size, mark);
                if (cache.size() == size) {
                    int xor = cache.get(size - 1);
                    if ((xor & (xor - 1)) == 0) {
                        return;
                    }
                }
                cache.remove(cache.size() - 1);
                visited[curr] = false;
            }
        }
    }
}
