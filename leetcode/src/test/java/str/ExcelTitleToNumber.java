package str;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/excel-sheet-column-number">171. Excel 表列序号</a>
 * <p>给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * <p> 例如，
 * <p>
 * <p>     A -> 1
 * <p>     B -> 2
 * <p>     C -> 3
 * <p>     ...
 * <p>     Z -> 26
 * <p>     AA -> 27
 * <p>     AB -> 28
 * <p>     ...
 * <p> 示例 1:
 * <p>
 * <p> 输入: "A"
 * <p> 输出: 1
 * <p> 示例 2:
 * <p>
 * <p> 输入: "AB"
 * <p> 输出: 28
 *
 * @author : zhou.xu
 * @since : 2020/6/4 15:53
 */
public class ExcelTitleToNumber {

    public int titleToNumber(String s) {
        char[] cs2 = s.toCharArray();
        int res = 0;
        for (char c : cs2) {
            res = res * 26 + c - 'A' + 1;
        }
        return res;
    }

    private int test(String s) {
        return titleToNumber(s);
    }

    @Test
    public void test() {
        System.out.println("A = " + test("A"));
        System.out.println("AB = " + test("AB"));
        System.out.println("ZY = " + test("ZY"));
    }
}