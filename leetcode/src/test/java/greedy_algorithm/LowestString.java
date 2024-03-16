package greedy_algorithm;

import java.util.Arrays;
import java.util.TreeSet;
import org.junit.Test;
import utils.RandomArray;

/**
 * 给定一组字符串，将所有字符串拼接，要求得到的字符串是所有可能字符串中字典序最大的
 *
 * 例如：
 * 给定：["ba", "b"]
 * 返回："bab"
 */
public class LowestString {

    /**
     * 对所有字符串排序，排序策略是
     * a + b < b + a 则 a 在前，b 在后
     *
     * @param arr
     * @return
     */
    public String lowestString(String[] arr) {
        // TODO
        System.out.println("arr = " + Arrays.toString(arr));
        Arrays.sort(arr, (a, b) -> (a + b).compareTo(b + a));
        System.out.println("arr = " + Arrays.toString(arr));
        StringBuilder res = new StringBuilder();
        for (String s : arr) {
            res.append(s);
        }
        return res.toString();
    }

    public String lowestStringComparison(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        TreeSet<String> treeSet = process(arr);
        return treeSet.first();
    }

    public TreeSet<String> process(String[] arr) {
        TreeSet<String> res = new TreeSet<>();
        int N = arr.length;
        if (N == 1) {
            res.add(arr[0]);
            return res;
        }
        for (int i = 0; i < N; i++) {
            String s = arr[i];
            TreeSet<String> treeSet = process(removeStringByIndex(arr, i));
            for (String string : treeSet) {
                res.add(s + string);
            }
        }
        return res;
    }


    public String[] removeStringByIndex(String[] arr, int index) {
        String[] ans = new String[arr.length - 1];
        for (int i = 0, ansIndex = 0; i < arr.length; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            String[] array = RandomArray.generateRandomStringArrayNoEmptyString(10, 10);
            String s = lowestString(array);
            String s1 = lowestStringComparison(array);
            if (!s.equals(s1)) {
                System.out.println("arr = " + Arrays.toString(array));
                System.out.println("s  = " + s);
                System.out.println("s1 = " + s1);
            }
        }
    }

    @Test
    public void test2() {
        String[] arr = {"bpzofy", "qwhAyMuT", "", "dU", "wWlLeVnp"};
        String[] arr2 = new String[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        String s = lowestString(arr);
        System.out.println("arr = " + Arrays.toString(arr));
        String s1 = lowestStringComparison(arr2);
        if (!s.equals(s1)) {
            System.out.println("s  = " + s);
            System.out.println("s1 = " + s1);
        }
    }


}
