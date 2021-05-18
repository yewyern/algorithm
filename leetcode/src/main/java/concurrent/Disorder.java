package concurrent;

/**
 * @author zhou.xu
 * @date 2020/2/21 19:02
 */
public class Disorder {

    private static int a = 0, b = 0, x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            Thread thread1 = new Thread(() -> {
                a = 1;
                y = b;
            });
            Thread thread2 = new Thread(() -> {
                b = 1;
                x = a;
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            if (x == 0 && y == 0) {
                System.err.println("count = " + count);
                break;
            }
        }
    }
}
