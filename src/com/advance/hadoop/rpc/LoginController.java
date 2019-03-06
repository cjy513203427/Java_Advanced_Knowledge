package com.advance.hadoop.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/1 17:00
 * @Description: 客户端
 */
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);
    public static void main(String[] args) throws IOException {
        LoginServiceInterface proxy = RPC.getProxy(LoginServiceInterface.class, 1L,
                new InetSocketAddress("localhost", 9898)
                , new Configuration());

        String result = proxy.login("Fakler","123456");

        logger.debug(result);
    }
}