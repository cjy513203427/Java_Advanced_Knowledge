package com.advance.adapter;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/20 22:58
 * @Description:
 */

public class Mp4Player implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
