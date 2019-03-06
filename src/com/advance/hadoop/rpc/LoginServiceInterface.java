package com.advance.hadoop.rpc;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/1 16:49
 * @Description: 服务器端和客户端各放置一份
 * 这里写的单机版本，在同一台机器上，所以只有一份
 */
public interface LoginServiceInterface {
    long versionID = 1L;
    public String login(String username,String password);
}