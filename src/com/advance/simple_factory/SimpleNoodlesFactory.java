package com.advance.simple_factory;

/**
 * @Auther: 谷天乐
 * @Date: 2018/8/23 21:02
 * @Description:
 */
public class SimpleNoodlesFactory {
    public static final int TYPE_LZ = 1;//兰州拉面
    public static final int TYPE_PM = 2;//泡面
    public static final int TYPE_GK = 3;//干扣面

    public static INoodles createNoodles(int type) {
        switch (type) {
            case TYPE_LZ:
                return new LzNoodles();
            case TYPE_PM:
                return new PaoNoodles();
            case TYPE_GK:
                return new GankouNoodles();
            default:
                return new GankouNoodles();
        }
    }
}