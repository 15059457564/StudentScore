package com.chenhao.stuscore.Util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.chenhao.stuscore.domain.ClazzResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ch
 * @date 2021/1/3
 **/
public class ExcelUtil {
    public static void writeExcel(HttpServletResponse response, List<ClazzResult> list) throws IOException {
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        //定义工作簿对象
        WriteSheet sheet = EasyExcel.writerSheet(0,"sheet").head(ClazzResult.class).build();
        //往excel文件写入数据
        excelWriter.write(list,sheet);
        excelWriter.finish();

    }
}
