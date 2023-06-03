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

    /**
     * -a
     */
    public static int neg(int a) {
        return ~a + 1;
    }

    /**
     * 位运算实现减法
     * a - b = a + (-b) = a + (~a + 1)
     */
    public static int minus(int a, int b) {
        return add(a, neg(b));
    }

    /**
     * 位运算实现乘法，a对于b中所有存在1的位进行左移，并对结果相加
     * 0110
     * X0111
     * =   0110
     * +  01100
     * + 011000
     */
    public static int multiply(int a, int b) {
        int ans = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                ans = add(ans, a);
            }
            a <<= 1;
            // >> 如果b是负数，左移时，左边符号位不变
            // >>> 如果b是负数，左移时，左边符号位右移
            b >>>= 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(add(4, 5));
        System.out.println(neg(4));
        System.out.println(minus(5, 4));
        System.out.println(multiply(5, 4));
        System.out.println(multiply(5, -4));
    }
}
