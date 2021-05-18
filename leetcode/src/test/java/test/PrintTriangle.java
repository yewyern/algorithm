package test;

import org.junit.Test;

/**
 * <p> 打印如下数字三角形
 * <p> n=5
 * <p> 1
 * <p> 2 12
 * <p> 3 13 11
 * <p> 4 14 15 10
 * <p> 5  6  7   8   9
 *
 * @author zhou.xu
 * @date 2020/10/12 17:03
 */
public class PrintTriangle {

    public void printTriangle(int level) {
        int[][] triangle = new int[level][level];
        int x = 0, y = 0, addX = 0, addY = 1;
        for (int i = 0; i < (level + 1) * level / 2; i++) {
            triangle[y][x] = i + 1;
            int tempX = x + addX;
            int tempY = y + addY;
            if (tempX >= 0 && tempX < level && tempY >= 0 && tempY < level && triangle[tempY][tempX] == 0) {
                x = tempX;
                y = tempY;
            } else {
                if (addX == 0) {
                    addX = 1;
                    addY = 0;
                } else if (addY == 0) {
                    addX = -1;
                    addY = -1;
                } else {
                    addX = 0;
                    addY = 1;
                }
                x = x + addX;
                y = y + addY;
            }
        }
        for (int i = 0; i < triangle.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void test() {
        printTriangle(3);
        printTriangle(4);
        printTriangle(5);
        printTriangle(6);
    }
}
