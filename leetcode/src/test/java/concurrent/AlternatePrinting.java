package concurrent;

import java.util.concurrent.locks.LockSupport;
import org.junit.Test;

/**
 * 交替打印1A2B3C
 *
 * @author zhou.xu
 * @date 2020/9/25 9:41
 */
public class AlternatePrinting {

    private final char[] cs1 = "123456".toCharArray();
    private final char[] cs2 = "ABCDEF".toCharArray();
    static Thread t1, t2 = null;

    @Test
    public void testLockSupport() {
        t1 = new Thread(() -> {
            for (char c : cs1) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t2 = new Thread(() -> {
            for (char c : cs2) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static final Object o = new Object();

    @Test
    public void testWaitNotify() {
        t1 = new Thread(() -> {
            for (char c : cs1) {
                synchronized (o) {
                    System.out.print(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    o.notify();
                }
            }
        });
        t1.start();
        t2 = new Thread(() -> {
            for (char c : cs2) {
                synchronized (o) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    o.notify();
                }
            }
        });
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
