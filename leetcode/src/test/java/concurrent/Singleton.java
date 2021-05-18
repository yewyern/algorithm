package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.junit.Test;

/**
 * @author zhou.xu
 * @date 2020/9/25 10:03
 */
public class Singleton {

    private static Singleton INSTANCE = null;
    private Executor executor = Executors.newFixedThreadPool(100);

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            CompletableFuture.runAsync(() -> {
                Singleton instance = getInstance();
                System.out.println(instance);
            }, executor);
        }
    }
}
