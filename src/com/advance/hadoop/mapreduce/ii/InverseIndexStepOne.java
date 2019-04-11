package com.advance.hadoop.mapreduce.ii;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/4/11 10:05
 * @Description:
 */
public class InverseIndexStepOne {

    public static class StepOneMapper extends Mapper<LongWritable,Text,Text,LongWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //获取一行数据
            String line = value.toString();
            //切分出各个单词
            String[] fields = StringUtils.split(line," ");
            //获取这一行数据所在的文件切片
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            //从文件中获取文件名
            String fileName = inputSplit.getPath().getName();

            for (String field:fields){
                //写入hello-->a.txt 1
                context.write(new Text(field+"-->"+fileName),new LongWritable(1));
            }
        }
    }

    public static class StepOneReducer extends Reducer<Text,LongWritable,Text,LongWritable>{
        //<hello--->a.txt,{1,1,1,...}>
        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            long counter = 0;
            for (LongWritable value:values){
                counter+=value.get();
            }

            context.write(key,new LongWritable(counter));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("hadoop.home.dir", "D:\\hadoop-2.6.5");
        Configuration conf  = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(InverseIndexStepOne.class);

        job.setMapperClass(InverseIndexStepOne.StepOneMapper.class);
        job.setReducerClass(InverseIndexStepOne.StepOneReducer.class);
        //指定reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        //指定mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        Path output = new Path("E:/opt/output_ii/");
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(output)){
            fs.delete(output,true);
        }

        FileInputFormat.setInputPaths(job,new Path("E:/opt/srcdata_ii/"));
        FileOutputFormat.setOutputPath(job,output);

        System.exit(job.waitForCompletion(true)?0:1);
    }
}