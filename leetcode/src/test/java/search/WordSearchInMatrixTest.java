package search;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/word-search/description/">剑指 Offer 12. 矩阵中的路径</a>
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * 注意：本题与主站 79 题相同：<a href="https://leetcode-cn.com/problems/word-search/">79. 单词搜索</a>
 *
 * @author zhou.xu
 * @since 2023/7/6 21:34
 */
public class WordSearchInMatrixTest {

    @Test
    public void test() {
        char[][] board = {{
                'A', 'B', 'C', 'E'
        }, {
                'S', 'F', 'C', 'S'
        }, {
                'A', 'D', 'E', 'E'
        }};
        String word = "ABCCED";
        boolean exist = exist(board, word);
        System.out.println(exist);
    }

    public boolean exist(char[][] board, String word) {
        // 暴力方法：m*n*word.length
        int m = board.length;
        int n = board[0].length;
        int[] masks = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (process(board, word, masks, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean process(char[][] board, String word, int[] masks, int row, int col, int index) {
        int m = board.length;
        int n = board[0].length;
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return false;
        }
        if ((masks[row] & (1 << col)) != 0) {
            return false;
        }
        if (board[row][col] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        masks[row] |= 1 << col;
        if (process(board, word, masks, row, col + 1, index + 1)) {
            return true;
        }
        if (process(board, word, masks, row + 1, col, index + 1)) {
            return true;
        }
        if (process(board, word, masks, row, col - 1, index + 1)) {
            return true;
        }
        if (process(board, word, masks, row - 1, col, index + 1)) {
            return true;
        }
        masks[row] ^= 1 << col;
        return false;
    }
}
