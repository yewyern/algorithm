package search.binary;

/**
 * <a href="https://leetcode.cn/problems/guess-number-higher-or-lower">374. 猜数字大小</a>
 *
 * @author zhou.xu
 * @since 2024/1/21 16:49
 */
public class GuessGame {

    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l <= r) {
            int num = ((r - l) >> 1) + l;
            int guessed = guess(num);
            if (guessed == 0) {
                return num;
            } else if (guessed == 1) {
                l = num + 1;
            } else {
                r = num - 1;
            }
        }
        return l;
    }

    int guess(int num) {
        return (int) (Math.random() * 3 - 1);
    }
}
