package com.blacktv.shejimoshi.结构型模式.适配器模式;

import com.blacktv.shejimoshi.结构型模式.适配器模式.实现类.AudioPlayer;

public class Demo {
    /**
     * 运行结果
     * Playing mp3 file. Name: 二手玫瑰.mp3
     * Playing mp4 file. Name: 深田永美.mp4
     * Playing vlc file. Name: 对魔忍.vlc
     * Invalid media. avi format not supported
     * @param args
     */
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "二手玫瑰.mp3");
        audioPlayer.play("mp4", "深田永美.mp4");
        audioPlayer.play("vlc", "对魔忍.vlc");
        audioPlayer.play("avi", "苍井空.avi");
    }
}
