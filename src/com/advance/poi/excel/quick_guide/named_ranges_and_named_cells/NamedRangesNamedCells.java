package com.advance.poi.excel.quick_guide.named_ranges_and_named_cells;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/18 09:34
 * @Description:未知功能
 */
public class NamedRangesNamedCells {
    public static void main(String[] args) throws IOException {
        // setup code
        String sname = "TestSheet", cname = "TestName", cvalue = "TestVal";
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(sname);
        sheet.createRow(0).createCell(0).setCellValue(cvalue);

        sheet.createRow(2).createCell(8).setCellValue(1);
        sheet.createRow(3).createCell(8).setCellValue(2);
        sheet.createRow(4).createCell(8).setCellValue(3);
        sheet.createRow(5).createCell(8).setCellValue(3);
        sheet.createRow(6).createCell(8).setCellValue(3);
        sheet.createRow(7).createCell(8).setCellValue(3);
        sheet.createRow(8).createCell(8).setCellValue(3);
        // 1. create named range for a single cell using areareference
        Name namedCell = wb.createName();
        namedCell.setNameName(cname + "1");
        String reference = sname+"!$A$1:$A$1"; // area reference
        namedCell.setRefersToFormula(reference);

        // 2. create named range for a single cell using cellreference
        Name namedCel2 = wb.createName();
        namedCel2.setNameName(cname + "2");
        reference = sname+"!$A$1"; // cell reference
        namedCel2.setRefersToFormula(reference);

        // 3. create named range for an area using AreaReference
        Name namedCel3 = wb.createName();
        namedCel3.setNameName(cname + "3");
        reference = sname+"!$A$1:$C$5"; // area reference
        namedCel3.setRefersToFormula(reference);

        // 4. create named formula
        Name namedCel4 = wb.createName();
        namedCel4.setNameName("my_sum");
        namedCel4.setRefersToFormula("SUM(" + sname + "!$I$2:$I$6)");

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\named_ranges_and_named_cells\\workbook.xls")) {
            wb.write(fileOut);
        }

    }
}