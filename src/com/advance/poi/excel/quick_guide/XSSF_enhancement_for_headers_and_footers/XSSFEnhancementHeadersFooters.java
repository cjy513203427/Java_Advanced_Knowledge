package com.advance.poi.excel.quick_guide.XSSF_enhancement_for_headers_and_footers;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 16:28
 * @Description:
 */
public class XSSFEnhancementHeadersFooters {
    public static void main(String[] args) throws IOException {
        Workbook wb = new XSSFWorkbook();
        XSSFSheet sheet = (XSSFSheet) wb.createSheet("new sheet");
        sheet.createRow(0).createCell(0).setCellValue("iop");
        sheet.createRow(1).createCell(0).setCellValue("lkj");

        //第一页header
        Header header = sheet.getFirstHeader();
        header.setCenter("Center First Page Header");
        header.setLeft("Left First Page Header");
        header.setRight("Right First Page Header");

        //偶数页header
        Header header2 = sheet.getEvenHeader();
        header2.setCenter("Center Even Page Header");
        header2.setLeft("Left Even Page Header");
        header2.setRight("Right Even Page Header");

        //奇数页header
        Header header3 = sheet.getOddHeader();
        header3.setCenter("Center Odd Page Header");
        header3.setLeft("Left Odd Page Header");
        header3.setRight("Right Odd Page Header");

        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\XSSF_enhancement_for_headers_and_footers\\workbook.xlsx")) {
            wb.write(fileOut);
        }
    }
}