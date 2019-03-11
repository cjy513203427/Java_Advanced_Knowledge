package com.advance.hadoop.mapreduce.flowsum;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/11 11:17
 * @Description:
 */
public class FlowBean implements Writable {
    //手机号
    private String phoneNum;
    //上行流量
    private Long upFlow;
    //下行流量
    private Long downFlow;
    //总流量
    private Long sumFlow;

    public FlowBean() {}

    public FlowBean(String phoneNum, Long upFlow, Long downFlow) {
        this.phoneNum = phoneNum;
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString() {
        return ""+upFlow+"\t"+downFlow+"\t"+sumFlow;
    }

    //将对象序列化到流中
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(phoneNum);
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    //将对象反序列化出对象的数据
    @Override
    public void readFields(DataInput in) throws IOException {
        phoneNum = in.readUTF();
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }
}