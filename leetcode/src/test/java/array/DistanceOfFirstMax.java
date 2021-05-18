package array;

import java.util.Stack;

public class DistanceOfFirstMax {

    /**
     * 找出数组中每个数离他最近的最大的数的索引距离 例如：[2,3,3,4,5,7,3,2,8] 返回：[1,2,1,1,1,3,2,1,0]
     *
     * @param score
     * @return
     */
    public static int[] solve(int[] score) {
        if (score == null || score.length == 0) {
            return null;
        }
        int N = score.length;
        if (N == 1) {
            return new int[]{0};
        }
        int[] res = new int[N];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty()) {
                Integer peek = stack.peek();
                if (score[peek] < score[i]) {
                    res[stack.pop()] = i - peek;
                } else {
                    break;
                }
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] res = solve(new int[]{5, 8, 9, 10, 1, 1, 5, 2, 12});
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + ",");
        }
    }
}