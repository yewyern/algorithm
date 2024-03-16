package data_structure.graph.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * dijkstra算法 单源最短路径问题，有向无负环
 * 算法描述：
 * 初始化时，当前节点距离为0，其他节点距离为无穷大
 * 从一个点出发，根据点的直接边，计算到达点的最短距离并更新，更新完成后，该点处理结束，不再做处理
 * 在剩余的节点中，找一个距离最近的，重复上述过程，直到所有的节点处理完
 *
 * @author zhou.xu
 * @since 2023/11/16 22:21
 */
public class Dijkstra {

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

}
