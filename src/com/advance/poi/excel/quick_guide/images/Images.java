package com.advance.poi.excel.quick_guide.images;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 16:59
 * @Description:
 */
public class Images {
    public static void main(String[] args) throws IOException {
        //create a new workbook
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();

        //将字节型picture加入到workbook
        InputStream is = new FileInputStream("src\\com\\advance\\poi\\excel\\quick_guide\\images\\image1.jpeg");
        byte[] bytes = IOUtils.toByteArray(is);
        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        is.close();

        CreationHelper helper = wb.getCreationHelper();

        //create sheet
        Sheet sheet = wb.createSheet();

        // 创建drawing patriarch.  这是所有形状的顶级容器
        Drawing drawing = sheet.createDrawingPatriarch();

        // 添加图片形状
        ClientAnchor anchor = helper.createClientAnchor();
        //在第2行，第3列插入数据
        anchor.setCol1(3);
        anchor.setRow1(2);
        Picture pict = drawing.createPicture(anchor, pictureIdx);

        //auto-size picture
        pict.resize();

        // 保存工作簿
        String file = "workbook.xls";
        if(wb instanceof XSSFWorkbook) file += "x";
        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\images\\"+file)) {
            wb.write(fileOut);
        }
    }
}