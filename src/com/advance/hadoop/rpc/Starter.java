package com.advance.hadoop.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/1 16:54
 * @Description: 服务器端
 */
public class Starter {
    public static void main(String[] args) throws IOException {
        Builder builder = new Builder(new Configuration());
        builder.setBindAddress("localhost").setPort(9898).setProtocol(LoginServiceInterface.class)
                .setInstance(new LoginServiceImpl());
        Server server = builder.build();
        server.start();
    }
}