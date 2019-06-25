package com.advance.poi.excel.quick_guide.read_images_from_workbook;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/18 09:07
 * @Description: 读取Excel的所有图片
 */
public class ReadImages {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        try (InputStream inp = new FileInputStream("src\\com\\advance\\poi\\excel\\quick_guide\\read_images_from_workbook\\workbook.xlsx")) {
            //InputStream inp = new FileInputStream("workbook.xlsx");

            Workbook wb = WorkbookFactory.create(inp);

            List lst = wb.getAllPictures();
            // 不使用Iterator it = lst.iterator();
            // ListIterator可以获取索引，方便我们命名文件名
            for (ListIterator it =  lst.listIterator(); it.hasNext(); ) {
                PictureData pict = (PictureData)it.next();
                String ext = pict.suggestFileExtension();
                byte[] data = pict.getData();
                if (ext.equals("jpeg")){
                    try (OutputStream out = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\read_images_from_workbook\\pict"+it.nextIndex()+".jpg")) {
                        out.write(data);
                    }
                }
                if (ext.equals("png")){
                    try (OutputStream out = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\read_images_from_workbook\\pict.png")) {
                        out.write(data);
                    }
                }
            }
        }
    }
}