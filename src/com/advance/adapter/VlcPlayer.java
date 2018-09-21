package com.advance.adapter;

/**
 * @Auther: 谷天乐
 * @Date: 2018/9/20 22:58
 * @Description:
 */


public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}
