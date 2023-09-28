package data_structure.hash;

import org.junit.Test;
import utils.GenerateRandomString;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof">剑指 Offer 50. 第一个只出现一次的字符</a>
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * <p>
 * 示例 1:
 * <p>
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:
 * <p>
 * 输入：s = ""
 * 输出：' '
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= s 的长度 <= 50000
 *
 * @author zhou.xu
 * @since 2023/6/17 18:33
 */
public class FirstUniqCharTest {

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            String s = GenerateRandomString.generateRandomLenString(50000);
            s = s.toLowerCase();
            char c = firstUniqChar(s);
            char c1 = firstUniqCharComparison(s);
            if (c != c1) {
                System.out.println("s = " + s);
                System.out.println("c = " + c);
                System.out.println("c1 = " + c1);
                firstUniqChar(s);
                break;
            }
        }
    }

    public char firstUniqChar(String s) {
        // 看似使用indexOf逐个字符遍历，多扫描了，但是速度是最快的，因为是调用的c，而不是字节码
        char res = ' ';
        int min = Integer.MAX_VALUE;
        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            if (first == -1 || first > min) {
                continue;
            }
            if (s.lastIndexOf(c) == first) {
                min = first;
                res = c;
            }
        }
        return res;
    }

    public char firstUniqCharComparison(String s) {
        int[] count = new int[26];
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        int able = Integer.MAX_VALUE >> 5;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            count[i]++;
            if (count[i] == 1) {
                set.add(c);
            } else {
                set.remove(c);
                able = able ^ (1 << i);
            }
            if (able == 0) {
                return ' ';
            }
        }
        return set.isEmpty() ? ' ' : set.iterator().next();
    }

    public char firstUniqCharComparison2(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>(32);
        int able = 26;
        for (char c : s.toCharArray()) {
            Integer cnt = map.getOrDefault(c, 0);
            if (cnt == 0) {
                map.put(c, 1);
            } else if (cnt == 1) {
                able--;
                map.put(c, 2);
            }
            // 提早跳出循环
            if (able == 0) {
                return ' ';
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }

}
