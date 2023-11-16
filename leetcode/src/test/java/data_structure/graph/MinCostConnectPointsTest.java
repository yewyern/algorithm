package data_structure.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/min-cost-to-connect-all-points/">1584. 连接所有点的最小费用</a>
 *
 * @author zhou.xu
 * @since 2023/11/16 21:11
 */
public class MinCostConnectPointsTest {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        // kruskal算法
        // 初始化并查集
        int[] parents = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            size[i] = 1;
        }
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 初始化所有边
        for (int i = 0; i < n - 1; i++) {
            int[] from = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] to = points[j];
                priorityQueue.add(new Edge(i, j, Math.abs(to[0] - from[0]) + Math.abs(to[1] - from[1])));
            }
        }
        n--; // 总共需要n-1条边
        int cost = 0;
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!isSameSet(parents, edge.from, edge.to)) {
                cost += edge.weight;
                n--; // 找到一条边
                if (n == 0) {
                    break;
                }
                union(parents, size, edge.from, edge.to);
            }
        }
        return cost;
    }

    private int find(int[] parents, int i) {
        if (parents[i] == i) {
            return i;
        }
        int parent = find(parents, parents[i]);
        parents[i] = parent;
        return parent;
    }

    private boolean isSameSet(int[] parents, int i, int j) {
        return find(parents, i) == find(parents, j);
    }

    private void union(int[] parents, int[] size, int i, int j) {
        int p1 = find(parents, i);
        int p2 = find(parents, j);
        if (p1 != p2) {
            if (size[p1] >= size[p2]) {
                parents[p2] = p1;
                size[p1] += size[p2];
            } else {
                parents[p1] = p2;
                size[p2] += size[p1];
            }
        }
    }

    public int minCostConnectPointsPrim(int[][] points) {
        int n = points.length;
        boolean[] visitedPoints = new boolean[n];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        int cost = 0;
        // prim算法，从0号点开始解锁
        visitedPoints[0] = true;
        n--;
        addEdges(points, visitedPoints, priorityQueue, 0);
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!visitedPoints[edge.to]) {
                visitedPoints[edge.to] = true;
                cost += edge.weight;
                n--;
                if (n == 0) {
                    break;
                }
                addEdges(points, visitedPoints, priorityQueue, edge.to);
            }
        }
        return cost;
    }

    private void addEdges(int[][] points, boolean[] visitedPoints, PriorityQueue<Edge> priorityQueue, int cur) {
        int n = points.length;
        int[] from = points[cur];
        for (int i = 0; i < n; i++) {
            int[] to = points[i];
            if (!visitedPoints[i] && cur != i) {
                priorityQueue.add(new Edge(cur, i, Math.abs(to[0] - from[0]) + Math.abs(to[1] - from[1])));
            }
        }
    }

    private static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
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
}
