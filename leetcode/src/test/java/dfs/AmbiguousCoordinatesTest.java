package dfs;

import cn.hutool.core.util.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/ambiguous-coordinates/">816. 模糊坐标</a>
 * 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
 *
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 *
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 *
 *
 *
 * 示例 1:
 * 输入: "(123)"
 * 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 * 示例 2:
 * 输入: "(00011)"
 * 输出:  ["(0.001, 1)", "(0, 0.011)"]
 * 解释:
 * 0.0, 00, 0001 或 00.01 是不被允许的。
 * 示例 3:
 * 输入: "(0123)"
 * 输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
 * 示例 4:
 * 输入: "(100)"
 * 输出: [(10, 0)]
 * 解释:
 * 1.0 是不被允许的。
 *
 *
 * 提示:
 *
 * 4 <= S.length <= 12.
 * S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
 *
 * @author xuzhou
 * @since 2022/11/7 10:51
 */
public class AmbiguousCoordinatesTest {

    public List<String> ambiguousCoordinates(String s) {
        List<String> res = new ArrayList<>();
        char[] src = s.toCharArray();
        int N = src.length;
        char[] des = new char[N + 4];
        for (int i = 2; i < N - 1; i++) {
            // 逗号的位置是i
            if (isNotValidNumber(src, 1, i - 1) || isNotValidNumber(src, i, N - 2)) {
                continue;
            }
            List<Integer> pointIndexLeft = getPointIndex(src, 1, i - 1);
            List<Integer> pointIndexRight = getPointIndex(src, i, N - 2);
            List<String> ambiguousCoordinates = convertToAmbiguousCoordinates(src, des, i, pointIndexLeft, pointIndexRight);
            res.addAll(ambiguousCoordinates);
        }
        return res;
    }

    private boolean isNotValidNumber(char[] s, int l, int r) {
        return l < r && s[l] == '0' && s[r] == '0';
    }

    private List<Integer> getPointIndex(char[] s, int l, int r) {
        List<Integer> res = new ArrayList<>();
        if (l == r || s[r] == '0') {
            res.add(-1);
            return res;
        }
        if (s[l] == '0') {
            res.add(l + 1);
            return res;
        }
        res.add(-1);
        for (int i = l + 1; i <= r; i++) {
            res.add(i);
        }
        return res;
    }

    private List<String> convertToAmbiguousCoordinates(char[] src, char[] des, int commaIndex, List<Integer> pointIndexLefts, List<Integer> pointIndexRights) {
        List<String> res = new ArrayList<>();
        for (Integer pointIndexLeft : pointIndexLefts) {
            for (Integer pointIndexRight : pointIndexRights) {
                res.add(convertToAmbiguousCoordinate(src, des, commaIndex, pointIndexLeft, pointIndexRight));
            }
        }
        return res;
    }

    private String convertToAmbiguousCoordinate(char[] src, char[] des, int commaIndex, int pointIndexLeft, int pointIndexRight) {
        int N = src.length;
        int add = 2;
        if (pointIndexLeft > 0) {
            System.arraycopy(src, 0, des, 0, pointIndexLeft);
            des[pointIndexLeft] = '.';
            System.arraycopy(src, pointIndexLeft, des, pointIndexLeft + 1, commaIndex - pointIndexLeft);
            des[commaIndex + 1] = ',';
            des[commaIndex + 2] = ' ';
            add = 3;
        } else {
            System.arraycopy(src, 0, des, 0, commaIndex);
            des[commaIndex] = ',';
            des[commaIndex + 1] = ' ';
        }
        if (pointIndexRight > 0) {
            System.arraycopy(src, commaIndex, des, commaIndex + add, pointIndexRight - commaIndex);
            des[pointIndexRight + add] = '.';
            System.arraycopy(src, pointIndexRight, des, pointIndexRight + add + 1, N - pointIndexRight);
            add++;
        } else {
            System.arraycopy(src, commaIndex, des, commaIndex + add, N - commaIndex);
        }
        return new String(des, 0, N + add);
    }

    @Test
    public void test() {
        System.out.println("ambiguousCoordinates = " + ambiguousCoordinates("(123)"));
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            String s = RandomUtil.randomString(RandomUtil.BASE_NUMBER, RandomUtil.randomInt(2, 10));
            List<String> ambiguousCoordinates = ambiguousCoordinates("(" + s + ")");
            System.out.println("s = " + s + ", ambiguousCoordinates = " + ambiguousCoordinates);
        }
    }
}
