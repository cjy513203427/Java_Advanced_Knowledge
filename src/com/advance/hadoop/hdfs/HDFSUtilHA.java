package com.advance.hadoop.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: 谷天乐
 * @Date: 2019/4/18 15:02
 * @Description:
 */
public class HDFSUtilHA {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://ns1/"),conf,"root");
        fs.copyFromLocalFile(new Path("E:\\opt\\srcdata\\qingshu.txt"),new Path("hdfs://ns1/"));
    }
}