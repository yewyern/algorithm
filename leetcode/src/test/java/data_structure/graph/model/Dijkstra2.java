package data_structure.graph.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * dijkstra算法优化版
 *
 * @author zhou.xu
 * @since 2023/11/17 20:14
 */
public class Dijkstra2 {

    public static Map<Node, Integer> dijkstra(Node from) {
        Map<Node, Integer> dist = new HashMap<>();
        dist.put(from, 0);
        Set<Node> visited = new HashSet<>();
        Node minNode = from;
        while (minNode != null) {
            int distance = dist.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                if (dist.containsKey(to)) {
                    dist.put(to, Math.min(distance + edge.weight, dist.get(to)));
                } else {
                    dist.put(to, distance + edge.weight);
                }
            }
            visited.add(minNode);
            minNode = getMinNode(dist, visited);
        }
        return dist;
    }

    private static Node getMinNode(Map<Node, Integer> dist, Set<Node> visited) {
        // 此方法需要使用加强堆进行优化
        return dist.entrySet().stream()
                .filter(e -> !visited.contains(e.getKey()))
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static class Heap {
        Node[] nodes;
        Map<Node, Integer> indexMap;
        Map<Node, Integer> distanceMap;
        int size;

        public Heap(int capacity) {
            nodes = new Node[capacity];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                // 在堆上，更新
                distanceMap.put(node, Math.min(distance, distanceMap.get(node)));
                siftUp(node, indexMap.get(node));
            } else if (!isEntered(node)) {
                // 没进来过
                nodes[size] = node;
                indexMap.put(node, size);
                distanceMap.put(node, distance);
                siftUp(node, size++);
            }
        }

        public HeapNode pop() {
            HeapNode ans = new HeapNode(nodes[0], distanceMap.get(nodes[0]));
            indexMap.put(nodes[0], -1);
            distanceMap.remove(nodes[0]);
            swap(0, --size);
            siftDown(nodes[0], 0);
            return ans;
        }

        private void siftUp(Node node, int k) {
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                if (distanceMap.get(nodes[parent]) <= distanceMap.get(node)) {
                    break;
                }
                swap(k, parent);
                k = parent;
            }
        }

        private void siftDown(Node node, int k) {
            int half = size >>> 1;
            while (k < half) {
                int child = (k << 1) + 1;
                int rightChild = child + 1;
                if (distanceMap.get(nodes[rightChild]) < distanceMap.get(nodes[child])) {
                    child = rightChild;
                }
                if (distanceMap.get(node) <= distanceMap.get(nodes[child])) {
                    break;
                }
                swap(k, child);
                k = child;
            }
        }

        private boolean inHeap(Node node) {
            return indexMap.getOrDefault(node, -1) != -1;
        }

        private boolean isEntered(Node node) {
            return indexMap.containsKey(node);
        }

        private void swap(int i, int j) {
            indexMap.put(nodes[i], j);
            indexMap.put(nodes[j], i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }
    }

    private static class HeapNode {
        Node node;
        int distance;

        public HeapNode(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

}
