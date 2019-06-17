package com.advance.poi.excel.quick_guide.using_newlines_in_cells;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/11 17:09
 * @Description:
 */
public class UsingNewlines {
    public static void main(String[] args) throws IOException {
        Workbook wb = new XSSFWorkbook();   //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet();

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(2);
        cell.setCellValue("创建一个\n 新的行");

        //wrap=true则可以设置新行
        CellStyle cs = wb.createCellStyle();
        cs.setWrapText(true);
        cell.setCellStyle(cs);

        //增加行高来适应两行的text
        row.setHeightInPoints((3*sheet.getDefaultRowHeightInPoints()));

        //调整列宽适应内容
        sheet.autoSizeColumn(3);

        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\using_newlines_in_cells\\ooxml-newlines.xlsx")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}