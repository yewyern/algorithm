package data_structure.queue;

import org.junit.Test;

/**
 * @author zhou.xu
 * @since 2023/6/15 22:17
 */
public class MaxQueueTest {

    @Test
    public void test() {
        MaxQueue2 maxQueue = new MaxQueue2();
        maxQueue.push_back(2);
        maxQueue.push_back(-1);
        maxQueue.push_back(1);
        System.out.println("maxQueue = " + maxQueue);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println("maxQueue = " + maxQueue);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println("maxQueue = " + maxQueue);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println("maxQueue = " + maxQueue);
    }

}
