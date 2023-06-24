package class01binary;

import static utils.BinaryUtils.printBits;

/**
 * @author zhou.xu
 * @since 2023/6/24 10:56
 */
public class Binary03 {

    public static void main(String[] args) {
        // 提取一个数的最右边一个1
        int a = 40;
        printBits(a);
        int b = a & (-a);
        printBits(b);
        // 找到a最右边的1的位置
        int c = b - 1;
        printBits(c);
        System.out.println(Integer.bitCount(c));
        while (a != 0) {
            int t = a & (-a);
            int k = Integer.bitCount(t - 1);
            printBits(t);
            System.out.println(k);
            a ^= t;
        }
    }
}
