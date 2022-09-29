package normal;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * 整数转罗马数字
 *
 * @author zhou.xu
 * @since 2020/9/22 10:01
 */
public class IntToRomanTest {

    private static final Map<Integer, String> romanMap = new HashMap<>();

    static {
        romanMap.put(1, "I");
        romanMap.put(5, "V");
        romanMap.put(10, "X");
        romanMap.put(50, "L");
        romanMap.put(100, "C");
        romanMap.put(500, "D");
        romanMap.put(1000, "M");
    }

    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0) {
            while (num < nums[i]) {
                i++;
            }
            sb.append(romans[i]);
            num -= nums[i];
        }
//        int scale = 1;
//        while (num != 0) {
//            int i = num % 10;
//            if (i == 1 || i == 5) {
//                sb = romanMap.get(i * scale) + sb;
//            } else if (i == 9) {
//                sb = romanMap.get(scale) + romanMap.get(scale * 10) + sb;
//            } else if (i == 4) {
//                sb = romanMap.get(scale) + romanMap.get(scale * 5) + sb;
//            } else {
//                StringBuilder sb = new StringBuilder();
//                int k = i % 5;
//                for (int j = 0; j < k; j++) {
//                    sb.append(romanMap.get(scale));
//                }
//                sb = (i < 4 ? "" : romanMap.get(scale * 5)) + sb + sb;
//            }
//            num = num / 10;
//            scale *= 10;
//        }

        {
            return sb.toString();
        }
    }

    public void test(int num, String expected) {
        String roman = intToRoman(num);
        System.out.println(num + " = " + roman);
        Assert.assertEquals(expected, roman);
    }

    @Test
    public void test() {
        test(3, "III");
        test(4, "IV");
        test(9, "IX");
        test(58, "LVIII");
        test(1994, "MCMXCIV");
    }
}
