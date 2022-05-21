package com.blacktv.shejimoshi.结构型模式.适配器模式.实现类;

import com.blacktv.shejimoshi.结构型模式.适配器模式.接口.AdvancedMediaPlayer;
import com.blacktv.shejimoshi.结构型模式.适配器模式.接口.MediaPlayer;

/**
 * 视频播放器
 */
public class MediaAdapter implements MediaPlayer {

    private AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}