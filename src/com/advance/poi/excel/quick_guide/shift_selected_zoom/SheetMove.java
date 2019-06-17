package com.advance.poi.excel.quick_guide.shift_selected_zoom;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 15:04
 * @Description:
 */
public class SheetMove {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("row sheet");

        // Create various cells and rows for spreadsheet.
        //创建行，索引从0开始
        Row row = sheet.createRow(5);
        //也可以通过row调用createCell方法创建单元格.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue("fua");
        row.createCell(3).setCellValue("string");
        //将5到10行内容移动到0到5行
        sheet.shiftRows(5, 10, -5);
        //选择当前sheet
        sheet.setSelected(true);
        //缩放，75%大小
        sheet.setZoom(75);
        // Write out the workbook
        try (OutputStream fileOut = new FileOutputStream( "src\\com\\advance\\poi\\excel\\quick_guide\\shift_selected_zoom\\workbook.xls" )) {
            wb.write( fileOut );
        }

        wb.close();
    }
}