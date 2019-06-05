package com.advance.poi.excel.quick_guide.new_work_book;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/5 16:46
 * @Description:创建workbook
 * workbook 是一个excel文件，称为一个“工作簿”
 *
 */
public class NewWorkbook {


    public static void main(String[] args) throws IOException {
        //打不开，这是一个没有单元格的Excel
        Workbook wb = new XSSFWorkbook();
        OutputStream out = new FileOutputStream("src/com/advance/poi/excel/quick_guide/new_work_book/workbook.xlsx");
        wb.write(out);
    }
}
