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
     * .   0110
     * X   0111
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

    /**
     * 判断是否是负数
     */
    public static boolean isNeg(int a) {
        return a < 0;
    }

    /**
     * <p>位运算实现除法</p>
     * <p>a / b算法表达如下：将b左移到恰好小于a的值，左移的位数即结果中1的位置，再将a-b<<i，循环直到a<b</p>
     * <p>具体的做法中，使用a右移来判断b需要左移的位数，否则b可能出现溢出，导致异常</p>
     * <p>    101</p>
     * <p>   ----</p>
     * <p>11|1111</p>
     * <p>   11</p>
     * <p>-------</p>
     * <p>     11</p>
     * <p>     11</p>
     * <p>_______</p>
     * <p>      0</p>
     */
    public static int divide(int a, int b) {
        // 负数处理有bug，先全部转换成正数
        int x = isNeg(a) ? neg(a) : a;
        int y = isNeg(b) ? neg(b) : b;
        int ans = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            //
            if ((x >> i) >= y) {
                ans |= 1 << i;
                x = minus(x, y << i);
            }
        }
        // 如果符号不同，返回负数
        return isNeg(a) ^ isNeg(b) ? neg(ans) : ans;
    }

    public static void main(String[] args) {
        System.out.println(add(4, 5));
        System.out.println(neg(4));
        System.out.println(minus(5, 4));
        System.out.println(multiply(5, 4));
        System.out.println(multiply(5, -4));
        System.out.println(divide(12, 3));
    }
}
