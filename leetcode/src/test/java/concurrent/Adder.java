package concurrent;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import utils.TestUtils;

/**
 * @author zhou.xu
 * @since 2020/2/28 16:55
 */
public class Adder {

    private static long count1 = 0;
    private static final AtomicLong count2 = new AtomicLong(0);
    private static final LongAdder count3 = new LongAdder();
    private static final Object lock = new Object();
    private static final Long TARGET = 1000000L;
    private static final int THREAD_NUM = 1000;

    private static final Runnable runnable1 = () -> {
        for (int i = 0; i < TARGET; i++) {
            synchronized (lock) {
                count1++;
            }
        }
    };
    private static final Runnable runnable2 = () -> {
        for (int i = 0; i < TARGET; i++) {
            count2.incrementAndGet();
        }
    };
    private static final Runnable runnable3 = () -> {
        for (int i = 0; i < TARGET; i++) {
            count3.increment();
        }
    };

    private static int run(Runnable runnable) {
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        TestUtils.test(() -> run(runnable1), 1);
        TestUtils.test(() -> run(runnable2), 2);
        TestUtils.test(() -> run(runnable3), 3);
    }
}
