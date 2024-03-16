package union_find_set;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph">2316. 统计无向图中无法互相到达点对数</a>
 *
 * @author zhou.xu
 * @since 2023/10/29 22:20
 */
public class CountCantReachablePairsInGraphTest {

    @Test
    public void test() {
        int[][] edges = new int[][]{{2, 3}, {4, 5}, {7, 8}, {5, 9}, {2, 10}};
        long l = countPairs(11, edges);
        System.out.println(l);
    }

    public long countPairs(int n, int[][] edges) {
        UnionFinder unionFinder = new UnionFinder(n);
        for (int[] edge : edges) {
            unionFinder.union(edge[0], edge[1]);
        }
        return unionFinder.countNotReach();
    }

    private static class UnionFinder {
        private int[] parents;
        private int[] sizeMap;
        private int[] help;
        private int size;

        public UnionFinder(int n) {
            this.parents = new int[n];
            this.sizeMap = new int[n];
            this.help = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizeMap[i] = 1;
            }
            size = n;
        }

        private int find(int i) {
            int n = 0;
            while (i != parents[i]) {
                help[n++] = i;
                i = parents[i];
            }
            for (int j = 0; j < n; j++) {
                parents[help[j]] = i;
            }
            return i;
        }

        private void union(int a, int b) {
            int aFather = find(a);
            int bFather = find(b);
            if (aFather != bFather) {
                int big = sizeMap[aFather] > sizeMap[bFather] ? aFather : bFather;
                int small = big == aFather ? bFather : aFather;
                parents[small] = big;
                sizeMap[big] += sizeMap[small];
                sizeMap[small] = 0;
                size--;
            }
        }

        public long countNotReach() {
            int n = sizeMap.length;
            int s = size;
            long total = n;
            long count = 0;
            for (int i = 0; i < n && s > 1; i++) {
                if (sizeMap[i] > 0) {
                    long next = total - sizeMap[i];
                    count += sizeMap[i] * next;
                    total = next;
                }
            }
            return count;
        }
    }
}
