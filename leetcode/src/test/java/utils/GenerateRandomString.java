package utils;

import java.util.Random;

/**
 * @author xuzhou
 * @since 2021/4/27 16:19
 */
public class GenerateRandomString {

    private static final Random random = new Random(System.currentTimeMillis());
    public static final int MAX_STRING_LEN = 1000;
    public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        .toCharArray();

    public static void main(String[] args) {
        int testCase = 100000;
        long start = System.nanoTime();
        for (int i = 0; i < testCase; i++) {
            generateRandomString(i);
        }
        System.out.println("generateRandomString  = " + (System.nanoTime() - start) / testCase);
        start = System.nanoTime();
        for (int i = 0; i < testCase; i++) {
            generateRandomString2(i);
        }
        System.out.println("generateRandomString2 = " + (System.nanoTime() - start) / testCase);
        start = System.nanoTime();
        for (int i = 0; i < testCase; i++) {
            generateRandomString3(i);
        }
        System.out.println("generateRandomString2 = " + (System.nanoTime() - start) / testCase);
    }

    public static String generateRandomLenString(int maxLen) {
        int len = random.nextInt(maxLen);
        return generateRandomString(len);
    }

    public static String generateRandomLenStringNoEmpty(int maxLen) {
        if (maxLen < 1) {
            throw new IllegalArgumentException("最大长度不能小于1");
        }
        int len = maxLen == 1 ? 1 : random.nextInt(maxLen - 1) + 1;
        return generateRandomString(len);
    }

    public static String generateRandomString() {
        int len = random.nextInt(MAX_STRING_LEN);
        return generateRandomString(len);
    }

    public static String generateRandomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            int offset = random.nextInt(52);
            str[i] = CHARS[offset];
        }
        return new String(str);
    }

    public static String generateRandomString2(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            boolean upper = random.nextBoolean();
            int offset = random.nextInt(26);
            str[i] = (char) (upper ? 'A' + offset : 'a' + offset);
        }
        return String.valueOf(str);
    }

    public static String generateRandomString3(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int offset = random.nextInt(52);
            sb.append(CHARS[offset]);
        }
        return sb.toString();
    }
}
