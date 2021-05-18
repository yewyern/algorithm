package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * <p>示例:
 * <p>
 * <p>输入: 3
 * <p>输出: [1,3,3,1]
 * <p>进阶：
 * <p>
 * <p>你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @author xzer
 */
public class TriangleGenerate2 {

    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex <= 0) {
            return Collections.singletonList(1);
        }
        List<Integer> curr = Arrays.asList(1, 1);
        for (int i = 2; i <= rowIndex; i++) {
            List<Integer> last = curr;
            curr = new ArrayList<>(i + 1);
            curr.add(1);
            for (int j = 0; j < i - 1; j++) {
                curr.add(last.get(j) + last.get(j + 1));
            }
            curr.add(1);
        }
        return curr;
    }

    /**
     * f(n, k) = f(n, k - 1) * (n - k + 1) / k
     *
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRowCombination(int rowIndex) {
        if (rowIndex <= 0) {
            return Collections.singletonList(1);
        }
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        res.add(1);
        res.add(1);
        long curr = 1;
        boolean isOdd = rowIndex % 2 == 0;
        int center = rowIndex / 2;
        for (int i = 1; i <= center; i++) {
            curr = curr * (rowIndex - i + 1) / i;
            res.add(i, (int) curr);
            if (i != center || !isOdd) {
                res.add(i, (int) curr);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("getRow(0) = " + getRowCombination(0));
        System.out.println("getRow(1) = " + getRowCombination(1));
        System.out.println("getRow(2) = " + getRowCombination(2));
        System.out.println("getRow(3) = " + getRowCombination(3));
        System.out.println("getRow(4) = " + getRowCombination(4));
        System.out.println("getRow(5) = " + getRowCombination(5));
    }
}