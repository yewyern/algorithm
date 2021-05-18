package concurrent;

import java.util.Random;
import org.junit.Test;

/**
 * 有一个总任务A，分解为子任务A1 A2 A3 ...，任何一个子任务失败后要快速取消所有任务，请写程序模拟。
 *
 * @author zhou.xu
 * @date 2020/11/16 11:10
 */
public class FastCancelTasks {

    volatile boolean isError = false;
    Random random = new Random();

    public void func() {
        while (!isError) {
            int i = random.nextInt(100);
            if (i == 0) {
                isError = true;
                System.out.println("error");
            } else {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

    @Test
    public void test() {
        Thread task1 = new Thread(this::func);
        Thread task2 = new Thread(this::func);
        Thread task3 = new Thread(this::func);

        task1.start();
        task2.start();
        task3.start();
        try {
            task1.join();
            task2.join();
            task3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
