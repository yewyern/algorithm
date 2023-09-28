package easy;

/**
 * <a href="https://leetcode.cn/problems/excel-sheet-column-title/">168. Excel表列名称</a>
 */
public class ConvertToExcelTitle {

    public static String convertToTitle(int n) {
        char[] cs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.insert(0, cs[n % 26]);
            n = n / 26;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("1 = " + convertToTitle(1));
        System.out.println("28 = " + convertToTitle(28));
        System.out.println("701 = " + convertToTitle(701));
    }
}