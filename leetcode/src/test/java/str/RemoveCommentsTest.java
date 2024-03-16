package str;

import java.util.ArrayList;
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
        List<String> ans = new ArrayList<>(source.length);
        StringBuilder sb = new StringBuilder();
        boolean inBlockComment = false;
        for (String s : source) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            for (int i = 0; i < n; i++) {
                if (inBlockComment) {
                    // 在块注释里面找到一个*/可以结束注释
                    if (cs[i] == '*' && i < n - 1 && cs[i + 1] == '/') {
                        inBlockComment = false;
                        i++;
                    }
                } else {
                    if (cs[i] == '/' && i < n - 1) {
                        if (cs[i + 1] == '/') {
                            // 行注释，直接跳出
                            break;
                        } else if (cs[i + 1] == '*') {
                            // 块注释开始
                            inBlockComment = true;
                            i++;// 多跳一个位置，避免/*/这种
                            continue;
                        }
                    }
                    sb.append(cs[i]);
                }
            }
            if (!inBlockComment && sb.length() > 0) {
                ans.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return ans;
    }

    public List<String> removeComments1(String[] source) {
        List<String> ans = new ArrayList<>(source.length);
        String[] last = new String[] {"", "1"};
        StringBuilder sb = null;
        for (String s : source) {
            String[] cur = removeComments(s, last[1].isEmpty());
            if (cur[1].isEmpty() || last[1].isEmpty()) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                // 块注释需要拼接
                sb.append(cur[0]);
            } else {
                if (sb != null && sb.length() > 0) {
                    ans.add(sb.toString());
                }
                if (!cur[0].isEmpty()) {
                    ans.add(cur[0]);
                }
                sb = null;
            }
            last = cur;
        }
        if (sb != null && sb.length() > 0) {
            ans.add(sb.toString());
        }
        return ans;
    }

    private String[] removeComments(String s, boolean inBlockComment) {
        int n = s.length();
        char[] source = s.toCharArray();
        char[] target = new char[n];
        int len = 0; // 结果值长度
        int currentIndex = 0;
        int blockCommentEnd = s.indexOf("*/");
        if (inBlockComment) {
            if (blockCommentEnd < 0) {
                return new String[] {"", ""};
            }
            currentIndex = blockCommentEnd + 2;
            inBlockComment = false;
        }
        int fromIndex = currentIndex; // 源字符串当前索引
        while (fromIndex < n) {
            int slashStart = s.indexOf("/", fromIndex);
            if (slashStart < 0 || slashStart >= n - 1) {
                System.arraycopy(source, currentIndex, target, len, n - currentIndex);
                len += n - currentIndex;
                break;
            } else if (source[slashStart + 1] == '/') {
                // 行注释
                System.arraycopy(source, currentIndex, target, len, slashStart - currentIndex);
                len += slashStart - currentIndex;
                break;
            } else if (source[slashStart + 1] == '*') {
                // 块注释
                System.arraycopy(source, currentIndex, target, len, slashStart - currentIndex);
                len += slashStart - currentIndex;
                int commentEnd = s.indexOf("*/", slashStart + 2);
                if (commentEnd > 0) {
                    currentIndex = commentEnd + 2;
                    fromIndex = currentIndex;
                } else {
                    inBlockComment = true;
                    break;
                }
            } else {
                fromIndex = slashStart + 1;
            }
        }
        return new String[] {new String(target, 0, len), inBlockComment ? "" : "1"};
    }

    public List<String> removeComments2(String[] source) {
        // 先用\n拼接，然后删除注释，注意:行注释不能删除结尾的\n
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
