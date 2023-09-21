package concurrent;

import java.util.LinkedList;

/**
 * @author zhou.xu
 * @since 2020/2/28 22:47
 */
public class MyBlockingQueue<T> {

    private final LinkedList<T> list = new LinkedList<T>();
    private int len = 0;
    private final int max;

    public MyBlockingQueue(int max) {
        this.max = max;
    }

    public synchronized T get() {
        while (len == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = list.removeFirst();
        len--;
        this.notifyAll();
        return t;
    }

    public synchronized void put(T t) {
        while (len == max) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        len++;
        this.notify();
    }

    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
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

