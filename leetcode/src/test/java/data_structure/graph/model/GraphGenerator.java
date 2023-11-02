package data_structure.graph.model;

/**
 * @author zhou.xu
 * @since 2023/11/2 19:56
 */
public class GraphGenerator {

    public static Graph generate(int nodeCount, int[][] edges) {
        Graph graph = new Graph();
        for (int i = 0; i < nodeCount; i++) {
            graph.nodes.put(i, new Node(i));
        }
        for (int[] edge : edges) {
            Node to = graph.nodes.get(edge[0]);
            Node from = graph.nodes.get(edge[1]);
            from.edges.add(new Edge(from, to, 0));
            from.next.add(to);
            from.out++;
            to.in++;
        }
        return graph;
    }

    public static Node toNode(Graph graph, int val) {
        if (!graph.nodes.containsKey(val)) {
            graph.nodes.put(val, new Node(val));
        }
        return graph.nodes.get(val);
    }
}
