package com.advance.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: 谷天乐
 * @Date: 2019/4/24 15:33
 * @Description:
 */
public class HBaseDao {

    @Test
    public void insertTest() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","CentOS7Five:2181,CentOS7Six:2181,CentOS7Seven:2181");
        HTable nvshen = new HTable(conf,"nvshen");
        //列族的name字段
        Put name = new Put(Bytes.toBytes("rk0001"));
        name.add(Bytes.toBytes("base_info"),Bytes.toBytes("name"),Bytes.toBytes("angelababy"));
        //列族的age字段
        Put age = new Put(Bytes.toBytes("rk0001"));
        age.add(Bytes.toBytes("base_info"),Bytes.toBytes("age"),Bytes.toBytes("18"));

        ArrayList<Put> puts = new ArrayList<>();
        puts.add(name);
        puts.add(age);

        nvshen.put(puts);

    }

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","CentOS7Five:2181,CentOS7Six:2181,CentOS7Seven:2181");

        HBaseAdmin admin = new HBaseAdmin(conf);

        TableName tableName = TableName.valueOf("nvshen");

        HTableDescriptor desc = new HTableDescriptor(tableName);
        //base_info列族
        HColumnDescriptor base_info = new HColumnDescriptor("base_info");
        //extra_info列族
        HColumnDescriptor extra_info = new HColumnDescriptor("extra_info");
        //保存的版本最大数
        base_info.setMaxVersions(5);
        //加入列族
        desc.addFamily(base_info);
        desc.addFamily(extra_info);

        admin.createTable(desc);
    }
}