package com.advance.storm_kafka;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;

import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
/**
 * @Author: 谷天乐
 * @Date: 2019/4/29 10:12
 * @Description:
 */

public class MessageScheme implements Scheme {

    public List<Object> deserialize(ByteBuffer var1) {
        try {
            byte[] arg0 = null;
            String msg = new String(arg0, "UTF-8");
            return new Values(msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Fields getOutputFields() {
        return new Fields("msg");
    }

}