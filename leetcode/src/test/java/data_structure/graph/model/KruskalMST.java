package data_structure.graph.model;

import java.util.*;

/**
 * 最小生成树算法 kruskal 贪心算法
 * 优先选最小的边
 * 当前边不会成环，进入结果集
 * 如果成环，则不进入结果集
 * 判断是否成环，使用并查集进行判断
 *
 * @author zhou.xu
 * @since 2023/11/16 19:51
 */
public class KruskalMST {

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(EdgeComparators.weightComparator);
        priorityQueue.addAll(graph.edges); // O(logn)
        Set<Edge> ans = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll(); // O(logn)
            if (!unionFind.isSameSet(edge.from, edge.to)) {// O(1)
                ans.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return ans;
    }

    public static class UnionFind {
        private Map<Node, Node> parent;
        private Map<Node, Integer> size;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.size = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            for (Node node : nodes) {
                parent.put(node, node);
                size.put(node, 1);
            }
        }

        private Node findFather(Node cur) {
            if (parent.get(cur) == cur) {
                return cur;
            }
            Node father = findFather(parent.get(cur));
            parent.put(cur, father);
            return father;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            Node fa = findFather(a);
            Node fb = findFather(b);
            if (fa != fb) {
                Node big = size.get(fa) > size.get(fb) ? fa : fb;
                Node small = big == a ? b : a;
                parent.put(small, big);
                size.put(big, size.get(big) + size.get(small));
            }
        }
    }
}
