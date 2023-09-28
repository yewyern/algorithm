package data_structure.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/">剑指 Offer 59 - II. 队列的最大值</a>
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * <p>
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 * @author zhou.xu
 * @since 2023/6/17 12:00
 */
public class MaxQueue {

    // 保存值和索引的队列
    private final LinkedList<int[]> valQueue;
    // 保存最大值和对应索引的队列，此队列保持单调递减
    // 通过index判断，保证与valQueue同时入队和出队
    private final LinkedList<int[]> maxQueue;
    private int index;

    public MaxQueue() {
        valQueue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    public int max_value() {
        if (maxQueue.isEmpty()) {
            return -1;
        }
        return maxQueue.getFirst()[0];
    }

    public void push_back(int value) {
        int[] val = {value, index};
        index++;
        valQueue.addLast(val);
        while (!maxQueue.isEmpty() && maxQueue.peekLast()[0] <= value) {
            maxQueue.pollLast();
        }
        maxQueue.addLast(val);
    }

    public int pop_front() {
        if (valQueue.isEmpty()) {
            return -1;
        }
        int[] val = valQueue.pollFirst();
        int[] maxQueueFirst = maxQueue.peekFirst();
        if (maxQueueFirst != null && maxQueueFirst[1] == val[1]) {
            maxQueue.pollFirst();
        }
        return val[0];
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MaxQueue.class.getSimpleName() + "[", "]")
                .add("valQueue=" + toString(valQueue))
                .add("maxQueue=" + toString(maxQueue))
                .add("index=" + index)
                .toString();
    }

    private String toString(LinkedList<int[]> list) {
        return list.stream().map(Arrays::toString).collect(Collectors.toList()).toString();
    }
}
