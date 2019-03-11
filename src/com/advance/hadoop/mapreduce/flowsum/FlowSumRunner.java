package com.advance.hadoop.mapreduce.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/11 14:29
 * @Description: Job描述和提交类的规范写法
 */
public class FlowSumRunner extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        System.setProperty("hadoop.home.dir", "D:\\hadoop-2.6.5");
        Configuration conf  = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(FlowSumRunner.class);

        job.setMapperClass(FlowSumMapper.class);
        job.setReducerClass(FlowSumReducer.class);
        //指定reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //指定mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job,new Path("E:/opt/srcdata_flow/"));
        FileOutputFormat.setOutputPath(job,new Path("E:/opt/output_flow/"));

        job.waitForCompletion(true);
        return job.waitForCompletion(true)?1:0;
    }

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new Configuration(),new FlowSumRunner(),args);
        System.exit(run);
    }


}