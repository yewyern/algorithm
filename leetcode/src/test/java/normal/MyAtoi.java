package normal;

/**
 * @author zhou.xu
 * @since 2020/8/14 16:56
 */
public class MyAtoi {

    public int myAtoi(String str) {
        final char space = ' ';
        final char positive = '+';
        final char negative = '-';
        final char zero = '0';
        final char nine = '9';
        int res = 0, f = 1;
        int start = 0;
        while (start < str.length() && space == str.charAt(start)) {
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (positive == c || negative == c) {
                if (i > start) {
                    break;
                }
                f = negative == c ? -1 : 1;
            } else if (c >= zero && c <= nine) {
                long temp = res;
                temp *= 10;
                temp += (c - zero) * f;
                if (temp < Integer.MIN_VALUE) {
                    res = Integer.MIN_VALUE;
                    break;
                } else if (temp > Integer.MAX_VALUE) {
                    res = Integer.MAX_VALUE;
                    break;
                }
                res = (int) temp;
            } else {
                break;
            }
        }
        return res;
    }
}
