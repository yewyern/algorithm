package math;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/gray-code/">89. 格雷编码</a>
 *
 * @author xuzhou
 * @since 2023/10/9 18:27
 */
public class GrayCodeTest {

    @Test
    public void test() {
        for (int i = 1; i < 17; i++) {
            System.out.println(grayCode(i));
        }
    }

    private static final int[] CACHE = new int[1 << 16];
    private static int curr = 1;

    static {
        CACHE[1] = 1;
    }

    public List<Integer> grayCode(int n) {
        while (n > curr) {
            int add = 1 << curr;
            int i = add, j = add - 1;
            while (j >= 0) {
                CACHE[i++] = CACHE[j--] + add;
            }
            curr++;
        }
        int size = 1 << n;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ans.add(CACHE[i]);
        }
        return ans;
    }

}
