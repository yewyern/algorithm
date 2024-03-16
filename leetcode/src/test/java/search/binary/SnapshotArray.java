package search.binary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/snapshot-array">1146. 快照数组</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 16:10
 */
public class SnapshotArray {

    private final Map<Integer, List<Node>> NODE_MAP = new HashMap<>();
    private int id = 0;

    public SnapshotArray(int length) {

    }

    public void set(int index, int val) {
        List<Node> nodes = NODE_MAP.computeIfAbsent(index, k -> new ArrayList<>());
        if (nodes.isEmpty()) {
            nodes.add(new Node(id, val));
        } else {
            Node node = nodes.get(nodes.size() - 1);
            if (node.snapId == id) {
                node.value = val;
            } else {
                nodes.add(new Node(id, val));
            }
        }
    }

    public int snap() {
        return id++;
    }

    public int get(int index, int snap_id) {
        List<Node> nodes = NODE_MAP.get(index);
        if (nodes == null) {
            return 0;
        }
        int l = 0, r = nodes.size() - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            Node node = nodes.get(m);
            if (node.snapId == snap_id) {
                return node.value;
            } else if (node.snapId < snap_id) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l == 0 ? 0 : nodes.get(l - 1).value;
    }

    private class Node {
        int snapId;
        int value;

        public Node(int snapId, int value) {
            this.snapId = snapId;
            this.value = value;
        }
    }
}
