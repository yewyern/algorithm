package search;

import org.junit.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/successful-pairs-of-spells-and-potions">2300. 咒语和药水的成功对数</a>
 *
 * @author xuzhou
 * @since 2023/11/13 17:25
 */
public class SuccessfulPairsOfSpellsAndPotionsTest {

    @Test
    public void test() {
        System.out.println(Arrays.toString(successfulPairs(new int[]{5, 1, 3}, new int[]{1, 2, 3, 4, 5}, 7)));
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = count(potions, spells[i], success);
        }
        return res;
    }

    private int count(int[] potions, long spell, long success) {
        int n = potions.length;
        int l = 0, r = n - 1;
        int m = 0;
        long min = success / spell;
        if (min * spell < success) {
            min++;
        }
        boolean big = true;
        while (r >= l) {
            m = (l + r) >> 1;
            if (potions[m] >= min) {
                r = m - 1;
                big = true;
            } else {
                l = m + 1;
                big = false;
            }
        }
        return big ? n - m : n - m - 1;
    }

    public int[] successfulPairs2(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, spells[i]);
        }
        Arrays.sort(nodes);
        int[] res = new int[n];
        res[nodes[0].index] = count(potions, nodes[0].val, success);
        for (int i = 1; i < n; i++) {
            if (nodes[i].val == nodes[i - 1].val) {
                res[nodes[i].index] = res[nodes[i - 1].index];
            } else if (res[nodes[i - 1].index] == 0) {
                res[nodes[i].index] = res[nodes[i - 1].index];
            } else {
                res[nodes[i].index] = count(potions, nodes[i].val, success);
            }
        }
        return res;
    }

    private static class Node implements Comparable<Node> {
        int index;
        int val;

        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return o.val - val;
        }
    }

}
