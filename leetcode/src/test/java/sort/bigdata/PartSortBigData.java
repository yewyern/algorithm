package sort.bigdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import org.junit.Test;

/**
 * @author xuzhou
 * @since 2021/6/24 17:24
 */
public class PartSortBigData {

//    private static final Executor executor = Executors.newFixedThreadPool(2000);

    private static final LinkedBlockingDeque<DataNode> queue = new LinkedBlockingDeque<>(100);
    private static volatile boolean isEnd = false;

    public void partition(File f) {
        List<File> files = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            int count = 1;
            int N = 100_0000;
            Thread t1 = new Thread(new SortPartRunnable());
            t1.start();
            Thread t2 = new Thread(new SortPartRunnable());
            t2.start();
            while (reader.ready()) {
                int[] A = new int[N];
                String s;
                int i;
                for (i = 0; i < N && (s = reader.readLine()) != null; i++) {
                    A[i] = Integer.parseInt(s);
                }
                System.out.println(count + ", len = " + i);
//            System.out.println("sort = " + ArrayUtils.checkSort(A));
                String filename = count++ + ".txt";
                File file = new File(f.getParentFile(), filename);

                DataNode dataNode = new DataNode(A, i, file.getAbsolutePath());
                queue.offer(dataNode);
//                sortPart(A, i, file);
//                files.add(file);
            }
            isEnd = true;
            t1.join();
            t2.join();
        } catch (IOException | InterruptedException e) {
            isEnd = true;
            e.printStackTrace();
        }
    }

    public static void sortPart(int[] A, int size, File f) {
//        MergeSort.mergeSortLoop(A, 0, size);//657s
        Arrays.sort(A, 0, size);// 517s
        write(A, size, f);
//        executor.execute(() -> write(A, size, f)); // 多线程的吞吐量没有提升，反而下降了
        // 使用1个线程读，1个线程写 内存占用上升了，
        // 队列500，4G内存，368s
        // 队列100，1.8G内存，265s
        // 使用1个线程读，2个线程写 内存占用下降
        // 队列100，650M内存，319s
    }

    public static void write(int[] a, int size, File f) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (int i = 0; i < size; i++) {
                bw.write(a[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class DataNode {

        final int[] A;
        final int size;
        final String filename;

        public DataNode(int[] a, int size, String filename) {
            A = a;
            this.size = size;
            this.filename = filename;
        }
    }

    private static class SortPartRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    if (isEnd && queue.size() == 0) {
                        break;
                    }
                    DataNode dataNode = queue.take();
                    sortPart(dataNode.A, dataNode.size, new File(dataNode.filename));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test() {
        String tempDir = System.getenv("TEMP");
        System.out.println("tempDir = " + tempDir);
        File file = new File(tempDir + "\\testSortBigData\\test.txt");
        long s = System.currentTimeMillis();
        partition(file);
        System.out.println("time spent = " + (System.currentTimeMillis() - s) / 1000); // 517s
    }
}
