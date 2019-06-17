package com.advance.poi.excel.quick_guide.splits_and_freeze_panes;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/17 15:15
 * @Description:划分并冻结窗格
 */
public class SplitsAndFreezePanes {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("second sheet");
        Sheet sheet3 = wb.createSheet("third sheet");
        Sheet sheet4 = wb.createSheet("fourth sheet");

        // 划分一行并冻结
        sheet1.createFreezePane( 0, 1, 0, 1 );
        // 划分一列并冻结
        sheet2.createFreezePane( 1, 0, 1, 0 );
        // Freeze the columns and rows (forget about scrolling position of the lower right quadrant).
        //划分行列并冻结，第三、四象限可以滑动
        sheet3.createFreezePane( 2, 2 );
        // 第三象限是活跃象限，第三第四象限可以滑动
        sheet4.createSplitPane( 2000, 2000, 0, 0, Sheet.PANE_LOWER_LEFT );

        try (OutputStream fileOut = new FileOutputStream("src\\com\\advance\\poi\\excel\\quick_guide\\splits_and_freeze_panes\\workbook.xls")) {
            wb.write(fileOut);
        }
    }
}