package array;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/summary-ranges/">228. 汇总区间</a>
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * 示例 2：
 * <p>
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 *
 * @author zhou.xu
 * @since 2022/11/4 21:36
 */
public class SummaryRangesTest {

    public List<String> summaryRanges(int... nums) {
        List<String> res = new ArrayList<>();
        int N = nums.length;
        if (N == 0) {
            return res;
        }
        int start = nums[0], end;
        for (int i = 1; i < N; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                end = nums[i - 1];
                addResult(res, start, end);
                start = nums[i];
            }
        }
        addResult(res, start, nums[N - 1]);
        return res;
    }

    public void addResult(List<String> res, int start, int end) {
        if (start == end) {
            res.add(Integer.toString(start));
        } else {
            // builder比+快
            String builder = start +
                    "->" +
                    end;
            res.add(builder);
        }
    }

    @Test
    public void test() {
        System.out.println(summaryRanges(0, 1, 2, 4, 5, 7));
        System.out.println(summaryRanges(0, 2, 3, 4, 6, 8, 9));
    }
}
