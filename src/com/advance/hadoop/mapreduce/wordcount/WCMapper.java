package com.advance.hadoop.mapreduce.wordcount;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/6 14:51
 * @Description: map和reduce输入输出都是key-value形式
 * 前两个是指定mapper输入的数据类型，后两个是传递给reduce的数据类型
 */

public class WCMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
    /**
     * @Author 谷天乐
     * @Description 写入<I,1>,<I,1>,<Cang,1>...
     * @Date 2019/3/21 17:36
     * @Param [key, value, context]
     * @return void
     **/
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //key数据起始偏移量,value文本内容

        //转换String
        String line = value.toString();
        //按特定分隔符分割
        String[] words = StringUtils.split(line," ");
        //遍历单词数组，输出成k-v形式
        for(String word:words) {
            context.write(new Text(word),new LongWritable(1));
        }
    }
}