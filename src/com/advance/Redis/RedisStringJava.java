package com.advance.Redis;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/10/8.
 */
public class RedisStringJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        jedis.append("runoobkey",":88");
        //删除键为key的项
        //jedis.del("runoobkey");
        //设置过期时间
        jedis.expire("runoobkey",3);
        Long timeRemaining = jedis.ttl("runoobkey");
        jedis.persist("runoobkey");
        // 获取存储的数据类型并输出
        System.out.println("redis 的类型是"+jedis.type("runoobkey"));
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }
}
