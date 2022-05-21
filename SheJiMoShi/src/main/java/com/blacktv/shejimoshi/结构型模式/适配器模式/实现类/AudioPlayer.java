package com.blacktv.shejimoshi.结构型模式.适配器模式.实现类;

import com.blacktv.shejimoshi.结构型模式.适配器模式.接口.MediaPlayer;

/**
 * 音频播放器
 */
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType){
            case "mp3":
                //播放 mp3 音乐文件的内置支持
                System.out.println("Playing mp3 file. Name: " + fileName);
                break;
            case "vlc":
            case "mp4":
                //mediaAdapter 提供了播放其他文件格式的支持
                //这里就是适配器模式的关键
                mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
                break;
            default:
                System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}