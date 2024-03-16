package str;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/text-justification">68. 文本左右对齐</a>
 * @author xuzhou
 * @since 2023/11/20 13:41
 */
public class TextJustificationTest {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        int start = 0;
        while (start < n) {
            start = process(words, maxWidth, start, ans);
        }
        return ans;
    }

    private int process(String[] words, int maxWidth, int start, List<String> ans) {
        int n = words.length;
        int width = words[start].length();// 标准宽度
        int end = start + 1;
        while (end < n) {
            int nextWidth = width + words[end].length() + 1;
            if (nextWidth > maxWidth) {
                break;
            }
            width = nextWidth;
            end++;
        }
        ans.add(concat(words, start, end, maxWidth, width));
        return end;
    }

    private String concat(String[] words, int start, int end, int maxWidth, int width) {
        int n = words.length;
        StringBuilder sb = new StringBuilder();
        sb.append(words[start]);
        int diff = maxWidth - width;
        if (end == n || maxWidth == width) {
            for (int i = start + 1; i < end; i++) {
                sb.append(' ').append(words[i]);
            }
        } else {
            // 需要补的空格数
            for (int i = start + 1; i < end; i++) {
                sb.append(' ');
                int space = diff / (end - i);
                space = space * (end - i) != diff ? space + 1 : space;
                for (int j = 0; j < space; j++) {
                    sb.append(' ');
                }
                diff -= space;
                sb.append(words[i]);
            }
        }
        for (int j = 0; j < diff; j++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
    }
}
