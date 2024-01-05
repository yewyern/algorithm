package data_structure.graph;


import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/snakes-and-ladders/">909. 蛇梯棋</a>
 *
 * @author xuzhou
 * @since 2024/1/2 15:58
 */
public class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int m = n * n;
        int[] resolvedBoard = resolve(board);
        int[] steps = new int[m + 1];
        int[] queue = new int[m];
        queue[0] = 1;
        int size = 1;
        for (int i = 0; i < size; i++) {
            int curr = queue[i];
            int nextStep = steps[curr] + 1;
            for (int j = 1; j < 7; j++) {
                int next = curr + j;
                if (next > m) {
                    break;
                }
                if (resolvedBoard[next] > 0) {
                    next = resolvedBoard[next];
                }
                if (steps[next] > 0) {
                    continue;
                }
                queue[size++] = next;
                steps[next] = nextStep;
            }
            if (steps[m] > 0) {
                return steps[m];
            }
        }
        return -1;
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

    public int snakesAndLadders1(int[][] board) {
        int n = board.length;
        int size = n * n;
        int[] resolvedBoard = resolve(board);
        int[] steps = new int[size + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            int nextStep = steps[curr] + 1;
            for (int i = 1; i < 7; i++) {
                int next = curr + i;
                if (next > size) {
                    break;
                }
                if (resolvedBoard[next] > 0) {
                    next = resolvedBoard[next];
                }
                if (steps[next] > 0) {
                    continue;
                }
                queue.offer(next);
                steps[next] = nextStep;
            }
            if (steps[size] > 0) {
                return steps[size];
            }
        }
        return -1;
    }

    public int snakesAndLadders2(int[][] board) {
        int n = board.length;
        int size = n * n;
        int[] resolvedBoard = resolve(board);
        UniqueList positions = new UniqueList(size + 1);
        positions.add(1);
        int step = 0;
        int count = 1;
        int index = 0;
        for (Integer curr : positions) {
            for (int i = 1; i < 7; i++) {
                int next = curr + i;
                if (next > size) {
                    break;
                }
                if (resolvedBoard[next] > 0) {
                    next = resolvedBoard[next];
                }
                positions.add(next);
            }
            index++;
            if (index == count) {
                count = positions.size;
                step++;
                if (positions.contains(size)) {
                    return step;
                }
            }
        }
        return -1;
    }

    public int snakesAndLadders3(int[][] board) {
        int n = board.length;
        int size = n * n;
        int[] resolvedBoard = resolve(board);
        boolean[] visited = new boolean[size + 1];
        UniqueList currPositions = new UniqueList(size + 1);
        currPositions.add(1);
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
                        nextPositions.add(next);
                    } else if (resolvedBoard[next] != -1 && !visited[resolvedBoard[next]]) {
                        nextPositions.add(resolvedBoard[next]);
                    }
                }
            }
            currPositions = nextPositions;
            step++;
        }
        return visited[size] ? step : -1;
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

        public void add(int a) {
            if (!set[a]) {
                set[a] = true;
                values[size++] = a;
            }
        }

        public boolean contains(int a) {
            return set[a];
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
