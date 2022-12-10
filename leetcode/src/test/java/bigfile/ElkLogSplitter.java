package bigfile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author xuzhou
 * @since 2022/12/8 15:53
 */
public class ElkLogSplitter {

    private static final Map<String, Pattern> patternMap = new HashMap<>();

    private static Pattern getPattern(String pattern) {
        Pattern p = patternMap.get(pattern);
        if (p == null) {
            p = Pattern.compile(pattern);
            patternMap.put(pattern, p);
        }
        return p;
    }

    public static <T> List<T> getSplits(String filename, Function<String, T> convertor, boolean hasHeader) {
        return getSplits(filename, convertor, hasHeader ? 1 : 0);
    }

    public static <T> List<T> getSplits(String filename, Function<String, T> convertor, int startRow) {
        BufferedReader reader = FileUtil.getUtf8Reader(filename);
        List<String> list = reader.lines().skip(startRow).collect(Collectors.toList());
        return getSplits(list, convertor);
    }

    public static <T> void getSplitsWithBigFile(String filename, Function<String, T> convertor, boolean hasHeader, Consumer<T> consumer) {
        getSplitsWithBigFile(filename, convertor, hasHeader ? 1 : 0, consumer);
    }

    public static <T> void getSplitsWithBigFile(String filename, Function<String, T> convertor, int startRow, Consumer<T> consumer) {
        BufferedReader reader = FileUtil.getUtf8Reader(filename);
        reader.lines().skip(startRow).map(s -> getSplits(s, convertor)).forEach(consumer);
    }

    public static <T> List<T> getSplits(List<String> lines, Function<String, T> convertor) {
        return CollUtil.map(lines, convertor, true);
    }

    public static <T> T getSplits(String line, Function<String, T> convertor) {
        return convertor.apply(line);
    }

    public static String getSpiltStr(String content, String pattern, String prefix, String suffix) {
        Pattern p = getPattern(pattern);
        Matcher matcher = p.matcher(content);
        if (matcher.find()) {
            try {
                String group = matcher.group();
                return group.substring(prefix.length(), group.length() - suffix.length());
            } catch (Throwable e) {
                System.out.println(content);
                System.out.println(pattern);
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return null;
    }

}
