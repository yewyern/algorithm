package union_find_set;

/**
 * <a href="https://leetcode.cn/problems/reachable-nodes-with-restrictions">2368. 受限条件下可到达节点的数目</a>
 * @author xuzhou
 * @since 2024/3/11 15:18
 */
public class ReachableNodesTest {

    boolean[] restrictedNodes; // 受限节点
    int[] parent; // 并查集-父节点map
    int[] size; // 并查集-size map
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        parent = new int[n];
        size = new int[n];
        restrictedNodes = new boolean[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int i : restricted) {
            restrictedNodes[i] = true;
        }
        for (int[] edge : edges) {
            if (restrictedNodes[edge[0]] || restrictedNodes[edge[1]]) {
                // 有节点受限
                continue;
            }
            union(edge[0], edge[1]);
        }
        return size[get(0)];
    }

    private int get(int i) {
        int p = parent[i];
        if (p != i) {
            parent[i] = get(parent[i]);
        }
        return p;
    }

    private void union(int i, int j) {
        int p1 = get(i);
        int p2 = get(j);
        if (p1 != p2) {
            if (size[p1] >= size[p2]) {
                parent[p2] = p1;
                size[p1] += size[p2];
            } else {
                parent[p1] = p2;
                size[p2] += size[p1];
            }
        }
    }
}
