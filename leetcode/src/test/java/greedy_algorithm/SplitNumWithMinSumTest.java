package greedy_algorithm;

/**
 * <a href="https://leetcode.cn/problems/split-with-minimum-sum">2578. 最小和分割</a>
 * @author xuzhou
 * @since 2023/10/9 15:25
 */
public class SplitNumWithMinSumTest {

    public int splitNum(int num) {
        int[] map = new int[10];
        while (num > 0) {
            map[num % 10]++;
            num /= 10;
        }
        int a = 0, b = 0;
        boolean aFlag = true;
        for (int i = 1; i < 10; i++) {
            while (map[i] > 0) {
                if (aFlag) {
                    a = a * 10 + i;
                } else {
                    b = b * 10 + i;
                }
                aFlag = !aFlag;
                map[i]--;
            }
        }
        return a + b;
    }
}
