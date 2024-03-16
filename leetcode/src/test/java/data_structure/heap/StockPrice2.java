package data_structure.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/stock-price-fluctuation">2034. 股票价格波动</a>
 * @author xuzhou
 * @since 2023/10/8 16:43
 */
public class StockPrice2 {

    private final Heap timeQueue = new Heap(Comparator.comparing(Node::getTimestamp).reversed());
    private final Heap maxQueue = new Heap(Comparator.comparing(Node::getPrice).reversed());
    private final Heap minQueue = new Heap(Comparator.comparing(Node::getPrice));

    public StockPrice2() {

    }

    public void update(int timestamp, int price) {
        Node node = new Node(timestamp, price);
        timeQueue.add(node);
        maxQueue.add(node);
        minQueue.add(node);
    }

    public int current() {
        return timeQueue.peek().price;
    }

    public int maximum() {
        return maxQueue.peek().price;
    }

    public int minimum() {
        return minQueue.peek().price;
    }

    private static class Node {
        int timestamp;
        int price;

        public Node(int timestamp, int price) {
            this.timestamp = timestamp;
            this.price = price;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public int getPrice() {
            return price;
        }
    }

    private static class Heap {
        private static final int capacity = 50000;
        private final Node[] queue = new Node[capacity];
        private int size = 0;
        private Map<Integer, Integer> map = new HashMap<>();
        private final Comparator<Node> comparator;

        private Heap(Comparator<Node> comparator) {
            this.comparator = comparator;
        }

        public void add(Node node) {
            Integer index = map.getOrDefault(node.timestamp, -1);
            if (index >= 0) {
                siftDown(index, node);
                if (queue[index] == node) {
                    siftUp(index, node);
                }
            } else {
                if (size == capacity) {
                    if (comparator.compare(node, queue[size - 1]) < 0) {
                        siftUp(size - 1, node);
                    }
                } else {
                    siftUp(size++, node);
                }
            }
        }

        public Node peek() {
            return queue[0];
        }

        private void siftUp(int k, Node x) {
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                Node e = queue[parent];
                if (comparator.compare(x, e) >= 0) {
                    break;
                }
                queue[k] = e;
                map.put(e.timestamp, k);
                k = parent;
            }
            queue[k] = x;
            map.put(x.timestamp, k);
        }

        private void siftDown(int k, Node x) {
            int half = size >>> 1;
            while (k < half) {
                int child = (k << 1) + 1;
                Node c = queue[child];
                int right = child + 1;
                if (right < size && comparator.compare(c, queue[right]) > 0) {
                    c = queue[child = right];
                }
                if (comparator.compare(x, c) <= 0) {
                    break;
                }
                queue[k] = c;
                map.put(c.timestamp, k);
                k = child;
            }
            queue[k] = x;
            map.put(x.timestamp, k);
        }

    }
}
