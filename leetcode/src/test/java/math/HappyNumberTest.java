package math;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/happy-number/">202. 快乐数</a>
 * @author zhou.xu
 * @since 2020/3/6 17:48
 */
public class HappyNumberTest {

    private static final boolean[] HELPER = {false, true, false, false, false, false, false, true, false, false, true, false, false, true, false, false, false, false, false, true, false, false, false, true, false, false, false, false, true, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false, false, true, false, false, true, false, false, false, true, false, false, false, false, true, false, false, true, false, false, true, false, false, true, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, true, true, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, true, true, false, false, false, false, false, true, false, false, true, false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, false, true, false, true, true, false, false, false, false, false, false, false, true, false, false, true, false, false, false, true, false, false, true, false, false, false, false, true, true, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, true, false, true, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, true, false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    
//    static {
//        for (int i = 1; i < 731; i++) {
//            HELPER[i] = process(i);
//        }
//        System.out.println(Arrays.toString(HELPER));
//    }

//    public static boolean process(int n) {
//        Set<Integer> set = new HashSet<>();
//        while (n != 1 && !set.contains(n)) {
//            set.add(n);
//            int sum = 0;
//            while (n > 0) {
//                int i = n % 10;
//                sum += i * i;
//                n /= 10;
//            }
//            n = sum;
//        }
//        return n == 1;
//    }

    public boolean isHappy(int n) {
        if (n < 731) {
            return HELPER[n];
        }
        int sum = 0;
        while (n > 0) {
            int i = n % 10;
            sum += i * i;
            n /= 10;
        }
        return HELPER[sum];
    }

    @Test
    public void test() {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
        System.out.println(isHappy(1999999999));
    }

}
