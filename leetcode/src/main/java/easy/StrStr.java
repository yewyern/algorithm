package easy;

class StrStr {

    //给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
    //
    //示例 1:
    //
    //输入: haystack = "hello", needle = "ll"
    //输出: 2
    //示例 2:
    //
    //输入: haystack = "aaaaa", needle = "bba"
    //输出: -1
    //说明:
    //
    //当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
    //
    //对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        char[] cs1 = haystack.toCharArray();
        char[] cs2 = needle.toCharArray();
        int targetCount = cs2.length;
        int max = cs1.length - targetCount;
        char first = cs2[0];
        for (int i = 0; i <= max; i++) {
            if (cs1[i] != first) {
                while (++i <= max && cs1[i] != first) {
                }
            }
            if (i <= max) {
                int j = 1;
                for (; j < targetCount && cs1[i + j] == cs2[j]; j++) {
                }
                if (j == targetCount) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StrStr strStr = new StrStr();
        for (int i = 0; i < 1000; i++) {
            strStr.test("hello", "ll");
            strStr.test("aaaaa", "bba");
        }
    }

    public void test(String haystack, String needle) {
        long l = System.nanoTime();
        int res = strStr(haystack, needle);
        long l2 = System.nanoTime();
        System.out.println("strStr(" + haystack + ", " + needle + ") = " + res + ", time spent = " + (l2 - l));
    }
}