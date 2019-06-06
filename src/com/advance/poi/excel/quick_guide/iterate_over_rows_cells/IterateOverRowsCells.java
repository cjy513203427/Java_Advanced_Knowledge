package com.advance.poi.excel.quick_guide.iterate_over_rows_cells;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 14:49
 * @Description:遍历单元格
 */
public class IterateOverRowsCells {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        try (InputStream inp = new FileInputStream("src/com/advance/poi/excel/quick_guide/iterate_over_rows_cells/test.xlsx")) {

            Workbook wb = WorkbookFactory.create(inp);
            for (Sheet sheet : wb ) {
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        //获取cell的值
                        //单元格为空会跳过，不输出
                        System.out.println(cell);
                    }
                }
            }

        }
    }
}