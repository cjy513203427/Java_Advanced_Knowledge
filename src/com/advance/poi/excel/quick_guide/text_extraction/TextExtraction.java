package com.advance.poi.excel.quick_guide.text_extraction;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 16:11
 * @Description:
 */
public class TextExtraction {
    public static void main(String[] args) {
        try (InputStream inp = new FileInputStream("src\\com\\advance\\poi\\excel\\quick_guide\\text_extraction\\workbook.xls")) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            //忽略公式
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
            //将excel内容提取成text
            String text = extractor.getText();
            System.out.println(text);
            //关不关无所谓
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}