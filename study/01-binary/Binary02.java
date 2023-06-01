/**
 * 位运算实现加减乘除
 *
 * @author zhou.xu
 * @since 2023/6/1 22:39
 */
public class Binary02 {

    /**
     * 位运算实现加法
     */
    public static int add(int a, int b) {
        int sum = 0;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(add(4, 5));
    }
}
