package com.advance.Engine_and_Message.domain;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/7 17:30
 * @Description:
 * 引擎类
 */
public class EngineDomain {
    private String engineType;//引擎类型，跟消息类型对应
    private String desc;//引擎描述
    private String className;//引擎类名
    private int initsize;//初始化引擎数量
    private int initMessageSize = 10000;//初始化消息队列大小，默认为10000
    public String getEngineType() {
        return engineType;
    }
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public int getInitsize() {
        return initsize;
    }
    public void setInitsize(int initsize) {
        this.initsize = initsize;
    }
    public int getInitMessageSize() {
        return initMessageSize;
    }
    public void setInitMessageSize(int initMessageSize) {
        this.initMessageSize = initMessageSize;
    }

}
