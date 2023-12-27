package data_structure.cache;

/**
 * <a href="https://leetcode.cn/problems/lru-cache">146. LRU 缓存</a>
 *
 * @author xuzhou
 * @since 2023/12/26 16:47
 */
public class LRUCache {

    int capacity;
    int size;
    Node[] nodes;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.nodes = new Node[10001];
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        Node node = removeByKey(key);
        if (node == null) {
            return -1;
        }
        insertHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = removeByKey(key);
        if (node != null) {
            node.val = value;
        } else {
            node = new Node(key, value);
        }
        insertHead(node);
        if (size > capacity) {
            removeTail();
        }
    }

    private void insertHead(Node node) {
        nodes[node.key] = node;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    private void removeTail() {
        if (tail == null) {
            return;
        }
        removeByKey(tail.key);
    }

    private Node removeByKey(int key) {
        Node removed = nodes[key];
        if (removed == null) {
            return null;
        }
        nodes[key] = null;
        if (removed == head) {
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.prev = null;
            }
        } else if (removed == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            removed.prev.next = removed.next;
            removed.next.prev = removed.prev;
        }
        size--;
        removed.prev = null;
        removed.next = null;
        return removed;
    }

    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public Node(int key, int val, Node prev, Node next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "(" + key + "," + val + ") -> " + next;
        }
    }

}
