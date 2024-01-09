package greedy_algorithm;

import org.junit.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/ipo/">502. IPO</a>
 * @author xuzhou
 * @since 2024/1/8 17:27
 */
public class FindMaximizedCapitalTest {

    @Test
    public void test() {
        System.out.println(findMaximizedCapital(2, 0 , new int[]{1, 2, 3}, new int[]{0, 1, 1}));
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(profits[i], capital[i]);
        }
        Arrays.sort(nodes, Comparator.comparing(Node::getCapital));
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getProfit).reversed());
        int size = 0;
        while (k > 0) {
            while (size < n && nodes[size].capital <= w) {
                priorityQueue.add(nodes[size++]);
            }
            if (priorityQueue.isEmpty()) {
                return w;
            }
            Node node = priorityQueue.poll();
            w += node.profit;
            k--;
        }
        return w;
    }

    private class Node {
        int profit;
        int capital;

        public Node(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }

        public int getProfit() {
            return profit;
        }

        public int getCapital() {
            return capital;
        }

    }
}
