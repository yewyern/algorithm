package bigfile;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhou
 * @since 2022/9/15 15:10
 */
public class FileWriteUtils {

    public static <T> void writeFile(List<T> list, String filename) {
        List<Map<String, Object>> mapList = CollUtil.map(list, t -> BeanUtil.beanToMap(t, false, false), true);
        try (BigExcelWriter bigWriter = ExcelUtil.getBigWriter(filename)) {
            bigWriter.write(mapList, true);
        } catch (Exception e) {
            System.err.println("导出文件失败," + e.getMessage());
            e.printStackTrace();
        }
    }

    public static <T> void writeFile(T t, String filename) {
        try (BigExcelWriter bigWriter = ExcelUtil.getBigWriter(filename)) {
            bigWriter.writeRow(t, false);
        } catch (Exception e) {
            System.err.println("导出文件失败," + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeExcel(String resultFile, List<Map<String, Object>> resultList) {
        ExcelWriter writer = ExcelUtil.getWriter(resultFile);
        writer.write(resultList);
        writer.close();
    }
}
