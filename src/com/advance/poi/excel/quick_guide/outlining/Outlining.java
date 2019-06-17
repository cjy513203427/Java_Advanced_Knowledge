package com.advance.poi.excel.quick_guide.outlining;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 16:50
 * @Description:大纲
 */
public class Outlining {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        //对行分组
        sheet1.groupRow( 5, 14 );
        sheet1.groupRow( 7, 14 );
        sheet1.groupRow( 16, 19 );

        //对列分组
        sheet1.groupColumn( 4, 7 );
        sheet1.groupColumn( 9, 12 );
        sheet1.groupColumn( 10, 11 );

        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\outlining\\workbook.xls")) {
            wb.write(fileOut);
        }
    }
}