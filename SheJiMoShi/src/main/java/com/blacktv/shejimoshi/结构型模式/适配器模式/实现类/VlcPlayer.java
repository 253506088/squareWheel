package com.blacktv.shejimoshi.结构型模式.适配器模式.实现类;

import com.blacktv.shejimoshi.结构型模式.适配器模式.接口.AdvancedMediaPlayer;

/**
 * vlc播放器
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}