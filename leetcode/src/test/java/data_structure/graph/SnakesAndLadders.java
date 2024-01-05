package data_structure.graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 * <a href="https://leetcode.cn/problems/snakes-and-ladders/">909. 蛇梯棋</a>
 *
 * @author xuzhou
 * @since 2024/1/2 15:58
 */
public class SnakesAndLadders {

    @Test
    public void test() {
        System.out.println(snakesAndLadders(new int[][]{{-1,-1,-1,-1,48,5,-1},{12,29,13,9,-1,2,32},{-1,-1,21,7,-1,12,49},{42,37,21,40,-1,22,12},{42,-1,2,-1,-1,-1,6},{39,-1,35,-1,-1,39,-1},{-1,36,-1,-1,-1,-1,5}}));
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int size = n * n;
        int[] resolvedBoard = resolve(board);
        boolean[] visited = new boolean[size + 1];
        UniqueList currPositions = new UniqueList(size + 1);
        currPositions.put(1);
        int step = 0;
        while (!currPositions.isEmpty()) {
            for (Integer curr : currPositions) {
                visited[curr] = true;
            }
            if (visited[size]) {
                break;
            }
            UniqueList nextPositions = new UniqueList(size + 1);
            for (Integer curr : currPositions) {
                for (int i = 1; i < 7; i++) {
                    int next = curr + i;
                    if (next > size) {
                        break;
                    }
                    if (resolvedBoard[next] == -1 && !visited[next]) {
                        nextPositions.put(next);
                    } else if (resolvedBoard[next] != -1 && !visited[resolvedBoard[next]]) {
                        nextPositions.put(resolvedBoard[next]);
                    }
                }
            }
            currPositions = nextPositions;
            step++;
        }
        return visited[size] ? step : -1;
    }

    private int[] resolve(int[][] board) {
        int n = board.length;
        int[] resolvedBoard = new int[n * n + 1];
        boolean forward = true;
        int curr = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (forward) {
                for (int j = 0; j < n; j++) {
                    resolvedBoard[curr++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    resolvedBoard[curr++] = board[i][j];
                }
            }
            forward = !forward;
        }
        return resolvedBoard;
    }

    private static class UniqueList implements Iterable<Integer> {

        boolean[] set;
        int[] values;
        int size;

        public UniqueList(int capacity) {
            set = new boolean[capacity];
            values = new int[capacity];
            size = 0;
        }

        public void put(int a) {
            if (!set[a]) {
                set[a] = true;
                values[size++] = a;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iter();
        }

        private class Iter implements Iterator<Integer> {
            private int index;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Integer next() {
                return values[index++];
            }
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", UniqueList.class.getSimpleName() + "[", "]")
                    .add("values=" + Arrays.toString(values))
                    .toString();
        }
    }
}
