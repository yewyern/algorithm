package data_structure.graph.model;

/**
 * 图上的边
 *
 * @author zhou.xu
 * @since 2023/11/2 19:45
 */
public class Edge {

    /**
     * 起点
     */
    public Node from;
    /**
     * 终点
     */
    public Node to;
    /**
     * 权重
     */
    public int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
