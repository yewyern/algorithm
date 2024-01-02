package data_structure.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/course-schedule-ii/">210. 课程表 II</a>
 * @author xuzhou
 * @since 2023/12/29 18:04
 */
public class CourseSchedule2 {


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 拓扑排序
        int[] ans = new int[numCourses];
        Node[] nodes = new Node[numCourses];
        for (int i = 0; i < numCourses; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] prerequisite : prerequisites) {
            nodes[prerequisite[0]].in++;
            nodes[prerequisite[1]].addEdge(new Edge(nodes[prerequisite[1]], nodes[prerequisite[0]]));
        }
        int size = 0;
        for (Node node : nodes) {
            if (node.in == 0) {
                ans[size++] = node.index;
            }
        }
        int i = 0;
        while (i < size && size < numCourses) {
            Node curr = nodes[ans[i++]];
            for (Edge edge : curr.edges) {
                edge.to.in--;
                if (edge.to.in == 0) {
                    ans[size++] = edge.to.index;
                }
            }
        }
        return size == numCourses ? ans : new int[0];
    }

    private static class Node {
        int index;
        int in;
        List<Edge> edges = new LinkedList<>();

        public Node(int index) {
            this.index = index;
        }

        void addEdge(Edge edge) {
            edges.add(edge);
        }
    }

    private static class Edge {
        Node from;
        Node to;

        public Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }
    }

}
