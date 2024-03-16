package data_structure.graph;


import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/minimum-genetic-mutation/">433. 最小基因变化</a>
 * @author xuzhou
 * @since 2024/1/5 14:32
 */
public class MinMutation {

    public int minMutation(String startGene, String endGene, String[] bank) {
        if (endGene.equals(startGene)) {
            return 0;
        }
        int n = bank.length;
        boolean[] visited = new boolean[n];
        Queue<String> startList = new LinkedList<>();
        startList.add(startGene);
        int step = 1;
        while (!startList.isEmpty()) {
            Queue<String> nextStartList = new LinkedList<>();
            while (!startList.isEmpty()) {
                String start = startList.poll();
                for (int i = 0; i < n; i++) {
                    if (visited[i]) {
                        continue;
                    }
                    if (onlyOneDiff(start, bank[i])) {
                        nextStartList.add(bank[i]);
                        visited[i] = true;
                        if (bank[i].equals(endGene)) {
                            return step;
                        }
                    }
                }
            }
            startList = nextStartList;
            step++;
        }
        return -1;
    }

    private boolean onlyOneDiff(String a, String b) {
        int d = 0;
        for (int i = 0; i < 8; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                d++;
                if (d > 1) {
                    return false;
                }
            }
        }
        return d == 1;
    }

}
