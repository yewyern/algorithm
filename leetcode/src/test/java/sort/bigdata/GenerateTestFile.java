package sort.bigdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;
import utils.RandomUtils;

/**
 * @author xuzhou
 * @date 2021/6/24 17:26
 */
public class GenerateTestFile {

    @Test
    public void test() throws IOException {
        String tempDir = System.getenv("TEMP");
        System.out.println("tempDir = " + tempDir);
        File file = new File(tempDir + "\\testSortBigData\\test.txt");
        delFile(file);
        createFile(file, false);
        long s = System.currentTimeMillis();
        writeRandomNumberFile(file);
        System.out.println("time spent = " + (System.currentTimeMillis() - s) / 1000);
    }

    private void writeRandomNumberFile(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            int MAX = Integer.MAX_VALUE;
            for (int i = 0; i < MAX; i++) {
                int n = RandomUtils.nextInt(MAX);
                // 直接写出整形，占用7.8g，耗费128s
                bw.write(n);
                bw.write('\n');
                // 写出字符串，占用20g
                // bw.write(n + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delFile(File f) {
        if (f.exists()) {
            boolean delete = f.delete();
        }
    }

    private void createFile(File f, boolean isDir) throws IOException {
        if (f.exists()) {
            return;
        }
        if (isDir) {
            boolean mkdirs = f.mkdirs();
        } else {
            createFile(f.getParentFile(), true);
            boolean newFile = f.createNewFile();
        }
    }
}
