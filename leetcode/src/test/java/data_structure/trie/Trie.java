package data_structure.trie;

/**
 * <a href="https://leetcode.cn/problems/implement-trie-prefix-tree/">208. 实现 Trie (前缀树)</a>
 * @author xuzhou
 * @since 2024/1/22 18:18
 */
public class Trie {

    private Node root = new Node();
    public Trie() {

    }

    public void insert(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (curr.children[i] == null) {
                curr.children[i] = new Node();
            }
            curr = curr.children[i];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (curr.children[i] == null) {
                return false;
            }
            curr = curr.children[i];
        }
        return curr.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (curr.children[i] == null) {
                return false;
            }
            curr = curr.children[i];
        }
        return true;
    }

    class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }
}
