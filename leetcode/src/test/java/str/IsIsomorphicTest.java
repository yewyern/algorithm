package str;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/isomorphic-strings">205. 同构字符串</a>
 *
 * @author xuzhou
 * @since 2024/2/19 14:08
 */
public class IsIsomorphicTest {

    public boolean isIsomorphic(String s, String t) {
        int[] preIndexS = new int[128];
        int[] preIndexT = new int[128];
        Arrays.fill(preIndexS, -1);
        Arrays.fill(preIndexT, -1);
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int n = str.length;
        for (int i = 0; i < n; i++) {
            if (preIndexS[str[i]] != preIndexT[target[i]]) {
                return false;
            } else {
                preIndexS[str[i]] = i;
                preIndexT[target[i]] = i;
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int n = str.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(str[i]) ? map.get(str[i]) != target[i] : map.containsValue(target[i])) {
                return false;
            } else {
                map.put(str[i], target[i]);
            }
        }
        return true;
    }

    public boolean isIsomorphic1(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
