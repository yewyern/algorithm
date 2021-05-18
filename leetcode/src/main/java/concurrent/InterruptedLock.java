package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhou.xu
 * @date 2020/2/28 17:55
 */
public class InterruptedLock {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("lock.isLocked() by t1 = " + lock.isLocked());
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println("interrupt by t2");
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                boolean tryLock = lock.tryLock(5, TimeUnit.SECONDS);
                System.out.println("tryLock = " + tryLock);
                if (!tryLock) {
                    t1.interrupt();
                }
                lock.tryLock(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    System.out.println("locked by t2");
                    lock.unlock();
                } else {
                    System.out.println("locked by t1");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
