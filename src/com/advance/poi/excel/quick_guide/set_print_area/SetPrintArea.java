package com.advance.poi.excel.quick_guide.set_print_area;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 09:01
 * @Description:设置打印区域
 */
public class SetPrintArea {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");
        //sets the print area for the first sheet
        wb.setPrintArea(0, "$A$1:$C$2");

        //Alternatively:
        //打印区域为0行0列到0行1列
        /*wb.setPrintArea(
                0, //sheet index
                0, //start column
                1, //end column
                0, //start row
                0  //end row
        );*/

        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\set_print_area\\workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}