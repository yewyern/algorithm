package data_structure.array.matrix;

import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * @author zhou.xu
 * @since 2023/6/23 16:47
 */
public class MatrixGeneratorTest {

    public static int[][] generateNoRepeatSortedMatrix2(int w, int h, int min, int max) {
        int N = w * h;
        int[][] matrix = new int[h][w];
        int[] nums = RandomArray.generateNoRepeatSortedArray(N, min, max);
        int[] filled = new int[h];// 记录已经填过数字的个数
        int count = 0; // 已填过的数字个数
        int needFillRow = 0; // 需要填充的起始行
        while (count < N) {
            for (int i = needFillRow; i < h; i++) {
                // 每一行随机填充，下一行能填充的个数<=上一行已填充的个数
                int filledWidth = filled[i] == w - 1 ? w : RandomUtils.nextInt(filled[i], i == 0 ? w + 1 : filled[i - 1] + 1);
                if (filledWidth == filled[i]) {
                    break;
                }
                System.arraycopy(nums, count, matrix[i], filled[i], filledWidth - filled[i]);
                count += filledWidth - filled[i];
                filled[i] = filledWidth;
                if (filled[i] == w) {
                    needFillRow = i + 1;
                }
            }
        }
        return matrix;
    }

    @Test
    public void test() {
        int rows = 100, cols = 100, min = -100000, max = 100000, tests = 100000;
        System.out.println("开始测试生成方法1");
        long start = System.currentTimeMillis();
        for (int i = 0; i < tests; i++) {
            MatrixGenerator.generateNoRepeatSortedMatrix(rows, cols, min, max);
        }
        long end = System.currentTimeMillis();
        System.out.println("结束测试生成方法1，用时：" + (end - start));
        System.out.println("开始测试生成方法2");
        start = System.currentTimeMillis();
        for (int i = 0; i < tests; i++) {
            generateNoRepeatSortedMatrix2(rows, cols, min, max);
        }
        end = System.currentTimeMillis();
        System.out.println("结束测试生成方法2，用时：" + (end - start));
    }
}
