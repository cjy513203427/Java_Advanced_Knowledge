package com.advance.hadoop.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: 谷天乐
 * @Date: 2019/2/28 16:31
 * @Description:
 */
public class HDFSUtil {
    private Logger logger = LogManager.getLogger(HDFSUtil.class);

    FileSystem fs = null;

    /**
     * @Author 谷天乐
     * @Description 解析配置文件
     * @Date 2019/3/1 11:33
     * @Param []
     * @return void
     **/
    @BeforeTest
    public void init() throws IOException, URISyntaxException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://CentOS7One:9000/");
        fs = FileSystem.get(new URI("hdfs://CentOS7One:9000/"),conf,"root");
    }

    /**
     * @Author 谷天乐
     * @Description
     * @Date 2019/3/1 11:10
     * @Param []
     * @return void
     **/
    @Test
    public void upload() throws IOException {
        Path dst = new Path("hdfs://CentOS7One:9000/aa/qingshu.txt");
        FSDataOutputStream os = fs.create(dst);
        FileInputStream is = new FileInputStream("E://opt//qingshu.txt");
        IOUtils.copy(is, os);
    }

    /**
     * @Author 谷天乐
     * @Description 调用封装好的方法
     * @Date 2019/3/1 11:11
     * @Param []
     * @return void
     **/
    @Test
    public void capsulation_upload() throws IOException {
        fs.copyFromLocalFile(new Path("E://opt//qingshu.txt"),
                new Path("hdfs://CentOS7One:9000/aa/qingshu2.txt"));
    }

    @Test
    public void download() throws IOException {
        Path dst = new Path("hdfs://CentOS7One:9000/aa/qingshu.txt");
        FSDataInputStream is = fs.open(dst);
        FileOutputStream os = new FileOutputStream("E://opt//qingshu_cap.txt");
        IOUtils.copy(is, os);
    }

    public void capsulation_download() throws IOException {
        fs.copyToLocalFile(new Path("hdfs://CentOS7One:9000/aa/qingshu2.txt"),
                new Path("E://opt//qingshu.txt"));
    }


    @Test
    public void listFiles() throws IOException {
        //拿文件,可以设置是否遍历
        RemoteIterator<LocatedFileStatus> files =  fs.listFiles(new Path("/"),true);
        while (files.hasNext()){
            LocatedFileStatus file = files.next();
            Path filePath = file.getPath();
            String fileName = filePath.getName();
            logger.info(fileName);
        }
        logger.info("-----------------分割线-----------------");
        //拿文件和文件夹，是否遍历需要自己写
        FileStatus[] listFileStatus = fs.listStatus(new Path("/"));
        for(FileStatus fileStatus:listFileStatus){
            String fileName = fileStatus.getPath().getName();
            logger.info(fileName + (fileStatus.isDirectory()?"(文件夹)":"(文件)"));
        }

    }

    @Test
    public void mkdir() throws IOException {
        fs.mkdirs(new Path("/a"));
    }

    public void rm() throws IOException {
        fs.delete(new Path("/a"),true);
    }
}