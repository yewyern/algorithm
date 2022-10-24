package datastruct;

import java.util.Arrays;

/**
 * @author zhou.xu
 * @since 2022/10/24 21:57
 */
public class BitMap {

    private final byte[] bits;
    private final int l;
    private int count = 0;

    public BitMap(int l) {
        if (l < 0) {
            throw new IllegalArgumentException();
        }
        this.l = l;
        this.bits = new byte[(l >> 3) + 1];
    }

    public void add(int a) {
        if (a < 0 || a > l) {
            throw new IllegalArgumentException();
        }
        int i = a >> 3;
        byte bit = bits[i];
        bits[i] = (byte) (bit | (1 << (a & 7)));
        count++;
    }

    public void remove(int a) {
        if (a < 0 || a > l) {
            throw new IllegalArgumentException();
        }
        int i = a >> 3;
        byte bit = bits[i];
        bits[i] = (byte) (bit & (~(1 << (a & 7))));
        count--;
    }

    public boolean exists(int a) {
        if (a < 0 || a > l) {
            throw new IllegalArgumentException();
        }
        int i = a >> 3;
        byte bit = bits[i];
        return (bit & (1 << (a & 7))) != 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(bits);
    }
}
