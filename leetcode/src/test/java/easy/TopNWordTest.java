package easy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.junit.Test;

/**
 * @author zhou.xu
 * @since 2020/9/21 21:44
 */
public class TopNWordTest {

    public static List<String> topNWord2(String text, int n) {
        // 拆分字符串
        String[] words = text.split(" ");
        return topKFrequent(words, n);
    }

    public static List<String> topKFrequent(String[] words, int k) {
        // 计数
        Map<String, Long> map = Arrays.stream(words)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // 排序
        // 取前n个
        return map.entrySet().stream().sorted(comparingLongDesc(Entry::getValue))
            .map(Entry::getKey).limit(k).collect(Collectors.toList());
    }

    public static <T> Comparator<T> comparingLongDesc(@Nonnull ToLongFunction<? super T> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
            (c1, c2) -> Long.compare(keyExtractor.applyAsLong(c2), keyExtractor.applyAsLong(c1));
    }

    public static String[] topNWord(String text, int n) {
        char[] cs = text.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        ArrayList<StringCount> list = new ArrayList<>();
        StringBuilder sb;
        for (int i = 0; i < cs.length; ) {
            while (cs[i] == ' ') {
                i++;
            }
            sb = new StringBuilder();
            while (i < cs.length && cs[i] != ' ') {
                sb.append(cs[i++]);
            }
            String s = sb.toString();
            if (s.length() > 0) {
                Integer index = map.getOrDefault(s, -1);
                if (index == -1) {
                    // 不存在的时候，计数为1，加到最后
                    StringCount stringCount = new StringCount(s, 1);
                    list.add(stringCount);
                    map.put(s, list.size() - 1);
                } else {
                    // 存在的时候，计数+1
                    StringCount stringCount = list.get(index);
                    stringCount.count++;
                    if (index > 0) {
                        // 判断前一位与当前位置的计数，如果比前一位count大，并交换位置
                        boolean end = false;
                        while (!end && index > 0) {
                            StringCount stringCount2 = list.get(index - 1);
                            if (stringCount.count > stringCount2.count) {
                                // swap
                                list.set(index - 1, stringCount);
                                list.set(index, stringCount2);
                                map.put(s, index - 1);
                                map.put(stringCount2.key, index);
                                index--;
                            } else {
                                end = true;
                            }
                        }
                    }
                }
            }
        }
        String[] res = new String[Math.min(n, list.size())];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i).key;
        }
        return res;
    }

    private static class StringCount {

        String key;
        int count;

        public StringCount(String key, int count) {
            this.key = key;
            this.count = count;
        }
    }

    @Test
    public void test() {
        String text = "Hello world, java! Hello Xz! 123 123 456 456 456 789 789 145";
        System.out.println(Arrays.toString(topNWord(text, 3)));
        System.out.println(topNWord2(text, 3));
    }
}
