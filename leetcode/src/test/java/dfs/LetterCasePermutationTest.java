package dfs;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/letter-case-permutation/">784. 字母大小写全排列</a>
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * <p>
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 示例 2:
 * <p>
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length <= 12
 * s 由小写英文字母、大写英文字母和数字组成
 *
 * @author zhou.xu
 * @since 2022/10/30 15:26
 */
public class LetterCasePermutationTest {

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        res.add(s);
        char[] cs = s.toCharArray();
        List<int[]> letterIndexes = new ArrayList<>();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] >= 'a' && cs[i] <= 'z') {
                letterIndexes.add(new int[] {i, 1});
            } else if (cs[i] >= 'A' && cs[i] <= 'Z') {
                letterIndexes.add(new int[] {i, 0});
            }
        }
        dfs(res, cs, 0, letterIndexes);
        return res;
    }

    public void dfs(List<String> res, char[] cs, int index, List<int[]> letterIndexes) {
        // a-z 0x41-0x5A
        // A-Z 0x61-0x7A
        for (int i = index; i < letterIndexes.size(); i++) {
            int[] letterIndex = letterIndexes.get(i);
            if (letterIndex[1] == 1) {
                cs[letterIndex[0]] -= 0x20;
                res.add(String.valueOf(cs));
                dfs(res, cs, i + 1, letterIndexes);
                cs[letterIndex[0]] += 0x20;
            } else if (letterIndex[1] == 0) {
                cs[letterIndex[0]] += 0x20;
                res.add(String.valueOf(cs));
                dfs(res, cs, i + 1, letterIndexes);
                cs[letterIndex[0]] -= 0x20;
            }
        }
    }

    public void test(String s) {
        List<String> list = letterCasePermutation(s);
        System.out.println("list = " + list);
    }

    @Test
    public void test() {
        test("a1b2");
        test("3z4");
        test("AAAAA");
    }
}
