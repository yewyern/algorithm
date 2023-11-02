package data_structure.graph.model;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author zhou.xu
 * @since 2023/11/2 20:12
 */
public class GraphUtils {

    // 图的宽度优先遍历
    public static void bfs(Node node, Consumer<Node> consumer) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            consumer.accept(curr); // to do sth
            for (Node next : curr.next) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
    }

    // 图的深度优先遍历
    public static void dfs(Node node, Consumer<Node> consumer) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        consumer.accept(node); // to do sth
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            for (Node next : curr.next) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    stack.push(curr);
                    stack.push(next);
                    consumer.accept(next); // to do sth
                    break;
                }
            }
        }
    }

    public static List<Node> topologySort(Graph graph) {
        List<Node> res = new ArrayList<>();
        Map<Node, Integer> inMap = new HashMap<>(); // 入度表
        Queue<Node> zeroInQueue = new LinkedList<>(); // 入度表中入度为0的进这个队列
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            res.add(cur);
            for (Node next : cur.next) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }
}
