package binary;

/**
 * <a href="https://leetcode.cn/problems/reverse-bits">190. 颠倒二进制位</a>
 * @author xuzhou
 * @since 2024/2/20 17:02
 */
public class ReverseBitsTest {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = result << 1;
            result = result | (n & 1);
            n = n >> 1;
        }
        return result;
    }
}
