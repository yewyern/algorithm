package data_structure.array.matrix;

import utils.NoRepeatNumberGenerator;

import java.util.Arrays;

/**
 * @author zhou.xu
 * @since 2023/6/23 16:47
 */
public class MatrixGenerator {

    public static int[][] generateNoRepeatSortedMatrix(int rows, int cols, int min, int max) {
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

}
