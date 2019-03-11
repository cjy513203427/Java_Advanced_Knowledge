package com.advance.hadoop.mapreduce.flowsum;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/11 11:16
 * @Description:
 */
public class FlowSumMapper extends Mapper<LongWritable,Text,Text,FlowBean> {
    //拿到日志中的一行数据，切分各个字段，抽取出我们需要的字段；手机号，上行流量，下行流量，然后封装成kv发送出去
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //拿到一行数据
        String line = value.toString();
        //切分各个字段
        String[] fields = StringUtils.split(line," ");

        String phoneNum = fields[0];
        long upFlow = Long.parseLong(fields[1]);
        long downFlow = Long.parseLong(fields[2]);

        context.write(new Text(phoneNum),new FlowBean(phoneNum,upFlow,downFlow));

    }
}