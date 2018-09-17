package com.advance.Chain_Of_Responsibility;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/8 17:37
 * @Description:
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}