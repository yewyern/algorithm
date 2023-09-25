package math;

/**
 * <a href="https://leetcode.cn/problems/distribute-money-to-maximum-children">2591. 将钱分给最多的儿童</a>
 *
 * @author xuzhou
 * @since 2023/9/22 16:59
 */
public class DistributeMoneyToMoreChildrenTest {

    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        if (money == children << 3) {
            return children;
        }
        // 第一步，每个孩子分配1元，保证每个人都分到
        money -= children;
        // 以7元一份进行拆分
        int count = money / 7;
        int mod = money % 7;
        if (count >= children) {
            return children - 1;
        }
        if (count == children - 1 && mod == 3) {
            return children - 2;
        }
        return count;
    }
}
