package easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * <p>示例:
 * <p>
 * <p>输入: 5
 * <p>输出:
 * <p>[
 * <p>     [1],
 * <p>    [1,1],
 * <p>   [1,2,1],
 * <p>  [1,3,3,1],
 * <p> [1,4,6,4,1]
 * <p>]
 * <p>
 *
 * @author xzer
 */
public class TriangleGenerate {

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return Collections.emptyList();
        }
        if (numRows < 0) {
            numRows = 1;
        }
        List<List<Integer>> res = new ArrayList<>(numRows);
        List<Integer> curr = null;
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                curr = Collections.singletonList(1);
            } else {
                List<Integer> last = curr;
                curr = new ArrayList<>(i);
                curr.add(1);
                for (int j = 1; j < i; j++) {
                    curr.add(last.get(j - 1) + last.get(j));
                }
                curr.add(1);
            }
            res.add(curr);
        }
        return res;
    }

    public static void main(String[] args) {
        TriangleGenerate generate = new TriangleGenerate();
        System.out.println("generate.generate(5) = " + generate.generate(10));
    }
}