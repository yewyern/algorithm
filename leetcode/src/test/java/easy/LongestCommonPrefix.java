package easy;


public class LongestCommonPrefix {


    /**
     * <p>编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * <p>如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * <p>示例 1:
     * <p>
     * <p>输入: ["flower","flow","flight"]
     * <p>输出: "fl"
     * <p>示例 2:
     * <p>
     * <p>输入: ["dog","racecar","car"]
     * <p>输出: ""
     * <p>解释: 输入不存在公共前缀。
     * <p>说明:
     * <p>
     * <p>所有输入只包含小写字母 a-z 。
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String... strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        char[] cs = new char[strs[0].length()];
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            cs[i] = c;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i) {
                    return strs[j];
                }
                if (strs[j].charAt(i) != c) {
                    /**
                     * substring效率奇差无比，所以不用 strs[j].substring(0, i)
                     */
                    return String.valueOf(cs, 0, i);
                }
            }
        }
        
        return strs[0];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix("flower", "flow", "flight"));
    }
}