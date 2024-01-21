package search.binary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/time-based-key-value-store/">981. 基于时间的键值存储</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 15:34
 */
public class TimeMap {

    private final Map<String, List<Node>> NODES = new HashMap<>();

    public TimeMap() {
    }

    public void set(String key, String value, int timestamp) {
        List<Node> list = NODES.computeIfAbsent(key, k -> new ArrayList<>());
        list.add(new Node(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<Node> nodes = NODES.get(key);
        if (nodes == null) {
            return "";
        }
        int n = nodes.size();
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            Node node = nodes.get(m);
            if (node.time == timestamp) {
                return node.value;
            } else if (node.time < timestamp) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        if (l > 0) {
            return nodes.get(l - 1).value;
        }
        return "";
    }

    private class Node {
        int time;
        String value;

        public Node(int time, String value) {
            this.time = time;
            this.value = value;
        }
    }
}
