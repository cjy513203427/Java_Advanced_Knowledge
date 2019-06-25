package com.advance.poi.excel.quick_guide.cell_comments;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Map;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/18 09:59
 * @Description: 创建批注
 */
public class CellComments {
    public static void main(String[] args) throws IOException {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();

        CreationHelper factory = wb.getCreationHelper();

        Sheet sheet = wb.createSheet();

        Row row = sheet.createRow(3);
        Cell cell = row.createCell(5);
        cell.setCellValue("F4");

        Drawing drawing = sheet.createDrawingPatriarch();

        // 当注释是可见的，显示成1行3列
        ClientAnchor anchor = factory.createClientAnchor();
        anchor.setCol1(cell.getColumnIndex());
        anchor.setCol2(cell.getColumnIndex() + 1);
        anchor.setRow1(row.getRowNum());
        anchor.setRow2(row.getRowNum() + 3);

        // 创建批注并且设置文本和作者
        Comment comment = drawing.createCellComment(anchor);
        RichTextString str = factory.createRichTextString("Hello, World!");
        comment.setString(str);
        comment.setAuthor("Apache POI");

        // 给单元格设置批注
        cell.setCellComment(comment);

        String fname = "src\\com\\advance\\poi\\excel\\quick_guide\\cell_comments\\comment-xssf.xls";
        if (wb instanceof XSSFWorkbook) fname += "x";
        try (OutputStream out = new FileOutputStream(fname)) {
            wb.write(out);
        }

        //wb.close();

        //读取所有的注释信息
        try (InputStream inp = new FileInputStream("src\\com\\advance\\poi\\excel\\quick_guide\\cell_comments\\comment-xssf.xlsx")) {

            Map<CellAddress, Comment> comments = (Map<CellAddress, Comment>) sheet.getCellComments();
            Comment commentA1 = comments.get(new CellAddress(0, 0));
            Comment commentB1 = comments.get(new CellAddress(0, 1));
            for (Map.Entry<CellAddress, ? extends Comment> e : comments.entrySet()) {
                CellAddress loc = e.getKey();
                Comment comment1 = e.getValue();
                System.out.println("Comment at " + loc + ": " +
                        "[" + comment1.getAuthor() + "] " + comment1.getString().getString());
            }

        }
    }
}