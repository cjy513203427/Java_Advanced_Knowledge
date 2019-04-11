package com.advance.hadoop.mapreduce.ii;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
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
 * @Date: 2019/4/11 10:32
 * @Description:
 */
public class InverseIndexStepTwo {
    public static class StepTwoMapper extends Mapper<LongWritable,Text,Text,Text>{
        //k:起始偏移量  v:{hello->a.txt 3}

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();

            String [] fields = StringUtils.split(line,"\t");

            String[] wordAndFileName = StringUtils.split(fields[0],"-->");

            String word = wordAndFileName[0];

            String filename = wordAndFileName[1];

            long count = Long.parseLong(fields[1]);

            context.write(new Text(word),new Text(filename+"-->"+count));
            //map的输出结果是这个形式：<hello,a.txt-->3>
        }
    }

    public static class StepTwoReducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //得到的数据<hello,{a.txt-->3,b.txt-->2,c.txt-->1}>
            String result = "";
            for (Text value:values){
                result += value+" ";
            }
            //输出结果：k:hello v:a.txt-->3  b.txt-->2  c.txt-->1
            context.write(key,new Text(result));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("hadoop.home.dir", "D:\\hadoop-2.6.5");
        Configuration conf  = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(InverseIndexStepTwo.class);

        job.setMapperClass(InverseIndexStepTwo.StepTwoMapper.class);
        job.setReducerClass(InverseIndexStepTwo.StepTwoReducer.class);
        //指定reduce的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //指定mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        Path output = new Path("E:/opt/output_ii2/");
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(output)){
            fs.delete(output,true);
        }

        FileInputFormat.setInputPaths(job,new Path("E:/opt/output_ii/"));
        FileOutputFormat.setOutputPath(job,output);

        System.exit(job.waitForCompletion(true)?0:1);
    }
}