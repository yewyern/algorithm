package test;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * <p> 有如下字符串，包含3条关于水果数量的信息
 * <p> “5 西瓜 苹果 蓝莓 苹果 苹果 3 桃 橙子 桃 0”
 * <p> 数字代表该数字后面出现的一组信息的水果数量，直到出现0为止，代表信息结束。
 * <p> 请输出每组信息中出现次数最多的水果。
 * <p> （假设每组信息都保证有数量最多的1种水果）
 *
 * @author zhou.xu
 * @date 2020/10/12 17:06
 */
public class CountFruits {

    public void maxFruits(String fruitStr) {
        Map<String, Integer> map = new HashMap<>();
        String max = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fruitStr.length(); i++) {
            boolean isDigit = true;
            while (i < fruitStr.length() && fruitStr.charAt(i) != ' ') {
                if (!Character.isDigit(fruitStr.charAt(i))) {
                    isDigit = false;
                }
                sb.append(fruitStr.charAt(i));
                i++;
            }
            if (isDigit) {
                if (!"".equals(max)) {
                    System.out.println("max = " + max);
                }
                max = "";
                map.clear();
            } else {
                String fruit = sb.toString();
                map.put(fruit, map.getOrDefault(fruit, 0) + 1);
                if (map.get(fruit) > map.getOrDefault(max, 0)) {
                    max = fruit;
                }
            }
            sb = new StringBuilder();
        }
    }

    @Test
    public void test() {
        maxFruits("5 西瓜 苹果 蓝莓 苹果 苹果 3 桃 橙子 桃 0");
    }
}
