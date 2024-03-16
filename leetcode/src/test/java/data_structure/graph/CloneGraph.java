package data_structure.graph;


import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/clone-graph">133. 克隆图</a>
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * @author xuzhou
 * @since 2023/12/29 14:54
 */
public class CloneGraph {

    Map<Node, Node> nodeMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node copy = nodeMap.get(node);
        if (copy == null) {
            copy = new Node(node.val);
            nodeMap.put(node, copy);
            copy.neighbors = cloneGraph(node.neighbors);
        }
        return copy;
    }

    private List<Node> cloneGraph(List<Node> nodes) {
        return nodes.stream().map(this::cloneGraph).collect(Collectors.toList());
    }

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
