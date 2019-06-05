package com.advance.poi.excel.quick_guide.creating_cells;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/5 17:25
 * @Description:创建单元格
 */
public class CreatingCells {
    public static void main(String[] args) {
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        //创建行，索引从0开始
        Row row = sheet.createRow(0);
        //创建单元格，索引从0开始
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        //也可以通过row调用createCell方法创建单元格.
        row.createCell(1).setCellValue(1.2);
        //XSSFWorkbook会报错
        //row.createCell(1).setCellValue("测试");
        //createRichTextString支持HSSFWorkbook和XSSFWorkbook
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        try (OutputStream out = new FileOutputStream("src/com/advance/poi/excel/quick_guide/creating_cells/workbook.xls")) {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}