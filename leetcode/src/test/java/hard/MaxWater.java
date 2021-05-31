package hard;

/**
 * <p> 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水。
 * <p> [3,1,2,5,2,4]
 * <p> 5
 */
public class MaxWater {

    public long maxWater(int[] arr) {
        // TODO
        if (arr == null || arr.length < 3) {
            return 0;
        }
        long res = 0;
        int l = 0, c = 1, r = 2;
        while (l < arr.length && r < arr.length && l < r) {

        }
        return res;
    }
}
