package utils;

import com.alibaba.fastjson2.JSON;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author zhou.xu
 * @since 2020/2/24 19:49
 */
public class TestUtils {

    public static <T> void test(Supplier<T> supplier, int testCase) {
        long l = System.currentTimeMillis();
        T res = supplier.get();
        long l1 = System.currentTimeMillis();
        System.out.println("test case = " + testCase + ", res = " + res + ", time spent = " + (l1 - l));
    }

    public static void testIntArray(Supplier<int[]> supplier, int testCase) {
        long l = System.currentTimeMillis();
        int[] res = supplier.get();
        long l1 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("test case = " + testCase + ", res = [");
        for (int i = 0; i < res.length; i++) {
            sb.append(res[i] + ", ");
        }

        sb.append("], time spent = " + (l1 - l));
        System.out.println(sb.toString());
    }

    public static void test(Class<?> cls, String methodName, Object expected, Object... args)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                method.setAccessible(true);
                Constructor<?> constructor = cls.getConstructor();
                Object instance = constructor.newInstance();
                Object res = method.invoke(instance, args);
                if (expected.equals(res)) {
                    System.out.println(JSON.toJSONString(args) + "，结果：" + res + "，预期：" + expected + "，测试通过！");
                    return;
                } else {
                    throw new RuntimeException(JSON.toJSONString(args) + "，结果：" + res + "，预期：" + expected + "，测试不通过！");
                }
            }
        }
//        Method method = cls.getMethod(methodName, (Class<?>[]) Arrays.stream(args).map(Object::getClass).toArray());
        Method method = cls.getMethod(methodName);
        method.setAccessible(true);
//        Constructor<?> constructor = cls.getConstructor();
//        Object instance = constructor.newInstance();

    }
}
