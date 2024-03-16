package union_find_set;

/**
 * <a href="https://leetcode.cn/problems/number-of-provinces">547. 省份数量</a>
 *
 * @author zhou.xu
 * @since 2023/10/30 21:30
 */
public class NumberOfConnectedProvinceTest {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFinder unionFinder = new UnionFinder(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFinder.union(i, j);
                }
            }
        }
        return unionFinder.sets;
    }

    private static class UnionFinder {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionFinder(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            int p = 0;
            while (parent[i] != i) {
                help[p++] = i;
                i = parent[i];
            }
            while (p > 0) {
                parent[help[--p]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    parent[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    parent[f1] = f2;
                    size[f2] += size[f1];
                }
                sets--;
            }
        }
    }
}
