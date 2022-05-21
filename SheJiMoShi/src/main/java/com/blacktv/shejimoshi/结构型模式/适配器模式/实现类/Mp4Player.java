package com.blacktv.shejimoshi.结构型模式.适配器模式.实现类;

import com.blacktv.shejimoshi.结构型模式.适配器模式.接口.AdvancedMediaPlayer;

/**
 * mp4播放器
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}