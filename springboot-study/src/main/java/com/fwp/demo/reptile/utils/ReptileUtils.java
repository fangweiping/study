package com.fwp.demo.reptile.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 网站爬取工具类
 */
@Slf4j
public class ReptileUtils {

    public static void main(String[] args) throws IOException {
        filterImg("D:\\image\\polayoutu",2000,8000);
    }

    /**
     * 根据图片px过滤
     * 宽高都必须在2000-8000px
     * @param desk
     * @param minLimit
     * @param maxLimit
     */
    public static void filterImg(String desk, int minLimit, int maxLimit) {
        File dir = new File(desk);
        AtomicInteger handlerNum = new AtomicInteger();
        AtomicInteger deleteNum = new AtomicInteger();

        Arrays.stream(dir.listFiles()).forEach(file->{
            handlerNum.getAndIncrement();
            int width = 0;
            int height = 0;
            try {
                BufferedImage img = ImageIO.read(file);
                width = img.getWidth();
                height = img.getHeight();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (width < minLimit || width > maxLimit||height < minLimit || height > maxLimit) {
                file.delete();
                deleteNum.getAndIncrement();
                log.info("已删除{}张图片",deleteNum);
            }
            log.info("已处理{}张图片",handlerNum);
        });
    }
}
