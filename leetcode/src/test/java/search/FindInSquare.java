package search;

import java.util.Arrays;
import utils.RandomArray;
import utils.RandomUtils;

/**
 * 平方
 *
 * @author xuzhou
 * @since 2021/5/6 13:37
 */
public class FindInSquare {

    public static void main(String[] args) {
        int level = 40;
        for (int i = 0; i < 10000; i++) {
            int[][] square = new int[10][];
            for (int j = 0; j < square.length; j++) {
                square[j] = RandomArray.generateNoRepeatSortedArray(10, level * j, level * (j + 1));
            }
//            print(square);
            int target = RandomUtils.nextInt(200);
            int[] inSquare = findInSquare(square, target);
            int binarySearch = Arrays.binarySearch(square[target / level], target);
            if (binarySearch < 0 && (inSquare[0] != -1 || inSquare[1] != -1)) {
                print(square);
                System.out.println("inSquare = " + Arrays.toString(inSquare));
            } else if (binarySearch >= 0 && (inSquare[0] != target / level || binarySearch != inSquare[1])) {
                print(square);
                System.out.println("target = " + target);
                System.out.println("target / level = " + target / level);
                System.out.println("binarySearch = " + binarySearch);
                System.out.println("inSquare = " + Arrays.toString(inSquare));
            }
        }
    }

    private static void print(int[][] square) {
        System.out.println("square = [");
        for (int[] nums : square) {
            System.out.println(Arrays.toString(nums) + ",");
        }
        System.out.println("]");
    }

    public static int[] findInSquare(int[][] square, int target) {
        if (square == null || square.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0, end = square.length;
        while (start < end) {
            int mid = (end + start) >> 1;
            if (square[mid][0] == target) {
                return new int[]{mid, 0};
            }
            if (square[mid][0] > target) {
                end = mid;
            } else if (start != mid) {
                start = mid;
            } else {
                break;
            }
        }
        int search = Arrays.binarySearch(square[start], target);
        if (search < 0) {
            return new int[]{-1, -1};
        }
        return new int[]{start, search};
    }
}
