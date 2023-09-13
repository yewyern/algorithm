package greedy_algorithm;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/eliminate-maximum-number-of-monsters">1921. 消灭怪物的最大数量</a>
 * @author xuzhou
 * @since 2023/9/13 16:59
 */
public class EliminateMaximumTest {

    @Test
    public void test() {
        for (int i = 0; i < 100000; i++) {
            int n = RandomUtils.nextInt(100);
            int[] dist = RandomArray.generate(n, 1, 5000);
            int[] speed = RandomArray.generate(n, 1, 5000);
            int res1 = eliminateMaximum(dist, speed);
            int res2 = eliminateMaximum2(dist, speed);
            if (res1 != res2) {
                System.out.println("错误！");
                break;
            }
        }
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        // 根据最晚消灭时间计数排序
        int n = dist.length;
        int[] eliminateTime = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // 计算最晚消灭时间
            eliminateTime[latestEliminateTime(dist[i], speed[i], n)]++;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += eliminateTime[i];
            if (count > i + 1) {
                return i + 1;
            }
        }
        return n;
    }

    private int latestEliminateTime(int dist, int speed, int n) {
        int i = dist / speed;
        return i >= n ? n : (dist % speed == 0 ? i - 1 : i);
    }

    public int eliminateMaximum1(int[] dist, int[] speed) {
        // 根据到达时间快排
        int n = dist.length;
        float[] arriveTimes = new float[n];
        for (int i = 0; i < n; i++) {
            arriveTimes[i] = (float) dist[i] / speed[i];
        }
        Arrays.sort(arriveTimes);
        for (int i = 0; i < n; i++) {
            if (arriveTimes[i] <= i) {
                return i;
            }
        }
        return n;
    }

    public int eliminateMaximum2(int[] dist, int[] speed) {
        // 根据到达时间进行堆排序
        int n = dist.length;
        Heap heap = new Heap(dist, speed);
        for (int i = 0; i < n; i++) {
            int[] poll = heap.poll();
            if (poll[0] <= i * poll[1]) {
                return i;
            }
        }
        return n;
    }

    private static class Heap {
        int[] dist;
        int[] speed;
        int size;

        public Heap(int[] dist, int[] speed) {
            this.dist = dist;
            this.speed = speed;
            this.size = dist.length;
            heapify();
        }

        public int[] poll() {
            int[] res = new int[] {dist[0], speed[0]};
            size--;
            if (size > 0) {
                swap(0, size);
                siftDown(0);
            }
            return res;
        }

        private void heapify() {
            for (int i = (size >> 1) - 1; i >= 0; i--) {
                siftDown(i);
            }
        }

        private void siftDown(int i) {
            while (i < size) {
                int min = min(i, (i << 1) + 1, (i << 1) + 2);
                if (min == i) {
                    break;
                }
                swap(i, min);
                i = min;
            }
        }

        private int min(int i, int j, int k) {
            return min(i, min(j, k));
        }

        private int min(int i, int j) {
            if (i < size && j < size) {
                return compare(i, j) <= 0 ? i : j;
            }
            return i < size ? i : j;
        }

        private int compare(int i, int j) {
            return dist[i] * speed[j] - dist[j] * speed[i];
        }

        private void swap(int i, int j) {
            swap(dist, i, j);
            swap(speed, i, j);
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
