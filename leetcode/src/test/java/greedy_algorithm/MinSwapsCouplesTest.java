package greedy_algorithm;

/**
 * <a href="https://leetcode.cn/problems/couples-holding-hands">765. 情侣牵手</a>
 * @author xuzhou
 * @since 2023/11/15 17:02
 */
public class MinSwapsCouplesTest {

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            map[row[i]] = i;
        }
        int count = 0;
        for (int i = 0; i < n; i += 2) {
            if (row[i] >> 1 != row[i + 1] >> 1) {
                int couple = couple(row[i]);// i+1位置应该坐的是哪个
                int j = map[couple]; // 情侣现在坐在哪个位置
                swap(map, row[i + 1], couple); // 交换情侣位置索引map
                swap(row, i + 1, j); // 交换情侣实际的位置
                count++;
            }
        }
        return count;
    }

    private void swap(int[] nums, int i , int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int couple(int a) {
        return (a & 1) == 0 ? a + 1 : a - 1;
    }
}
