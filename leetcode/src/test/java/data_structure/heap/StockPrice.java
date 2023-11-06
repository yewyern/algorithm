package data_structure.heap;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/stock-price-fluctuation">2034. 股票价格波动</a>
 * @author xuzhou
 * @since 2023/10/8 16:43
 */
public class StockPrice {

    private final Map<Integer, Integer> stock = new HashMap<>();
    private int currTimestamp = 0;
    private final Map<Integer, Integer> priceCountMap = new HashMap<>();
    private final PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    private final PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

    public StockPrice() {
    }

    public void update(int timestamp, int price) {
        if (timestamp > currTimestamp) {
            currTimestamp = timestamp;
        }
        Integer lastPrice = stock.put(timestamp, price);
        if (lastPrice != null) {
            if (lastPrice == price) {
                return;
            }
            Integer count = priceCountMap.get(lastPrice);
            if (count > 1) {
                priceCountMap.put(lastPrice, count - 1);
            } else {
                priceCountMap.remove(lastPrice);
                maxQueue.remove(lastPrice);
                minQueue.remove(lastPrice);
            }
        }
        Integer count = priceCountMap.getOrDefault(price, 0);
        priceCountMap.put(price, count + 1);
        if (count == 0) {
            maxQueue.add(price);
            minQueue.add(price);
        }
    }

    public int current() {
        return stock.get(currTimestamp);
    }

    public int maximum() {
        return maxQueue.peek();
    }

    public int minimum() {
        return minQueue.peek();
    }


}
