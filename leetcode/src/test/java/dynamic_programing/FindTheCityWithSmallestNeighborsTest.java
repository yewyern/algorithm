package dynamic_programing;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance">1334. 阈值距离内邻居最少的城市</a>
 * @author xuzhou
 * @since 2023/11/15 17:49
 */
public class FindTheCityWithSmallestNeighborsTest {
    Edge[] heap;
    int size;
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // todo
        int M = edges.length;
        heap = new Edge[M];
        size = 0;
        int[][] dist = new int[n][n];
        for (int[] edge : edges) {
            heap[size++] = new Edge(edge[0], edge[1], edge[2]);
        }
        return 0;
    }

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    @Test
    public void test() {
        System.out.println(findTheCity(4, new int[][]{{0,1,3},{1,2,1},{1,3,4},{2,3,1}}, 4));
    }

}
