package com.advance.poi.excel.quick_guide.iterate_over_rows_cells;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 15:00
 * @Description:遍历单元格，可以自定义规则筛选空行
 */
public class IterateOverCellsWithCondition {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        try (InputStream inp = new FileInputStream("src/com/advance/poi/excel/quick_guide/iterate_over_rows_cells/test.xlsx")) {

            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);

            //设置行起始喝结束
            int rowStart = Math.min(15, sheet.getFirstRowNum());
            int rowEnd = Math.max(0, sheet.getLastRowNum());

            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row r = sheet.getRow(rowNum);
                if (r == null) {
                    //整行都为空
                    continue;
                }

                int lastColumn = Math.max(r.getLastCellNum(), 0);

                for (int cn = 0; cn < lastColumn; cn++) {
                    //MissingCellPolicy.RETURN_BLANK_AS_NULL：如果是空白返回空
                    Cell c = r.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (c == null) {
                        //cell为空，需要做的处理写这里
                        System.out.println("null");
                    } else {
                        //cell不为空，需要做的处理写这里
                        System.out.println(c);
                    }
                }
            }
        }

    }
}