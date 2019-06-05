package com.advance.poi.excel.quick_guide.new_sheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/5 17:02
 * @Description:创建sheet
 * sheet就是excel中的一个工作表
 */
public class NewSheet {


    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();  // or new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("second sheet");

        //sheet名称不能超过31个字符
        // 不能包含以下字符:
        // 0x0000
        // 0x0003
        // (:)
        // (\)
        // (*)
        // (?)
        // (/)
        // ([)
        // (])

        // 你可以使用org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
        // 该工具会将违法字符转换成空字符串
        String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
        Sheet sheet3 = wb.createSheet(safeName);

        //sheet已经设置到了workbook里面
        for(int i=0;i<3;i++) {
            System.out.println(wb.getSheetName(i));
        }

        //打不开，这是一个没有单元格的Excel
        OutputStream out = new FileOutputStream("src/com/advance/poi/excel/quick_guide/new_sheet/workbook.xlsx");
        wb.write(out);
    }
}