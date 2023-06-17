import java.util.Arrays;

/**
 * 基础位运算
 *
 * @author zhou.xu
 * @since 2023/6/1 22:38
 */
public class Binary01 {

    public static void main(String[] args) {
        int a = 69, b = 37;
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

    private static void printBits(int num) {
        String binaryString = Integer.toBinaryString(num);
        char[] cs = new char[32];
        Arrays.fill(cs, '0');
        System.arraycopy(binaryString.toCharArray(), 0, cs, 32 - binaryString.length(), binaryString.length());
        System.out.println(String.valueOf(cs) + " = " + num);
    }
}
