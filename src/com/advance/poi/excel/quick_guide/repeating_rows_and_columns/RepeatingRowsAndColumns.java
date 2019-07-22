package com.advance.poi.excel.quick_guide.repeating_rows_and_columns;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 15:41
 * @Description: 未知功能
 * It's possible to set up repeating rows and columns in your printouts by using the setRepeatingRows() and setRepeatingColumns() methods in the Sheet class.
 * These methods expect a CellRangeAddress parameter which specifies the range for the rows or columns to repeat. For setRepeatingRows(), it should specify a range of rows to repeat,
 * with the column part spanning all columns. For setRepeatingColums(), it should specify a range of columns to repeat, with the row part spanning all rows. If the parameter is null,
 * the repeating rows or columns will be removed.
 */
public class RepeatingRowsAndColumns {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();           // or new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Sheet1");
        Sheet sheet2 = wb.createSheet("Sheet2");

        sheet1.createRow(4).createCell(0).setCellValue("ThreadDomain42");
        sheet1.createRow(5).createCell(0).setCellValue("ssda");
        // Set the rows to repeat from row 4 to 5 on the first sheet.
        sheet1.setRepeatingRows(CellRangeAddress.valueOf("4:5"));
        Row row1 = sheet2.createRow(1);
        row1.createCell(0).setCellValue("ThreadDomain42");
        row1.createCell(1).setCellValue("22");
        // Set the columns to repeat from column A to C on the second sheet
        sheet2.setRepeatingColumns(CellRangeAddress.valueOf("A:C"));

        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\repeating_rows_and_columns\\workbook.xls")) {
            wb.write(fileOut);
        }

    }
}