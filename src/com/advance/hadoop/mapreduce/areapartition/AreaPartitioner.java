package com.advance.hadoop.mapreduce.areapartition;

import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/28 16:19
 * @Description:
 */
public class AreaPartitioner<KEY, VALUE> extends Partitioner<KEY, VALUE>{

    private static HashMap<String,Integer> areaMap = new HashMap<>();

    static {
        areaMap.put("188",0);
        areaMap.put("198",1);

    }
    @Override
    public int getPartition(KEY key, VALUE value, int numPartitions) {
        int areaCoder = areaMap.get(key.toString().substring(0,3))==null?2:areaMap.get(key.toString().subSequence(0,3));
        return areaCoder;
    }


}