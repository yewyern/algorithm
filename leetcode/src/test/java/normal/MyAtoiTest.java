package normal;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author zhou.xu
 * @since 2020/8/14 17:51
 */
public class MyAtoiTest {

    private final MyAtoi instance = new MyAtoi();

    @Test
    public void testMyAtoi() {
        test("42", 42);
        test("   +42", 42);
        test("   -42", -42);
        test("   --42", 0);
        test("4193 with words", 4193);
        test("words and 987", 0);
        test("-91283472332", Integer.MIN_VALUE);
        test("91283472332", Integer.MAX_VALUE);
        test("    0000000000000   ", 0);
        test("    0-1   ", 0);
    }

    public void test(String str, int excepted) {
        System.out.println("str = " + str);
        int res = instance.myAtoi(str);
        System.out.println("res = " + res);
        System.out.println("exp = " + excepted);
        TestCase.assertEquals(res, excepted);
        System.out.println("-------------------------");
    }
}