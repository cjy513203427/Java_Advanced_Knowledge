package com.advance.poi.excel.quick_guide.set_page_numbers_on_footer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 09:08
 * @Description:设置页脚
 */
public class SetPageNumbersOnFooter {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook(); // or new XSSFWorkbook();
        Sheet sheet = wb.createSheet("format sheet");
        Footer footer = sheet.getFooter();

        footer.setRight( "Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages() );



        // Create various cells and rows for spreadsheet.

        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\set_page_numbers_on_footer\\workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}