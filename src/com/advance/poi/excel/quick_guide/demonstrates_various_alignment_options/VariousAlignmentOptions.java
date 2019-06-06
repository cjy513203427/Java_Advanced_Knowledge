package com.advance.poi.excel.quick_guide.demonstrates_various_alignment_options;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 11:29
 * @Description:对单元格进行布局
 */
public class VariousAlignmentOptions {
    public static void main(String[] args) throws Exception {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();

        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);
        row.setHeightInPoints(30);
        //HorizontalAlignment.CENTER：中央
        //HorizontalAlignment.BOTTOM：底部
        //HorizontalAlignment.FILL：填充
        //HorizontalAlignment.LEFT：左边靠齐
        //HorizontalAlignment.RIGHT：右边靠齐
        createCell(wb, row, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        createCell(wb, row, 1, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
        createCell(wb, row, 2, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
        createCell(wb, row, 3, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        createCell(wb, row, 4, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("src/com/advance/poi/excel/quick_guide/demonstrates_various_alignment_options/xssf-align.xlsx")) {
            wb.write(fileOut);
        }

        wb.close();
    }

    /**
     * 创建一个单元格并指定一种对齐方式
     *
     * @param wb     the workbook
     * @param row    行
     * @param column 列
     * @param halign 水平布局
     * @param valign 垂直布局
     */
    private static void createCell(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign) {
        Cell cell = row.createCell(column);
        cell.setCellValue("Align It");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }

}