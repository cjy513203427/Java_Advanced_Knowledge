package com.advance.poi.excel.quick_guide.working_with_borders;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 14:30
 * @Description:
 */
public class WorkingWithBorders {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(1);

        Cell cell = row.createCell(1);
        cell.setCellValue(4);

        //创建一个CellStyle，设置Border的属性
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop(BorderStyle.MEDIUM_DASHED);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style);

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("src/com/advance/poi/excel/quick_guide/working_with_borders/workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}