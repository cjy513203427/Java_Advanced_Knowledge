package com.advance.poi.excel.quick_guide.creating_date_cells;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/5 17:43
 * @Description:在单元格里创建格式化日期
 */
public class CreatingDateCells {
    public static void main(String[] args) {
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        //创建行，索引从0开始
        Row row = sheet.createRow(0);

        //创建单元格，这里第一个单元格的日期没有格式化
        Cell cell = row.createCell(0);
        cell.setCellValue(new Date());

        //创建一个CellStyle，设置日期格式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        //Calendar.getInstance()的结果和new Date()一致
        cell = row.createCell(2);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("src/com/advance/poi/excel/quick_guide/creating_date_cells/workbook.xls")) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}