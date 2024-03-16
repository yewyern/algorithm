package data_structure.tree.binarysearch;

import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/unique-binary-search-trees/">96. 不同的二叉搜索树</a>
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * @author xuzhou
 * @since 2023/12/29 10:08
 */
public class NumTrees {

    private static final int[] NUMS = new int[20];
    static {
        NUMS[0] = 1;
        NUMS[1] = 1;
        NUMS[2] = 2;
        NUMS[3] = 5;
    }

    public int numTrees(int n) {
        for (int i = 4; i <= n; i++) {
            if (NUMS[i] == 0) {
                calc(i);
            }
        }
        return NUMS[n];
    }

    private void calc(int n) {
        int m = n - 1;
        int mid = n >> 1;
        for (int i = 0; i < mid; i++) {
            NUMS[n] += 2 * (NUMS[i] * NUMS[m - i]);
        }
        if ((m & 1) == 0) {
            NUMS[n] += NUMS[mid] * NUMS[mid];
        }
    }

}
