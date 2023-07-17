package data_structure.tree;

import data_structure.stack.MyStack;
import org.junit.Test;
import utils.RandomArray;
import utils.RandomUtils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/description/?envType=study-plan-v2&envId=coding-interviews">剑指 Offer 33. 二叉搜索树的后序遍历序列</a>
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * <p>
 * <p>
 * 参考以下这颗二叉搜索树：
 * <p>
 * 5
 * / \
 * 2   6
 * / \
 * 1   3
 * 示例 1：
 * <p>
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 * <p>
 * 输入: [1,3,2,6,5]
 * 输出: true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 数组长度 <= 1000
 *
 * @author xuzhou
 * @since 2023/7/14 15:36
 */
public class VerifyPostorderTest {


    // 所谓前中后序遍历，代表的是根节点的顺序，子节点顺序都是先左后右
    // 即前序遍历为，根->左->右
    // 中序遍历为，左->根->右
    // 后序遍历为，左->右->根
    // 所以后序遍历根节点在最后，对于任意位置，它左边的位置，
    // 必须满足一段大于它，一段小于它，且大于它的数在小于它的数的右边
    public boolean verifyPostorder(int[] postorder) {
        // 因为如果是搜索树，任意节点左边肯定小于它，它右边一定大于它
        // 后序遍历，在后面的数一定是根节点或者右数
        // 自带的栈要1ms，重新定义一个，链表实现要比数组实现慢
        MyStack minS = new MyStack(postorder.length);
        int root = Integer.MAX_VALUE; // 不需要2个栈
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) {
                return false;
            }
            while (!minS.isEmpty() && minS.peek() > postorder[i]) {
                root = minS.pop();
            }
            minS.push(postorder[i]);
        }
        return true;
    }

    public boolean verifyPostorder1(int[] postorder) {
        // 因为如果是搜索树，任意节点左边肯定小于它，它右边一定大于它
        // 后序遍历，在后面的数一定是根节点或者右数
        // 自带的栈要1ms，重新定义一个，链表实现要比数组实现慢
        MyStack minS = new MyStack(postorder.length);
        MyStack maxS = new MyStack(postorder.length);
        for (int i = postorder.length - 1; i >= 0; i--) {
            while (!minS.isEmpty() && minS.peek() > postorder[i]) {
                int pop = minS.pop();
                if (!maxS.isEmpty() && maxS.peek() < pop) {
                    return false;
                }
                maxS.push(pop);
            }
            minS.push(postorder[i]);
        }
        if (!minS.isEmpty() && !maxS.isEmpty()) {
            return minS.peek() < maxS.peek();
        }
        return true;
    }

    public boolean verifyPostorder2(int[] postorder) {
        // 暴力解法
        int N = postorder.length;
        for (int i = N - 1; i >= 1; i--) {
            int j = i - 1;
            while (j >= 0 && postorder[j] > postorder[i]) {
                j--;
            }
            while (j >= 0 && postorder[j] < postorder[i]) {
                j--;
            }
            if (j >= 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int tests = 100000;
        int max = 100000;
        int len = 1000;
        for (int i = 0; i < tests; i++) {
            int[] nums = RandomArray.generateNoRepeatSortedArray(RandomUtils.nextInt(len), 0, max);
            boolean res1 = verifyPostorder(nums);
            boolean res2 = verifyPostorder2(nums);
            if (res1 != res2) {
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                break;
            }
        }
    }
}
