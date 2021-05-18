package concurrent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhou.xu
 * @date 2020/2/28 22:47
 */
public class MyBlockingQueue2<T> {

    private LinkedList<T> list = new LinkedList<T>();
    private int len = 0;
    private int max;
    private ReentrantLock lock = new ReentrantLock();
    private Condition consumer = lock.newCondition();
    private Condition producer = lock.newCondition();

    public MyBlockingQueue2(int max) {
        this.max = max;
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (len == 0) {
                consumer.await();
            }
            t = list.removeFirst();
            len--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public void put(T t) {
        try {
            lock.lock();
            while (len == max) {
                producer.await();
            }
            list.add(t);
            len++;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue2<String> queue = new MyBlockingQueue2<>(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + ".get() = " + queue.get());
                }
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    queue.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }
}

