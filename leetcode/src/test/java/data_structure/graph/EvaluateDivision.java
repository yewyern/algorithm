package data_structure.graph;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/evaluate-division/">399. 除法求值</a>
 *
 * @author xuzhou
 * @since 2023/12/29 15:36
 */
public class EvaluateDivision {

    Map<String, List<Edge>> edges;
    Set<String> nodes;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        edges = new HashMap<>();
        nodes = new HashSet<>();
        int n = equations.size();
        int m = queries.size();
        for (int i = 0; i < n; i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];
            List<Edge> list1 = edges.computeIfAbsent(a, k -> new LinkedList<>());
            list1.add(new Edge(a, b, val));
            List<Edge> list2 = edges.computeIfAbsent(b, k -> new LinkedList<>());
            list2.add(new Edge(b, a, 1 / val));
            nodes.add(a);
            nodes.add(b);
        }
        double[] ans = new double[m];
        for (int i = 0; i < m; i++) {
            ans[i] = eval(queries.get(i));
        }
        return ans;
    }

    private double eval(List<String> query) {
        String a = query.get(0);
        String b = query.get(1);
        if (!edges.containsKey(a) || !nodes.contains(b)) {
            return -1;
        }
        return eval(a, b);
    }

    private double eval(String a, String b) {
        Queue<Edge> queue = new LinkedList<>(edges.get(a));
        Map<String, Double> path = new HashMap<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (path.containsKey(edge.b)) {
                continue;
            }
            double curr = path.getOrDefault(edge.a, 1d) * edge.val;
            if (b.equals(edge.b)) {
                return curr;
            }
            path.put(edge.b, curr);
            List<Edge> edgeList = edges.get(edge.b);
            if (edgeList != null) {
                for (Edge e : edgeList) {
                    if (!path.containsKey(e.b)) {
                        queue.add(e);
                    }
                }
            }
        }
        return -1;
    }

    class Edge {
        String a;
        String b;
        double val;

        public Edge(String a, String b, double val) {
            this.a = a;
            this.b = b;
            this.val = val;
        }
    }
}
