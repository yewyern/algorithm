package data_structure.hash;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/insert-delete-getrandom-o1">380. O(1) 时间插入、删除和获取随机元素</a>
 * @author xuzhou
 * @since 2023/11/16 14:00
 */
public class RandomizedSet {

    private Set<Integer> set;

    public RandomizedSet() {
        // todo
        set = new HashSet<>();
    }

    public boolean insert(int val) {
        return set.add(val);
    }

    public boolean remove(int val) {
        return set.remove(val);
    }

    public int getRandom() {
        int n = (int) (Math.random() * set.size());
        return (int) set.toArray()[n];
    }
}
