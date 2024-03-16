package simulate;

/**
 * <a href="https://leetcode.cn/problems/WHnhjV">LCP 50. 宝石补给</a>
 * @author xuzhou
 * @since 2023/9/15 10:43
 */
public class GiveGemTest {

    public int giveGem(int[] gem, int[][] operations) {
        for (int[] operation : operations) {
            int given = gem[operation[0]] >> 1;
            gem[operation[0]] -= given;
            gem[operation[1]] += given;
        }
        int max = gem[0];
        int min = gem[0];
        for (int g : gem) {
            max = Math.max(max, g);
            min = Math.min(min, g);
        }
        return max - min;
    }
}
