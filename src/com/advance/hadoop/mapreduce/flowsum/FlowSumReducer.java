package com.advance.hadoop.mapreduce.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/11 14:10
 * @Description:
 */
public class FlowSumReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
    //在map处理完成后，将所有key-value缓存起来，进行分组，传递<key,values[]>
    //框架每传递一组数据<18895358020,[FlowBean,FlowBean,FlowBean,...]>
    //写入<18895358020,[FlowBean+FlowBean+...+FlowBean]>,即FlowBean对应属性相加
    //reduce中的业务逻辑就是遍历values，然后进行累加求和再输出
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