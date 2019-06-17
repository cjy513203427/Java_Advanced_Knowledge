package com.advance.poi.excel.quick_guide.reading_and_rewriting_workbooks;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/10 09:19
 * @Description:读取excel修改excel中的值
 */
public class readAndRewriteWorkbook {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        try (InputStream inp = new FileInputStream("src\\com\\advance\\poi\\excel\\quick_guide\\reading_and_rewriting_workbooks\\workbook.xls")) {
            //InputStream inp = new FileInputStream("workbook.xlsx");

            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(2);
            Cell cell = row.getCell(3);
            if (cell == null)
                cell = row.createCell(3);
            cell.setCellType(CellType.STRING);
            cell.setCellValue("a test111");

            // Write the output to a file
            try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\reading_and_rewriting_workbooks\\workbook.xls")) {
                wb.write(fileOut);
            }
        }

    }
}