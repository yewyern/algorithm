package greedy_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import org.junit.Test;

/**
 * <p>假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，
 * <p>其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。
 * <p>编写一个算法来重建这个队列。
 * <p>
 * <p>注意：
 * <p>总人数少于1100人。
 * <p>
 * <p>示例
 * <p>
 * <p>输入:
 * <p>[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>[[4,4], [5,0], [5,2], [6,1], [7,0], [7,1]]
 * <p>[[5,0], [7,0], [6,1], [5,2], [4,4], [7,1]]
 * <p>输出:
 * <p>[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * @author zhou.xu
 * @date 2020/11/16 17:20
 */
public class ReconstructQueue {

    public int[][] reconstructQueue(int[][] people) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });
        queue.addAll(Arrays.asList(people));
        List<int[]> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            list.add(list.size() - poll[1], poll);
        }
        int[][] res = new int[people.length][];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(res.length - i - 1);
        }
        return res;
    }

    @Test
    public void test() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] res = reconstructQueue(people);
        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }
}
