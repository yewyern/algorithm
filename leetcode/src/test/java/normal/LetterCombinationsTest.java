package normal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * @author zhou.xu
 * @since 2020/9/22 16:52
 */
public class LetterCombinationsTest {

    private static final String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private StringBuilder sb;
    private List<String> res;

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        res = new ArrayList<>(digits.length() * 3);
        sb = new StringBuilder();
        char[] cs = digits.toCharArray();
        backTrack(cs, 0);
        return res;
    }

    private void backTrack(char[] cs, int i) {
        if (i >= cs.length) {
            if (sb.length() > 0) {
                res.add(sb.toString());
            }
            return;
        }
        String s = map[cs[i] - '0'];
        for (char c1 : s.toCharArray()) {
            sb.append(c1);
            backTrack(cs, i + 1);
            sb.deleteCharAt(i);
        }
    }

    public void test(String digits) {
        List<String> res = letterCombinations(digits);
        System.out.println("res = " + res);
    }

    @Test
    public void test() {
        test("234");
        test("562");
    }
}
