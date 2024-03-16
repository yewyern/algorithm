package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>有一个生产奶酪的厂家，每天需要生产100000份奶酪卖给超市，通过一辆货车发货，送货车每次送100份。
 * <p>
 * <p>厂家有一个容量为1000份的冷库，用于奶酪保鲜，生产的奶酪需要先存放在冷库，运输车辆从冷库取货。
 * <p>
 * <p>厂家有三条生产线，分别是牛奶供应生产线，发酵剂制作生产线，奶酪生产线。
 * <p>生产每份奶酪需要2份牛奶和一份发酵剂。
 * <p>
 * <p>请设计生产系统？
 *
 * @author zhou.xu
 * @since 2020/11/16 11:20
 */
public class CheeseFactory {

    private static final int DEMAND = 1000;
    private static final LinkedTransferQueue<String> MILK_QUEUE = new LinkedTransferQueue<>();
    private static final LinkedTransferQueue<String> STARTER_QUEUE = new LinkedTransferQueue<>();
    private static final ArrayBlockingQueue<String> COLD_STORAGE = new ArrayBlockingQueue<>(1000);
    private static final Semaphore SEMAPHORE = new Semaphore(1000);
    private static final AtomicInteger count = new AtomicInteger();

    public static void addToColdStorage(String product) {
        COLD_STORAGE.offer(product);
        int i = count.incrementAndGet();
        if (i % 100 == 0) {
            SEMAPHORE.release(1);
        }
    }

    static class MilkProducer extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= DEMAND * 2; i++) {
                CheeseFactory.MILK_QUEUE.offer("milk" + i);
                System.out.println("produced " + i + " milk");
            }
        }
    }

    static class StarterProducer extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= DEMAND; i++) {
                CheeseFactory.STARTER_QUEUE.offer("starter" + i);
                System.out.println("produced " + i + " starter");
            }
        }
    }

    static class CheeseProducer extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= DEMAND; i++) {
                try {
                    String milk1 = CheeseFactory.MILK_QUEUE.take();
                    String milk2 = CheeseFactory.MILK_QUEUE.take();
                    String starter = CheeseFactory.STARTER_QUEUE.take();
                    addToColdStorage("cheese" + i);
                    System.out.println("produced " + i + " cheese, used: " + milk1 + "," + milk2 + "," + starter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Transport extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= DEMAND / 100; i++) {
                try {
                    SEMAPHORE.acquire();
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < 100; j++) {
                        String take = COLD_STORAGE.take();
                        sb.append(take).append(",");
                    }
                    System.out.println("transport " + i + ": " + sb.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MilkProducer milkProducer = new MilkProducer();
        StarterProducer starterProducer = new StarterProducer();
        CheeseProducer cheeseProducer = new CheeseProducer();
        Transport transport = new Transport();
        milkProducer.start();
        starterProducer.start();
        cheeseProducer.start();
        transport.start();
        try {
            transport.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
