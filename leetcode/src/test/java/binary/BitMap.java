package binary;

import utils.RandomUtils;

import java.util.Iterator;

/**
 * @author xuzhou
 * @since 2023/9/15 15:21
 */
public class BitMap implements Iterable<Integer> {
    final long[] bits;
    int start = -1;
    int size = 0;

    public BitMap(int capacity) {
        int n = (capacity >> 6) + 1;
        this.bits = new long[n];
    }

    public void add(int i) {
        int p = i >> 6;
        long m = 1L << (i % 64);
        if ((bits[p] & m) == 0) {
            bits[p] |= m;
            size++;
        }
        start = start < 0 ? p : Math.min(p, start);
    }

    public boolean contains(int i) {
        int p = i >> 6;
        long m = 1L << (i % 64);
        return (bits[p] & m) != 0;
    }

    public int poll() {
        long a = bits[start];
        long lastOne = a & (-a);
        bits[start] ^= lastOne;
        int ans = (start << 6) + Long.bitCount(lastOne - 1);
        size--;
        if (size > 0) {
            while (bits[start] == 0) {
                start++;
            }
        }
        return ans;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return size > 0;
            }

            @Override
            public Integer next() {
                return poll();
            }
        };
    }

    public static void main(String[] args) {
        BitMap set = new BitMap(1000);
        int max = 0;
        for (int i = 0; i < 1000; i++) {
            int a = RandomUtils.nextInt(1000);
            max = Math.max(a, max);
            set.add(a);
        }
        for (Integer i : set) {
            System.out.println(i);
        }
        System.out.println("max = " + max);
    }
}