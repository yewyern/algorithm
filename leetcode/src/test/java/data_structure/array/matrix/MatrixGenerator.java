package data_structure.array.matrix;

import utils.RandomUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhou.xu
 * @since 2023/6/23 16:47
 */
public class MatrixGenerator {

    public static int[][] generateNoRepeatSortedMatrix(int rows, int cols, int min, int max) {
        int N = rows * cols;
        int[][] matrix = new int[rows][cols];
        NoRepeatNumberGenerator generator = new NoRepeatNumberGenerator(min, max);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 先生成无序的矩阵，保证不重复
                matrix[i][j] = generator.nextInt();
            }
            // 对行排序
            Arrays.sort(matrix[i]);
        }
        // 对列排序
        for (int i = 0; i < cols; i++) {
            int[] col = new int[rows];
            for (int j = 0; j < rows; j++) {
                col[j] = matrix[j][i];
            }
            Arrays.sort(col);
            for (int j = 0; j < rows; j++) {
                matrix[j][i] = col[j];
            }
        }
        return matrix;
    }

    private static class NoRepeatNumberGenerator {
        private int min;
        private int max;
        private boolean[] arraySet;
        private Set<Integer> hashSet;

        private NoRepeatNumberGenerator(int min, int max) {
            this.min = min;
            this.max = max;
            if (max - min <= 1024) {
                arraySet = new boolean[max - min];
            } else {
                hashSet = new HashSet<>();
            }
        }

        private boolean contains(int num) {
            if (arraySet != null) {
                return arraySet[num - min];
            }
            return hashSet.contains(num);
        }

        public int nextInt() {
            int res = RandomUtils.nextInt(min, max);
            while (contains(res)) {
                res = RandomUtils.nextInt(min, max);
            }
            return res;
        }
    }

}
