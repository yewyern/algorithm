package utils;

import java.util.Arrays;

/**
 * @author zhou.xu
 * @since 2023/6/24 10:58
 */
public class BinaryUtils {

    public static void printBits(int num) {
        String binaryString = Integer.toBinaryString(num);
        char[] cs = new char[32];
        Arrays.fill(cs, '0');
        System.arraycopy(binaryString.toCharArray(), 0, cs, 32 - binaryString.length(), binaryString.length());
        System.out.println(String.valueOf(cs) + " = " + num);
    }
}
