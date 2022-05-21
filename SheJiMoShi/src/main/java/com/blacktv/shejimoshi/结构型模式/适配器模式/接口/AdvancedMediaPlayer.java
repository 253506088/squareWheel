package com.blacktv.shejimoshi.结构型模式.适配器模式.接口;

/***
 * 高级媒体接口
 */
public interface AdvancedMediaPlayer {
    /**
     * 播放vlc类型的媒体文件
     *
     * @param fileName
     */
    void playVlc(String fileName);

    /**
     * 播放mp4类型的媒体文件
     *
     * @param fileName
     */
    void playMp4(String fileName);
}
