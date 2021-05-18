package easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * 查找常用字符
 * <p>给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * <p>例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>你可以按任意顺序返回答案。
 *
 * @author zhou.xu
 * @date 2020/10/14 10:45
 */
public class CommonChars {

    public List<String> commonChars(String[] A) {
        if (A == null || A.length == 0) {
            return Collections.emptyList();
        }
        int[] hashTable = null;
        boolean first = true;
        for (String s : A) {
            if (first) {
                hashTable = toHashTable(s);
                first = false;
            } else {
                int[] otherHashTable = toHashTable(s);
                for (int j = 0; j < 26; j++) {
                    if (otherHashTable[j] < hashTable[j]) {
                        hashTable[j] = otherHashTable[j];
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < hashTable[i]; j++) {
                res.add(String.valueOf(((char) ('a' + i))));
            }
        }
        return res;
    }

    private int[] toHashTable(String s) {
        int[] hash = new int[26];
        for (char c : s.toCharArray()) {
            hash[c - 'a']++;
        }
        return hash;
    }

    @Test
    public void test() {
        test("bella", "label", "roller");
        test("cool", "lock", "cook");
    }

    public void test(String... A) {
        List<String> list = commonChars(A);
        System.out.println("list = " + list);
    }
}
