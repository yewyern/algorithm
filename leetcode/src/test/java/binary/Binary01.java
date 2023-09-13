package binary;

import static utils.BinaryUtils.printBits;

/**
 * 基础位运算
 *
 * @author zhou.xu
 * @since 2023/6/1 22:38
 */
public class Binary01 {

    public static void main(String[] args) {
        int a = 35, b = 44;
        printBits(a);
        printBits(b);
        System.out.println("---------------------");
        printBits(a & b);
        printBits(a | b);
        printBits(a ^ b);
        System.out.println("---------------------");
        printBits(a << 3);
        printBits(b >> 3);
        System.out.println("---------------------");
        printBits(b);
        printBits(~b);
        System.out.println("---------------------");
        int c = -4;
        printBits(c);
        for (int i = 0; i < 10; i++) {
            printBits(c >> i);
            printBits(c >>> i);
        }
        System.out.println("---------------------");
        printBits(Integer.MAX_VALUE);
        printBits(Integer.MIN_VALUE);
        printBits(Integer.MAX_VALUE >> 5);
    }

}
