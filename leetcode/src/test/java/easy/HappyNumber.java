package easy;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author zhou.xu
 * @since 2020/3/6 17:48
 */
public class HappyNumber {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + 1);
            System.out.println(isHappyNumber(i + 1));
            System.out.println("--------------------");
        }
    }

    /**
     * <p>【编程题】编写一个函数来判断一个数是不是“快乐数”。 “快乐数”定义：对于一
     * <p>个正整数，每一次将该数替换为它每个位置上的数字的平方和，
     * <p>然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。
     * <p>如果可以变为 1， 那么这个数就是快乐数。
     * <p>输入: 19
     * <p>输出: true
     * <p>解释:
     * <p>1^2 + 9^2 = 82
     * <p>8^2 + 2^2 = 68
     * <p>6^2 + 8^2 = 100
     * <p>1^2 + 0^2 + 0^2 = 1
     *
     * @param num
     * @return
     */
    public static boolean isHappyNumber(int num) {
        Set<Integer> set = new TreeSet<>();
        while (num != 1 && !set.contains(num)) {
            set.add(num);
            num = getNext(num);
            System.out.println("num = " + num);
        }
        return num == 1;
    }

    public static int getNext(int num) {
        int sum = 0;
        while (num > 0) {
            int i = num % 10;
            sum += i * i;
            num /= 10;
        }
        return sum;
    }

}
