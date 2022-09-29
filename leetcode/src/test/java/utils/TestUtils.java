package utils;

import java.util.function.Supplier;

/**
 * @author zhou.xu
 * @since 2020/2/24 19:49
 */
public class TestUtils {

    public static <T> void test(Supplier<T> supplier, int testCase) {
        long l = System.currentTimeMillis();
        T res = supplier.get();
        long l1 = System.currentTimeMillis();
        System.out.println("test case = " + testCase + ", res = " + res + ", time spent = " + (l1 - l));
    }

    public static void testIntArray(Supplier<int[]> supplier, int testCase) {
        long l = System.currentTimeMillis();
        int[] res = supplier.get();
        long l1 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("test case = " + testCase + ", res = [");
        for (int i = 0; i < res.length; i++) {
            sb.append(res[i] + ", ");
        }

        sb.append("], time spent = " + (l1 - l));
        System.out.println(sb.toString());
    }

}
