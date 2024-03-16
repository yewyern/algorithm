package data_structure.tree;

import org.junit.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/validate-binary-tree-nodes/">1361. 验证二叉树</a>
 * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
 * <p>
 * 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
 * <p>
 * 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。
 * <p>
 * 注意：节点没有值，本问题中仅仅使用节点编号。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^4
 * leftChild.length == rightChild.length == n
 * -1 <= leftChild[i], rightChild[i] <= n - 1
 *
 * @author xuzhou
 * @since 2023/9/5 16:14
 */
public class ValidateBinaryTreeTest {

    private int[] leftChild;
    private int[] rightChild;
    private boolean[] visited;
    private boolean[] parents;
    private int parentCount;
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        visited = new boolean[n];
        parents = new boolean[n];
        parentCount = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            boolean valid = process(i);
            if (!valid) {
                return false;
            }
            parents[i] = true;
            parentCount++;
        }
        return parentCount == 1; // 存在多个根节点的肯定是无效的
    }

    private boolean process(int cur) {
        if (cur < 0) {
            return true;
        }
        if (visited[cur]) {
            if (parents[cur]) {
                parents[cur] = false; // 非根节点移出
                parentCount--;
                return true;
            } else {
                // 已经有父节点，不能再有父节点
                return false;
            }
        }
        visited[cur] = true;
        return process(leftChild[cur]) && process(rightChild[cur]);
    }


    public boolean validateBinaryTreeNodes0(int n, int[] leftChild, int[] rightChild) {
        boolean[] visited = new boolean[n];
        boolean[] parents = new boolean[n];
        int parentCount = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            stack.push(i);
            while (!stack.isEmpty()) {
                // 宽度优先遍历
                Integer cur = stack.pop();
                if (visited[cur]) {
                    if (parents[cur]) {
                        parents[cur] = false; // 非根节点移出
                        parentCount--;
                        continue;
                    } else {
                        // 已经有父节点，不能再有父节点
                        return false;
                    }
                }
                visited[cur] = true;
                if (leftChild[cur] >= 0) {
                    stack.push(leftChild[cur]); // 加入左孩子
                }
                if (rightChild[cur] >= 0) {
                    stack.push(rightChild[cur]); // 加入右孩子
                }
            }
            parents[i] = true;
            parentCount++;
        }
        return parentCount == 1; // 存在多个根节点的肯定是无效的
    }

    public boolean validateBinaryTreeNodes1(int n, int[] leftChild, int[] rightChild) {
        boolean[] visited = new boolean[n];
        boolean[] parents = new boolean[n];
        int parentCount = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            queue.add(i);
            while (!queue.isEmpty()) {
                // 宽度优先遍历
                Integer cur = queue.pollFirst();
                if (visited[cur]) {
                    if (parents[cur]) {
                        parents[cur] = false; // 非根节点移出
                        parentCount--;
                        continue;
                    } else {
                        // 已经有父节点，不能再有父节点
                        return false;
                    }
                }
                visited[cur] = true;
                if (leftChild[cur] >= 0) {
                    queue.add(leftChild[cur]); // 加入左孩子
                }
                if (rightChild[cur] >= 0) {
                    queue.add(rightChild[cur]); // 加入右孩子
                }
            }
            parents[i] = true;
            parentCount++;
        }
        return parentCount == 1; // 存在多个根节点的肯定是无效的
    }

    public boolean validateBinaryTreeNodes2(int n, int[] leftChild, int[] rightChild) {
        boolean[] visited = new boolean[n];
        Set<Integer> parents = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            queue.add(i);
            while (!queue.isEmpty()) {
                // 宽度优先遍历
                Integer cur = queue.pollFirst();
                if (visited[cur]) {
                    if (parents.contains(cur)) {
                        parents.remove(cur); // 非根节点移出
                        continue;
                    } else {
                        // 已经有父节点，不能再有父节点
                        return false;
                    }
                }
                visited[cur] = true;
                if (leftChild[cur] >= 0) {
                    queue.add(leftChild[cur]); // 加入左孩子
                }
                if (rightChild[cur] >= 0) {
                    queue.add(rightChild[cur]); // 加入右孩子
                }
            }
            parents.add(i);
        }
        return parents.size() == 1; // 存在多个根节点的肯定是无效的
    }

    @Test
    public void test() {
        System.out.println(validateBinaryTreeNodes(3, new int[]{1,2,0}, new int[]{-1,-1,-1}));
    }
}
