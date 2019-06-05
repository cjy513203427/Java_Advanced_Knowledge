package com.advance.storm_kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.kafka.bolt.KafkaBolt;

public class StormKafkaTopo {
    public static void main(String[] args) throws AuthorizationException {
        BrokerHosts brokerHosts = new ZkHosts("CentOS7One:2181/kafka,CentOS7Two:2181/kafka,CentOS7Three:2181/kafka");

        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "topic1", "/kafka", "kafkaspout");

        Config conf = new Config();
        Map<String, String> map = new HashMap<String, String>();

        map.put("metadata.broker.list", "CentOS7One:9092,CentOS7Two:9092,CentOS7Three:9092");
        map.put("serializer.class", "kafka.serializer.StringEncoder");
        conf.put("kafka.broker.properties", map);
        conf.put("topic", "topic2");

        spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new KafkaSpout(spoutConfig));
        builder.setBolt("bolt", new SenqueceBolt()).shuffleGrouping("spout");
        builder.setBolt("kafkabolt", new KafkaBolt<String, Integer>()).shuffleGrouping("bolt");

        if(args != null && args.length > 0) {
            //提交到集群运行
            try {
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            }
        } else {
            //本地模式运行
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("Topotest", conf, builder.createTopology());
            Utils.sleep(1000000);
            cluster.killTopology("Topotest");
            cluster.shutdown();
        }



    }
}