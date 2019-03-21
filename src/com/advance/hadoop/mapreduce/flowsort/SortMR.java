package com.advance.hadoop.mapreduce.flowsort;

import com.advance.hadoop.mapreduce.flowsum.FlowBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/21 16:34
 * @Description:
 */
public class SortMR {
    public static class SortMapper extends Mapper<LongWritable,Text,FlowBean,NullWritable>{
        //拿到一行数据，切分各个字段，封装成一个FlowBean,作为key输出
        //写入<FlowBean,null>
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = StringUtils.split(line," ");

            String phoneNB = fields[0];
            long u_flow = Long.parseLong(fields[1]);
            long d_flow = Long.parseLong(fields[2]);

            context.write(new FlowBean(phoneNB,u_flow,d_flow),NullWritable.get());
        }
    }

    //传递<FlowBean,[null,null,...,null]>
    //写入<18895358020,FlowBean>,<19840170130,FlowBean>,...
    public static class SortReducer extends Reducer<FlowBean,NullWritable,Text,FlowBean>{
        @Override
        protected void reduce(FlowBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            String phoneNB = key.getPhoneNum();
            context.write(new Text(phoneNB),key);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("hadoop.home.dir", "D:\\hadoop-2.6.5");
        Configuration conf  = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(SortMR.class);

        job.setMapperClass(SortMapper.class);
        job.setReducerClass(SortReducer.class);
        //指定reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //指定mapper的输出类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("E:/opt/srcdata_flow/"));
        FileOutputFormat.setOutputPath(job,new Path("E:/opt/output_fow/"));

        job.waitForCompletion(true);
    }

}