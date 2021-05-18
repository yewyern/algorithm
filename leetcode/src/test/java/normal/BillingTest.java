package normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * @author zhou.xu
 * @date 2020/9/9 16:58
 */
public class BillingTest {

    @Test
    public void test() {
        int[] counts = {8, 9, 9, 8, 10, 8, 8, 9, 9, 10, 9, 8};
        List<Billing> res = new ArrayList<>(counts.length / 2);
        int zeroCount = 0;
        while (zeroCount < counts.length) {
            zeroCount = 0;
            Billing billing = new Billing();
            System.out.println("res = " + res);
            for (int i = 0; i < counts.length; i++) {
                billing.end = i;
                if (0 == counts[i] || i == counts.length - 1) {
                    zeroCount++;
                    System.out.println(i + " : " + Arrays.toString(counts));
                    if (billing.count != 0 && billing.count != Integer.MAX_VALUE) {
                        res.add(billing);
                        for (int j = billing.start; j <= billing.end; j++) {
                            counts[j] -= billing.count;
                        }
                        System.out.println(i + " : " + Arrays.toString(counts));
                        billing = new Billing();
                        billing.start = i + 1;
                    } else {
                        continue;
                    }
                }
                if (billing.count > counts[i]) {
                    billing.count = counts[i];
                }
            }
        }
        System.out.println("res = " + res);
    }

    static class Billing {

        private int start = 0;
        private int end = 0;
        private int count = Integer.MAX_VALUE;

        @Override
        public String toString() {
            return "Billing{" +
                "start=" + start +
                ", end=" + end +
                ", count=" + count +
                '}';
        }
    }
}
