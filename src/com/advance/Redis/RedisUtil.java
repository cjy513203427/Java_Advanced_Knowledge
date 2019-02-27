package com.advance.Redis;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @Author: 谷天乐
 * @Date: 2019/2/25 17:17
 * @Gratitude: wudalang_gd
 * @Description: 根据原生Jedis封装常用工具
 */
public class RedisUtil{
    private Logger logger = Logger.getLogger(RedisUtil.class);
    private final String ip = "localhost";
    private final Integer port = 6379;
    //获取连接
    public  Jedis connection() throws Exception{
        Jedis jedis = new Jedis(ip,port);
        return jedis;
    }


    //为string添加元素
    public void set(String key, String value) throws Exception {
        Jedis jedis = connection();
        jedis.set(key,value);

    }

    //获取string
    public String get(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.get(key);
    }


    //追加string
    public void append(String key, String value) throws Exception {
        Jedis jedis = connection();
        jedis.append(key,value);
    }

    //添加set
    public void sadd(String key, Set<String> value) throws Exception {
        Jedis jedis = connection();
        for(String str: value){
            jedis.sadd(key, str);
        }
    }

    //set删除指定元素
    public void srem(String key, Set<String> value) throws Exception {
        Jedis jedis = connection();
        Iterator<String> it = value.iterator();
        while(it.hasNext()){
            String str = it.next();
            jedis.srem(key, str);
        }
    }

    //获取Set的key对应的value总数
    public Long scard(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.scard(key);
    }

    //获取Set的key对应的所有value
    public Set<String> smembers(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.smembers(key);
    }

    //判断set是否存在
    public boolean sismember(String key, String value) throws Exception {
        Jedis jedis = connection();
        return jedis.sismember(key,value);
    }

    //随机获取数据
    public String srandmember(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.srandmember(key);
    }



    //向list添加元素
    public void lpush(String key, List<String> list) throws Exception {
        Jedis jedis = connection();
        for(String s: list){
            jedis.lpush(key,s);
        }
    }

    //向list添加string元素
    public void lpush(String key, String... params) throws Exception {
        Jedis jedis = connection();
        jedis.lpush(key,params);
    }

    //获取list
    public List<String> lrange(String key, Integer start, Integer end)
            throws Exception {
        Jedis jedis = connection();
        return jedis.lrange(key, start, end);
    }

    //删除任意类型的key
    public void del(String key) throws Exception {
        Jedis jedis = connection();
        jedis.del(key);
    }

    //设置map
    public void hmset(String key, Map<String, String> map) throws Exception {
        Jedis jedis = connection();
        jedis.hmset(key,map);
    }

    //获取map的key的个数
    public Long hlen(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.hlen(key);
    }

    //获取map中所有key
    public Set<String> hkeys(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.hkeys(key);
    }

    //获取map中所有value
    public List<String> hvals(String key) throws Exception {
        Jedis jedis = connection();
        return jedis.hvals(key);
    }

    //获取map中的指定key的value
    public List<String> hmget(String key, String... params)
            throws Exception {
        Jedis jedis = connection();
        if (null == params || params.length == 0) {
            throw new RuntimeException(this.getClass().getSimpleName()+  "::"
                    + new Exception().getStackTrace()[0].getMethodName()+"参数不能为空");
        }
        return jedis.hmget(key,params);
    }

    //获取map所有的key和value
    public Map<String, String> hgetAll(String key)
            throws Exception {
        Jedis jedis = connection();
        return jedis.hgetAll(key);
    }

    //删除指定key的map
    public void hdel(String key, String... params) throws Exception {
        Jedis jedis = connection();
        if (null == params || params.length == 0) {
            throw new RuntimeException(this.getClass().getSimpleName()+  "::"
                    + new Exception().getStackTrace()[0].getMethodName()+"参数不能为空");
        }
        jedis.hdel(key,params);
    }


    //测试string
    @Test
    public void testString() throws Exception{
        RedisUtil r = new RedisUtil();
        r.set("z", "wb");
        String s = r.get("z");
        logger.debug(s);
    }

    //测试set
    @Test
    public void testList() throws Exception{
        RedisUtil r = new RedisUtil();
        List<String> list = new ArrayList<>();
        list.add("w");
        list.add("b");
        r.lpush("list",list);
        List<String> t = r.lrange("list",0,-1);
        logger.debug(t);
    }

    //测试set
    @Test
    public void testSet() throws Exception{
        RedisUtil r = new RedisUtil();
        Set<String> set = new HashSet<String>();
        set.add("w");
        set.add("b");
        r.sadd("set",set);
        Set<String> t = r.smembers("set");
        Long zahl = r.scard("set");
        logger.debug(t);
    }

    //测试map
    @Test
    public void mapTest() throws Exception {
        RedisUtil r = new RedisUtil();
        Map <String,String> map = new HashMap<>();
        map.put("Red Alert 3","Long live Soviet");
        map.put("Starcraft","No one can undie");
        map.put("PUBG","Keep breath");
        r.hmset("Game",map);
        r.hdel("Game","Starcraft");
        Map <String,String> m = r.hgetAll("Game");
        logger.debug(m);
    }
}