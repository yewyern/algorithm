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
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        boolean exist = exist(board, word);
        System.out.println(exist);
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

    public boolean existComparison(char[][] board, String word) {
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


    public boolean existComparison2(char[][] board, String word) {
        // 官方写法：192ms
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (process(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean process(char[][] board, String word, boolean[][] visited, int row, int col, int index) {
        if (board[row][col] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        visited[row][col] = true;
        int m = board.length;
        int n = board[0].length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] direction : directions) {
            int r1 = row + direction[0], c1 = col + direction[1];
            if (r1 < 0 || r1 >= m || c1 < 0 || c1 >= n || visited[r1][c1]) {
                continue;
            }
            if (process(board, word, visited, r1, c1, index + 1)) {
                return true;
            }
        }
        visited[row][col] = false;
        return false;
    }

}
