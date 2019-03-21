package com.advance.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/6 14:51
 * @Description:
 */
public class WCReducer extends Reducer<Text,LongWritable,Text,LongWritable>{
    /**
     * @Author 谷天乐
     * @Description 在map处理完成后，将所有key-value缓存起来，进行分组，传递<key,values[]>
     * 如<hello,[1,1,1,1...]>
     * 写入<hello,5>
     * @Date 2019/3/6 15:21
     * @Param [key, values, context]
     * @return void
     **/
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        long count = 0;
        //遍历value的list
        for(LongWritable value:values){
            count+=value.get();
        }

        //输出单词的统计结果
        context.write(key,new LongWritable(count));
    }
}