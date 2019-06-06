package com.advance.poi.excel.quick_guide.control_of_the_lifecycle;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/6/6 10:53
 * @Description:控制流的生命周期
 */
public class LifecycleControl {
    //excel2003
    //Use a file
    public static void poiFileSystemwithFile() throws IOException {
        // HSSFWorkbook, File
        POIFSFileSystem fs = new POIFSFileSystem(new File("src/com/advance/poi/excel/quick_guide/control_of_the_lifecycle/file.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
        fs.close();
    }

    //excel2003
    //Use an InputStream, needs more memory
    public static void poiFileSystemWithStream() throws IOException {
        // HSSFWorkbook, File
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("src/com/advance/poi/excel/quick_guide/control_of_the_lifecycle/file.xlsx"));
        HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
        fs.close();
    }

    //excel2007+
    public static void opcPackageWithFile() throws InvalidFormatException, IOException {
        // XSSFWorkbook, File
        OPCPackage pkg = OPCPackage.open(new File("src/com/advance/poi/excel/quick_guide/control_of_the_lifecycle/file.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        pkg.close();
    }

    ////excel2007+
    //Use an InputStream, needs more memory
    public static void opcPackageWithStream() throws IOException, InvalidFormatException {
        // XSSFWorkbook, InputStream, needs more memory
        OPCPackage pkg = OPCPackage.open(new FileInputStream("src/com/advance/poi/excel/quick_guide/control_of_the_lifecycle/file.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        pkg.close();
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {

        /*poiFileSystemwithFile();
        poiFileSystemWithStream();*/
        opcPackageWithFile();
//        opcPackageWithStream();
    }
}