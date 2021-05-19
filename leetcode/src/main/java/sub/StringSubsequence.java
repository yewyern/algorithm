package sub;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串的子序列
 * abc: [abc,ab,ac,a,bc,b,c]
 * @author xuzhou
 * @date 2021/3/23 15:23
 */
public class StringSubsequence {

    public static void main(String[] args) {
        List<String> abc = getSubsequence("abc");
        System.out.println("abc = " + abc);
    }

    public static List<String> getSubsequence(String str) {
        List<String> ans = new ArrayList<>();
        process(str.toCharArray(), ans, 0, "");
        return ans;
    }

    public static void process(char[] str, List<String> ans, int index, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }

        process(str, ans, index + 1, path);
        String include = path + str[index];
        process(str, ans, index + 1, include);
    }
}
