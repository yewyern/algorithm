package data_structure.graph.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 图中的点
 *
 * @author zhou.xu
 * @since 2023/11/2 19:43
 */
public class Node {

    /**
     * 节点值
     */
    public int val;

    /**
     * 入度
     */
    public int in;

    /**
     * 出度
     */
    public int out;

    /**
     * 从当期节点出发可以到达的点
     */
    public List<Node> next;

    /**
     * 从当前点出发的边
     */
    public List<Edge> edges;

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        next = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
