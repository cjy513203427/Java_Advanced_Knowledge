package com.advance.poi.excel.quick_guide.working_with_fonts;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 16:58
 * @Description:
 */
public class WorkingWithFonts {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(1);

        // 创建Font，设置属性
        Font font = wb.createFont();
        //set the font height
        font.setFontHeightInPoints((short)24);
        font.setFontName("Courier New");
        //斜体
        font.setItalic(true);
        //删除线
        font.setStrikeout(true);

        // 创建CellStyle，放入font
        CellStyle style = wb.createCellStyle();
        style.setFont(font);

        Cell cell = row.createCell(1);
        cell.setCellValue("This is a test of fonts");
        cell.setCellStyle(style);

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\working_with_fonts\\workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}