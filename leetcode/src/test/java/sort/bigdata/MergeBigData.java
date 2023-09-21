package sort.bigdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author xuzhou
 * @since 2021/6/30 15:48
 */
public class MergeBigData {

    private void merge(List<File> fileList, File resFile) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(resFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("结果文件失败");
        }
        System.out.println("resFile = " + resFile);
        List<BufferedReader> list = fileList.stream().filter(File::exists).map(file -> {
            try {
                return new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        TreeSet<Node> set = new TreeSet<>();
        for (BufferedReader bufferedReader : list) {
            try {
                String line = bufferedReader.readLine();
                set.add(new Node(bufferedReader, Integer.parseInt(line)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int N = 100_0000;
        int[] A = new int[N];
        int i = 0;
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (!set.isEmpty()) {
            Node first = set.pollFirst();
            if (first == null) {
                break;
            }
            A[i++] = first.num;
            try {
                BufferedReader reader = first.reader;
                String line = reader.readLine();
                if (line != null && line.trim().length() > 0) {
                    first.num = Integer.parseInt(line);
                    set.add(first);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("set.size() = " + set.size());
            if (i == N) {
                write(A, N, bw);
                i = 0;
            }
        }
        write(A, i, bw);
        for (BufferedReader bufferedReader : list) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(int[] a, int size, BufferedWriter bw) {
        try {
            System.out.println("size = " + size);
            for (int i = 0; i < size; i++) {
                bw.write(a[i] + "\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Node implements Comparable<Node> {

        final BufferedReader reader;
        int num;

        public Node(BufferedReader reader, int num) {
            this.reader = reader;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(num, o.num);
        }
    }

    @Test
    public void test() {
        String tempDir = System.getenv("TEMP");
        System.out.println("tempDir = " + tempDir);
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < 2148; i++) {
            File file = new File(tempDir + "\\testSortBigData\\" + (i + 1) + ".txt");
            fileList.add(file);
        }
        long s = System.currentTimeMillis();
        merge(fileList, new File(tempDir + "\\testSortBigData\\res.txt"));
        System.out.println("time spent = " + (System.currentTimeMillis() - s) / 1000); // 517s
    }

}
