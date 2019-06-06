package com.advance.poi.excel.quick_guide.fills_and_colors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 16:26
 * @Description:设置单元格背景色
 */
public class FileAndColors {
    public static void main(String[] args) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        //创建行，0起始
        Row row = sheet.createRow(1);

        //创建CellStyle，使用Aqua背景
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.BIG_SPOTS);
        Cell cell = row.createCell(1);
        cell.setCellValue("J");
        cell.setCellStyle(style);

        //前景色为橙色
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell = row.createCell(2);
        cell.setCellValue("X");
        cell.setCellStyle(style);

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\fills_and_colors\\workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}