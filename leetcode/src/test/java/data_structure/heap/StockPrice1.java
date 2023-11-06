package data_structure.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <a href="https://leetcode.cn/problems/stock-price-fluctuation">2034. 股票价格波动</a>
 * @author xuzhou
 * @since 2023/10/8 16:43
 */
public class StockPrice1 {

    private final TreeMap<Integer, Integer> priceMap = new TreeMap<>();
    private final Map<Integer, Integer> stock = new HashMap<>();
    private int currTimestamp = 0;

    public StockPrice1() {

    }

    public void update(int timestamp, int price) {
        if (timestamp > currTimestamp) {
            currTimestamp = timestamp;
        }
        Integer lastPrice = stock.put(timestamp, price);
        if (lastPrice != null) {
            Integer count = priceMap.get(lastPrice);
            if (count == 1) {
                priceMap.remove(lastPrice);
            } else {
                priceMap.put(lastPrice, count - 1);
            }
        }
        Integer count = priceMap.getOrDefault(price, 0);
        priceMap.put(price, count + 1);
    }

    public int current() {
        return stock.get(currTimestamp);
    }

    public int maximum() {
        return priceMap.lastKey();
    }

    public int minimum() {
        return priceMap.firstKey();
    }


}
