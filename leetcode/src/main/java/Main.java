import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhou.xu
 * @date 2020/2/26 18:42
 */
public class Main {

    public static void main(String[] args) {
        Integer[] ints = new Integer[100];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i;
        }
        List<Integer> collect = Arrays.stream(ints).skip(90).limit(20).collect(Collectors.toList());
        System.out.println("list = " + collect);
    }
}
