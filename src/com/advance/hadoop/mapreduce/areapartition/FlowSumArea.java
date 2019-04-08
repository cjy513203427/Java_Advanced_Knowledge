package com.advance.hadoop.mapreduce.areapartition;

import com.advance.hadoop.mapreduce.flowsum.FlowBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/28 16:11
 * @Description:对流量原始日志进行流量统计，将不同省份的用户统计结果输出到不同文件
 * 需要自定义改造两个机制
 * 1、改造分区逻辑
 * 2、自定义reducer、task的并发任务数
 */
public class FlowSumArea {
    public static class FlowSumAreaMapper extends Mapper<LongWritable,Text,Text,FlowBean>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //拿到一行数据
            String line = value.toString();
            //切分各个字段
            String[] fields = StringUtils.split(line," ");

            String phoneNum = fields[0];
            Long upFlow = Long.parseLong(fields[1]);
            Long downFlow = Long.parseLong(fields[2]);

            context.write(new Text(phoneNum),new FlowBean(phoneNum,upFlow,downFlow));
        }
    }

    public static class FlowSumAreaReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            long up_flow_counter = 0;
            long down_flow_counter = 0;

            for(FlowBean bean:values){
                up_flow_counter += bean.getUpFlow();
                down_flow_counter += bean.getDownFlow();
            }
            context.write(key,new FlowBean(key.toString(),up_flow_counter,down_flow_counter));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("hadoop.home.dir", "D:\\hadoop-2.6.5");
        Configuration conf  = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(FlowSumArea.class);

        job.setMapperClass(FlowSumAreaMapper.class);
        job.setReducerClass(FlowSumAreaReducer.class);

        job.setPartitionerClass(AreaPartitioner.class);
        //指定mapper的输出数据key-value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //指定reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        //设置reduce任务的并发数，应该跟分组的数量保持一致
        job.setNumReduceTasks(2);

        FileInputFormat.setInputPaths(job,new Path("E:/opt/srcdata_flow/"));
        FileOutputFormat.setOutputPath(job,new Path("E:/opt/output_area/"));

        System.exit(job.waitForCompletion(true)?0:1);
    }
}