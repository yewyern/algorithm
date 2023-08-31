package easy;

import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/top-k-frequent-words">692. 前K个高频单词</a>
 * <p>
 * 给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。
 * <p>
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 * 示例 2：
 * <p>
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * 出现次数依次为 4, 3, 2 和 1 次。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 1 <= words.length <= 500
 * 1 <= words[i] <= 10
 * words[i] 由小写英文字母组成。
 * k 的取值范围是 [1, 不同 words[i] 的数量]
 * <p>
 * <p>
 * 进阶：尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 *
 * @author zhou.xu
 * @since 2020/9/21 21:44
 */
public class TopNWordTest {

    public List<String> topNWord2(String text, int n) {
        // 拆分字符串
        String[] words = text.split(" ");
        return topKFrequent2(words, n);
    }

    public List<String> topKFrequent(String[] words, int k) {
        int n = words.length;
        // 计数
        Map<String, StringCount> map = new HashMap<>(n);
        for (String word : words) {
            map.put(word, map.getOrDefault(word, new StringCount(word, 0)).incr());
        }
        StringCount[] array = map.values().toArray(new StringCount[0]);
        List<StringCount> list = Arrays.asList(Arrays.copyOfRange(array, 0, k));
        // 排序
        // 取前n个
        // 建前k个小顶堆
        PriorityQueue<StringCount> queue = new PriorityQueue<>(list);
        for (int i = k; i < array.length; i++) {
            if (queue.peek().compareTo(array[i]) < 0) {
                queue.poll();
                queue.offer(array[i]);
            }
        }
        String[] ans = new String[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = queue.poll().key;
        }
        return Arrays.asList(ans);
    }


    private static class StringCount implements Comparable<StringCount> {

        String key;
        int count;

        public StringCount(String key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(StringCount other) {
            if (other.count != count) {
                return count - other.count;
            }
            return other.key.compareTo(key);
        }

        private StringCount incr() {
            count++;
            return this;
        }
    }

    public List<String> topKFrequent0(String[] words, int k) {
        int n = words.length;
        // 计数
        Map<String, StringCount2> map = new HashMap<>(n);
        for (String word : words) {
            map.put(word, map.getOrDefault(word, new StringCount2(word, 0)).incr());
        }
        // 排序
        // 取前n个
        // 目前是nlog(n)的算法
        PriorityQueue<StringCount2> queue = new PriorityQueue<>(map.values());
        List<String> ans = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            ans.add(queue.poll().key);
        }
        return ans;
    }

    public List<String> topKFrequent1(String[] words, int k) {
        // 计数
        Map<String, Long> map = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // 排序
        // 取前n个
        PriorityQueue<StringCount2> queue = map.entrySet().stream().map(entry -> new StringCount2(entry.getKey(), Math.toIntExact(entry.getValue()))).collect(Collectors.toCollection(PriorityQueue::new));
        List<String> ans = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            ans.add(queue.poll().key);
        }
        return ans;
    }

    public List<String> topKFrequent2(String[] words, int k) {
        // 计数
        Map<String, Long> map = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // 排序
        // 取前n个
        return map.entrySet().stream().sorted((c1, c2) -> {
            int compare = Long.compare(c2.getValue(), c1.getValue());
            return compare == 0 ? c1.getKey().compareTo(c2.getKey()) : compare;
        }).map(Entry::getKey).limit(k).collect(Collectors.toList());
    }

    public String[] topNWord(String text, int n) {
        char[] cs = text.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        ArrayList<StringCount2> list = new ArrayList<>();
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
                    StringCount2 stringCount2 = new StringCount2(s, 1);
                    list.add(stringCount2);
                    map.put(s, list.size() - 1);
                } else {
                    // 存在的时候，计数+1
                    StringCount2 stringCount = list.get(index);
                    stringCount.count++;
                    if (index > 0) {
                        // 判断前一位与当前位置的计数，如果比前一位count大，并交换位置
                        boolean end = false;
                        while (!end && index > 0) {
                            StringCount2 stringCount2 = list.get(index - 1);
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

    private static class StringCount2 implements Comparable<StringCount2> {

        String key;
        int count;

        public StringCount2(String key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(StringCount2 other) {
            if (other.count != count) {
                return other.count - count;
            }
            return key.compareTo(other.key);
        }

        private StringCount2 incr() {
            count++;
            return this;
        }
    }

    @Test
    public void test() {
        String text = "Hello world, java! Hello Xz! 123 123 456 456 456 789 789 145";
        System.out.println(Arrays.toString(topNWord(text, 3)));
        System.out.println(topNWord2(text, 3));
    }
}
