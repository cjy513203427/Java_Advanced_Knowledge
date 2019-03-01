package com.advance.hadoop.rpc;

/**
 * @Author: 谷天乐
 * @Date: 2019/3/1 16:53
 * @Description:
 */
public class LoginServiceImpl implements LoginServiceInterface {
    @Override
    public String login(String username,String password) {
        return username + " logged in successfully";
    }
}