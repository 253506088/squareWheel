package com.example.myhashmap;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author chenxiaokang
 * @date 2019/7/8 13:48
 */
@Slf4j
public class ImageBuilderUtils {

    /**
     *  热敏打印机纸张大约宽度（4cm*6cm）
     */
    private static final int width = 151;
    /**
     *  热敏打印机纸张大约高度（4cm*6cm）
     */
    private static final int height = 227;

    /**
     *
     * @param code 编码
     * @param path 二维码存放路劲
     * @return 最新图片生成路径
     */
    public static String ImageBuilder(String code,String path) {
        //得到图片缓冲区
        FileInputStream fileInputStream = null;
        try {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            File file = new File(path);
            if(!file.exists()){
                throw  new Exception("二维码存放地址有误");
            }
            fileInputStream = new FileInputStream(file);
            BufferedImage image2 = ImageIO.read(fileInputStream);
            //得到它的绘制环境(这张图片的笔)
            Graphics2D g2 = (Graphics2D) bi.getGraphics();
            g2.fillRect(0, 0, width, height);
            //设置颜色
            g2.setColor(Color.WHITE);
            // 将图片大小设置为大约4cm*4cm 具体根据纸张大小设定
            g2.drawImage(image2, 10, 85, 135, 135,null);
            g2.drawRect(0, 0, width - 1, height - 1);
            //设置字体:字体、字号、大小
            g2.setFont(new Font("黑体", Font.BOLD, 26));
            //设置背景颜色
            g2.setColor(Color.BLACK);
            //向图片上写字符串
            g2.drawString("java", 15, 30);
            g2.setFont(new Font("黑体", Font.BOLD, 20));
            g2.drawString("·", 65, 28);
            g2.setFont(new Font("黑体", Font.BOLD, 26));
            g2.drawString("画图", 83, 30);
            g2.setFont(new Font("黑体", Font.BOLD, 18));
            g2.drawString("是真的不合适", 15, 60);
            g2.setFont(new Font("黑体", Font.ITALIC, 14));
            g2.drawString(code, 15, 80);
            // 图片上传后的路径
            String savePath = "C:\\Users\\Administrator\\Desktop\\aaaaaa.jpg";
            ImageIO.write(bi, "JPEG", new FileOutputStream(savePath));
            return savePath;
        }catch (Exception e){
            log.error("生成图片错误",e);
            return null;
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String path =  ImageBuilderUtils.ImageBuilder("实习期开发名片模板","C:\\Users\\Administrator\\Desktop\\JKHZ{E367DBYLRLQ{K4TO3L.jpg");
        System.out.println(path);
    }
}