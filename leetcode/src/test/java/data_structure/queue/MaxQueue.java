package data_structure.queue;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * <a href="https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 59 - II. 队列的最大值</a>
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
 * @since 2023/6/15 21:52
 */
public class MaxQueue {

    private final LinkedList<Integer> list;
    private final TreeSet<Integer> set;

    public MaxQueue() {
        list = new LinkedList<>();
        set = new TreeSet<>(Comparator.reverseOrder());
    }

    public int max_value() {
        if (set.isEmpty()) {
            return -1;
        }
        return set.first();
    }

    public void push_back(int value) {
        list.add(value);
        set.add(value);
    }

    public int pop_front() {
        if (list.isEmpty()) {
            return -1;
        }
        Integer num = list.pollFirst();
        set.remove(num);
        return num;
    }

}
