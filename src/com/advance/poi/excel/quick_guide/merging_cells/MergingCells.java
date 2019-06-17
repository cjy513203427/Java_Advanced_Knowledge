package com.advance.poi.excel.quick_guide.merging_cells;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 16:48
 * @Description:合并单元格
 */
public class MergingCells {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);


        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);

        Cell cell1 = row1.createCell(0);
        Cell cell2 = row1.createCell(1);
        Cell cell3 = row1.createCell(2);
        Cell cell4 = row2.createCell(2);
        Cell cell5 = row2.createCell(3);
        Cell cell6 =  row1.createCell(4);
        Cell cell7 = row2.createCell(4);
        Cell cell8 = row2.createCell(5);
        Cell cell9 = row2.createCell(6);
        Cell cell10 = row1.createCell(7);
        Cell cell11 = row2.createCell(7);
        Cell cell12 = row2.createCell(8);
        Cell cell13 = row2.createCell(9);

        cell1.setCellStyle(cellStyle);
        cell2.setCellStyle(cellStyle);
        cell3.setCellStyle(cellStyle);
        cell4.setCellStyle(cellStyle);
        cell5.setCellStyle(cellStyle);
        cell6.setCellStyle(cellStyle);
        cell7.setCellStyle(cellStyle);
        cell8.setCellStyle(cellStyle);
        cell9.setCellStyle(cellStyle);
        cell10.setCellStyle(cellStyle);
        cell11.setCellStyle(cellStyle);
        cell12.setCellStyle(cellStyle);
        cell13.setCellStyle(cellStyle);

        cell1.setCellValue("地市");
        cell2.setCellValue("平均值");
        cell3.setCellValue("资源系统/运维系统");
        cell4.setCellValue("站址一致率");
        cell5.setCellValue("设备一致率");
        cell6.setCellValue("资源系统/CRM系统");
        cell7.setCellValue("站址一致率");
        cell8.setCellValue("机房一致率");
        cell9.setCellValue("铁塔一致率");
        cell10.setCellValue("资源系统/财务系统");
        cell11.setCellValue("机房一致率");
        cell12.setCellValue("铁塔一致率");
        cell13.setCellValue("设备一致率");

        //（从第零行开始）第0行第0列合并到第1行第0列
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (从0行开始)
                1, //last row  (从0行开始)
                0, //first column (从0列开始)
                0  //last column  (从0列开始)
        ));
        //（从第零行开始）第0行第2列合并到第1行第2列
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (从0行开始)
                1, //last row  (从0行开始)
                1, //first column (从0列开始)
                1  //last column  (从0列开始)
        ));

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (从0行开始)
                0, //last row  (从0行开始)
                2, //first column (从0列开始)
                3  //last column  (从0列开始)
        ));

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (从0行开始)
                0, //last row  (从0行开始)
                4, //first column (从0列开始)
                6  //last column  (从0列开始)
        ));

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (从0行开始)
                0, //last row  (从0行开始)
                7, //first column (从0列开始)
                9  //last column  (从0列开始)
        ));

        //另外一种写法
        /*CellRangeAddress region = CellRangeAddress.valueOf("B2:E5");
        sheet.addMergedRegion( region );*/

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\merging_cells\\workbook.xls")) {
            wb.write(fileOut);
        }

        wb.close();
    }
}