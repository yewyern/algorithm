package str;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/remove-comments">722. 删除注释</a>
 *
 * @author xuzhou
 * @since 2023/9/8 17:55
 */
public class RemoveCommentsTest {

    public List<String> removeComments(String[] source) {
        String s = join(source);
        return split(removeComments(s));
    }

    private String removeComments(String s) {
        int n = s.length();
        char[] source = s.toCharArray();
        char[] target = new char[n];
        int len = 0; // 结果值长度
        int currentIndex = 0; // 源字符串当前索引
        int fromIndex = 0; // 源字符串当前索引
        while (fromIndex < n) {
            int slashStart = s.indexOf("/", fromIndex);
            if (slashStart < 0 || slashStart >= n - 1) {
                System.arraycopy(source, currentIndex, target, len, n - currentIndex);
                len += n - currentIndex;
                break;
            } else if (source[slashStart + 1] == '/') {
                // 行注释
                int commentEnd = s.indexOf("\n", slashStart + 2);
                System.arraycopy(source, currentIndex, target, len, slashStart - currentIndex);
                len += slashStart - currentIndex;
                currentIndex = commentEnd;
                fromIndex = currentIndex;
            } else if (source[slashStart + 1] == '*') {
                // 块注释
                int commentEnd = s.indexOf("*/", slashStart + 2);
                System.arraycopy(source, currentIndex, target, len, slashStart - currentIndex);
                len += slashStart - currentIndex;
                currentIndex = commentEnd + 2;
                fromIndex = currentIndex;
            } else {
                fromIndex = slashStart + 1;
            }
        }
        return new String(target, 0, len);
    }

    private String removeComments0(String s) {
        int n = s.length();
        char[] source = s.toCharArray();
        StringBuilder target = new StringBuilder();
        int currentIndex = 0; // 源字符串当前索引
        int fromIndex = 0; // 源字符串当前索引
        while (fromIndex < n) {
            int slashStart = s.indexOf("/", fromIndex);
            if (slashStart < 0 || slashStart >= n - 1) {
                target.append(s, currentIndex, n);
                break;
            } else if (source[slashStart + 1] == '/') {
                // 行注释
                int commentEnd = s.indexOf("\n", slashStart + 2);
                target.append(s, currentIndex, slashStart);
                currentIndex = commentEnd;
                fromIndex = currentIndex;
            } else if (source[slashStart + 1] == '*') {
                // 块注释
                int commentEnd = s.indexOf("*/", slashStart + 2);
                target.append(s, currentIndex, slashStart);
                currentIndex = commentEnd + 2;
                fromIndex = currentIndex;
            } else {
                fromIndex = slashStart + 1;
            }
        }
        return target.toString();
    }

    private String join(String[] source) {
        StringBuilder sb = new StringBuilder();
        for (String s : source) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

    private List<String> split(String s) {
        return Arrays.stream(s.split("\n")).filter(s1 -> !s1.isEmpty()).collect(Collectors.toList());
    }
}
