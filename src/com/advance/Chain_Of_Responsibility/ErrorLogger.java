package com.advance.Chain_Of_Responsibility;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/8 17:37
 * @Description:
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}