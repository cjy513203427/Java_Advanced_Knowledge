package com.advance.poi.excel.quick_guide.merging_cells;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 16:48
 * @Description:
 */
public class MergingCells {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("This is a test of merging");

        //（从第零行开始）第一行第一列合并到第一行第二列
        sheet.addMergedRegion(new CellRangeAddress(
                1, //first row (从0行开始)
                1, //last row  (从0行开始)
                1, //first column (从0列开始)
                2  //last column  (从0列开始)
        ));

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\merging_cells\\workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}