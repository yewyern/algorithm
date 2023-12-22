package str;

/**
 * <a href="https://leetcode.cn/problems/simplify-path/">71. 简化路径</a>
 * @author xuzhou
 * @since 2023/12/22 18:08
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        String[] dirs = path.split("/");
        int n = dirs.length;
        String[] realPath = new String[n];
        int size = 0;
        for (String dir : dirs) {
            if (dir.equals(".") || dir.isEmpty()) {
                continue;
            }
            if (dir.equals("..")) {
                if (size > 0) {
                    size--;
                }
            } else {
                realPath[size++] = dir;
            }
        }
        if (size == 0) {
            return "/";
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < size; i++) {
            ans.append("/").append(realPath[i]);
        }
        return ans.toString();
    }
}
