package data_structure.trie;

/**
 * <a href="https://leetcode.cn/problems/design-add-and-search-words-data-structure/">211. 添加与搜索单词 - 数据结构设计</a>
 * @author xuzhou
 * @since 2024/1/23 17:55
 */
public class WordDictionary {

    private Node root = new Node();

    public WordDictionary() {
    }

    public void addWord(String word) {
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
        return dfs(root, word.toCharArray(), 0);
    }

    private boolean dfs(Node node, char[] str, int i) {
        if (node == null) {
            return false;
        }
        int n = str.length;
        if (i == n) {
            return node.isEnd;
        }
        if (str[i] == '.') {
            for (Node child : node.children) {
                if (dfs(child, str, i + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(node.children[str[i] - 'a'], str, i + 1);
        }
    }

    class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }
}
