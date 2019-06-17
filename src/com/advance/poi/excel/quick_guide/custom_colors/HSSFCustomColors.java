package com.advance.poi.excel.quick_guide.custom_colors;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 17:11
 * @Description:xls设置颜色
 */
public class HSSFCustomColors {
    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("Default Palette");

        //在palette上使用一些颜色
        //红色文本绿黄色背景
        HSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.LIME.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.RED.index);
        style.setFont(font);

        cell.setCellStyle(style);

        //save默认的palette
        try (OutputStream out = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\custom_colors\\default_palette.xls")) {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 我们现在把红色和绿黄色替换成其他好看的颜色
        cell.setCellValue("Modified Palette");

        //creating a custom palette for the workbook
        HSSFPalette palette = wb.getCustomPalette();

        //用freebsd.org red替换直男红
        palette.setColorAtIndex(HSSFColor.RED.index,
                (byte) 153,  //RGB red (0-255)
                (byte) 0,    //RGB green
                (byte) 0     //RGB blue
        );

        //用freebsd.org gold替换绿黄色
        palette.setColorAtIndex(HSSFColor.LIME.index, (byte) 255, (byte) 204, (byte) 102);

        //保存修改过的palette
        //标记我们之前用的红和绿黄色
        //看看新颜色的效果
        try (OutputStream out = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\custom_colors\\modified_palette.xls")) {
            wb.write(out);
        }

    }
}