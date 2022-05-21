package com.blacktv.shejimoshi.结构型模式.适配器模式.接口;

/**
 * 媒体播放器接口
 */
public interface MediaPlayer {
    /**
     * 播放功能
     *
     * @param audioType 媒体类型
     * @param fileName  文件名
     */
    void play(String audioType, String fileName);
}
