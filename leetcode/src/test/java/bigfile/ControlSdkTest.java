package bigfile;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

/**
 * @author xuzhou
 * @since 2022/12/8 16:24
 */
public class ControlSdkTest {

    //    private final BigExcelWriter bigWriter = ExcelUtil.getBigWriter("C:\\Users\\69157\\Downloads\\res.xlsx");
    private final BufferedWriter writer = FileUtil.getWriter("C:\\Users\\69157\\Downloads\\res.txt", StandardCharsets.UTF_8, true);

    @Test
    public void test() {
        try {
            String file = "C:\\Users\\69157\\Desktop\\pay-cashier-center.json";
            ElkLogSplitter.getSplitsWithBigFile(file, this::getPayDTO, false, this::appendFile);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        for (PayDTO payDTO : payDTOList) {
//            log.info("{}", payDTO);
//        }
    }

    private void appendFile(PayDTO payDTO) {
//        bigWriter.writeRow(payDTO, false);
        try {
            writer.write(payDTO.getOutTradeNo());
            writer.write("|");
            writer.write(payDTO.getOrderNo());
            writer.write("|");
            writer.write(payDTO.getControlSDK());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PayDTO getPayDTO(String content) {
        JSONObject jsonObject = JSON.parseObject(content);
        JSONObject source = jsonObject.getJSONObject("_source");
        String message = source.getString("message");
        String outTradeNo = ElkLogSplitter.getSpiltStr(message, "outTradeNo=\\w+,", "outTradeNo=", ",");
        String orderNo = ElkLogSplitter.getSpiltStr(message, "orderNo=\\w+,", "orderNo=", ",");
        String controlSDK = ElkLogSplitter.getSpiltStr(message, "controlSDK=\\{.*\\}, creditPayDto", "controlSDK=", ", creditPayDto");
        if (StrUtil.isNotBlank(controlSDK)) {
            controlSDK = controlSDK.replaceAll("\"\"", "\"");
        }
        PayDTO payDTO = new PayDTO();
        payDTO.setOutTradeNo(outTradeNo);
        payDTO.setOrderNo(orderNo);
        payDTO.setControlSDK(controlSDK);
        return payDTO;
    }
}
