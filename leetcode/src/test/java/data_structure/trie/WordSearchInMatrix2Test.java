package data_structure.trie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/word-search-ii">212. 单词搜索 II</a>
 * @author xuzhou
 * @since 2024/1/26 13:54
 */
public class WordSearchInMatrix2Test {

    @Test
    public void test() {
        List<String> words = findWords(new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}}, new String[]{"oath", "pea", "eat", "rain"});
        System.out.println(words);
    }
    String[] words;
    Trie trie;
    Set<String> set;
    int m;
    int n;
    public List<String> findWords(char[][] board, String[] words) {
        this.words = words;
        set = new HashSet<>();
        trie = initTrie(words);
        m = board.length;
        n = board[0].length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, trie.root, i, j, sb);
            }
        }
        return new ArrayList<>(set);
    }

    private Trie initTrie(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.addWord(word);
        }
        return trie;
    }

    private void dfs(char[][] board, Node curr, int i, int j, StringBuilder sb) {
        if (set.size() == words.length) {
            return;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == ' ') {
            return;
        }
        char c = board[i][j];
        Node next = trie.next(curr, c);
        if (next == null) {
            return;
        }
        sb.append(c);
        board[i][j] = ' ';
        if (next.isEnd) {
            String word = sb.toString();
            trie.delete(word);
            set.add(word);
        }
        dfs(board, next, i + 1, j, sb);
        dfs(board, next, i - 1, j, sb);
        dfs(board, next, i, j + 1, sb);
        dfs(board, next, i, j - 1, sb);
        board[i][j] = c;
        sb.deleteCharAt(sb.length() - 1);
    }

    private class Trie {

        Node root = new Node();

        public void addWord(String word) {
            Node curr = root;
            for (char c : word.toCharArray()) {
                curr.count++;
                int i = c - 'a';
                if (curr.children[i] == null) {
                    curr.children[i] = new Node();
                }
                curr = curr.children[i];
            }
            curr.count++;
            curr.isEnd = true;
        }

        public Node next(Node node, char c) {
            int i = c - 'a';
            return node.children[i];
        }

        public void delete(String word) {
            Node curr = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                Node child = curr.children[i];
                if (--child.count == 0) {
                    curr.children[i] = null;
                }
                curr = child;
            }
            curr.isEnd = false;
        }
    }

    private class Node {
        Node[] children = new Node[26];
        boolean isEnd;
        int count = 0;
    }

    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    // 先找到word 中第一个字母在board中的位置
    // 找到了就进入递归方法，依次判断其周围的四个位置是否有 word 下一个字母
    public boolean exist(char[][] board, String word) {
        // leetcode速度最快的答案
        char[] chars = word.toCharArray();
        // 确定是顺序搜索还是逆序搜索
        int order = 0, reverse = 0;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == chars[0]) order++;
                else if (board[i][j] == chars[chars.length - 1]) reverse++;
            }
        }
        // order 大说明最前面的字母在board出现次数比较多，因此要采用逆序，因为开头的字母不符合就能快速退出递归
        if (order > reverse) {
            chars = new StringBuilder(word).reverse().toString().toCharArray();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (judge(board, chars, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    用改变 board[h][w] = ' ' 来替代 judgeArr 的作用，判断该字母是否已经被用过
    num 在每次递归时自增，代表走到了word中的下一个字母
    h 代表了是 board 中的第几行，传入的是上一次递归时找到当前字母时的行位置
    w 代表了是 board 中的第几列，传入的是上一次递归时找到当前字母时的列位置
    递归方法：在 board 中，上一次找到的字母四边是否有和当前要判断的字母相同的字母，且该位置之前没被走过
    */
    public boolean judge(char[][] board, char[] word, int num, int h, int w) {
        int m = board.length;
        int n = board[0].length;
        if (num == word.length) {
            return true;
        }
        if (h < 0 || h >= m || w < 0 || w >= n || board[h][w] != word[num]) {
            return false;
        }
        char temp = board[h][w];
        board[h][w] = ' '; // 修改当前字符，表示已访问
        boolean result = judge(board, word, num + 1,h - 1, w) ||
                judge(board, word, num + 1, h + 1, w) ||
                judge(board, word, num + 1, h, w + 1) ||
                judge(board, word, num + 1, h, w - 1);
        board[h][w] = temp; // 恢复当前字符
        return result;
    }
}
