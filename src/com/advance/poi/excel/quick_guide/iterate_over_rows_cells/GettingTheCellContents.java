package com.advance.poi.excel.quick_guide.iterate_over_rows_cells;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 15:27
 * @Description:获取单元格内容
 */
public class GettingTheCellContents {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        try (InputStream inp = new FileInputStream("src/com/advance/poi/excel/quick_guide/iterate_over_rows_cells/test.xlsx")) {

            Workbook wb = WorkbookFactory.create(inp);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet1 = wb.getSheetAt(0);
            for (Row row : sheet1) {
                for (Cell cell : row) {
                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                    //获取列号行号，如“$A$1”
                    System.out.print(cellRef.formatAsString());
                    System.out.print(" - ");

                    //获取格式化的String
                    String text = formatter.formatCellValue(cell);
                    System.out.println(text);

                    // 根据单元格的数据类型获取值
                    switch (cell.getCellType()) {
                        case 0:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.println(cell.getDateCellValue());
                            } else {
                                System.out.println(cell.getNumericCellValue());
                            }
                            break;
                        case 1:
                            System.out.println(cell.getRichStringCellValue().getString());
                            break;
                        case 2:
                            System.out.println(cell.getCellFormula());
                            break;
                        case 3:
                            System.out.println();
                            break;
                        case 4:
                            System.out.println(cell.getBooleanCellValue());
                            break;
                        default:
                            System.out.println();
                    }
                }
            }

        }
    }
}