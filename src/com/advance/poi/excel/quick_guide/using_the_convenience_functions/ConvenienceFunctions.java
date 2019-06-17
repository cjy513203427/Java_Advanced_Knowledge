package com.advance.poi.excel.quick_guide.using_the_convenience_functions;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 14:45
 * @Description:合并单元格，设置边框颜色；CellUtil创建Cell
 */
public class ConvenienceFunctions {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();  // or new XSSFWorkbook()
        Sheet sheet1 = wb.createSheet( "new sheet" );

        // 创建一个合并区域
        Row row = sheet1.createRow( 1 );
        Row row2 = sheet1.createRow( 2 );
        Cell cell = row.createCell( 1 );
        cell.setCellValue( "This is a test of merging" );
        CellRangeAddress region = CellRangeAddress.valueOf("B2:E5");
        sheet1.addMergedRegion( region );

        // 设置边框和边框颜色
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM_DASHED, region, sheet1);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM_DASHED, region, sheet1);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM_DASHED, region, sheet1);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM_DASHED, region, sheet1);
        RegionUtil.setBottomBorderColor(IndexedColors.AQUA.getIndex(), region, sheet1);
        RegionUtil.setTopBorderColor(IndexedColors.AQUA.getIndex(), region, sheet1);
        RegionUtil.setLeftBorderColor(IndexedColors.AQUA.getIndex(), region, sheet1);
        RegionUtil.setRightBorderColor(IndexedColors.AQUA.getIndex(), region, sheet1);

        // Shows some usages of HSSFCellUtil
        CellStyle style = wb.createCellStyle();
        style.setIndention((short)4);
        CellUtil.createCell(row, 8, "This is the value of the cell", style);
        Cell cell2 = CellUtil.createCell( row2, 8, "This is the value of the cell");
        CellUtil.setAlignment(cell2, HorizontalAlignment.CENTER);

        // Write out the workbook
        try (OutputStream fileOut = new FileOutputStream( "src\\com\\advance\\poi\\excel\\quick_guide\\using_the_convenience_functions\\workbook.xls" )) {
            wb.write( fileOut );
        }

        wb.close();
    }
}