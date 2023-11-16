package data_structure.graph.model;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树 prim算法 贪心算法
 * 从任意点出发，解锁该点相关的边，选一条最小的
 * 选中一条边后解锁下一个点相关的所有边
 * 重复执行，点->边->点->边的过程，直到解锁所有的边
 *
 * @author zhou.xu
 * @since 2023/11/16 20:25
 */
public class PrimMST {

    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(EdgeComparators.weightComparator);
        Set<Node> visited = new HashSet<>();
        Set<Edge> ans = new HashSet<>();
        for (Node node : graph.nodes.values()) {// 防止存在不连通的图
            if (!visited.contains(node)) {
                visited.add(node);
                priorityQueue.addAll(node.edges); // 由点解锁边
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 当前最小的边
                    if (!visited.contains(edge.to)) { // 是新的点
                        ans.add(edge);
                        visited.add(edge.to);
                        priorityQueue.addAll(edge.to.edges); // 通过解锁点和对应的边
                    }
                }
            }
        }
        return ans;
    }

}
