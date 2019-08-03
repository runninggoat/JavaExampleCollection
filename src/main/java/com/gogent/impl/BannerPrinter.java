package com.gogent.impl;

import com.gogent.interfaces.AbstractExampleExecutable;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;

public class BannerPrinter extends AbstractExampleExecutable {

    private static final int bufferLen = 1024;

    BufferedInputStream inputStream;

    public BannerPrinter(String bannerFilePath) {
//        System.out.println("加载Banner文件");
        if (StringUtils.isEmpty(bannerFilePath)) {
            System.out.println("Banner文件路径为空");
        } else {
            try {
                String resourcePath = this.getClass().getResource(bannerFilePath).getPath();
//                System.out.println(resourcePath);
                inputStream = (BufferedInputStream) this.getClass().getResourceAsStream(bannerFilePath);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(String.format("找不到Banner文件%s", bannerFilePath));
            }
        }
        start();
    }

    public void process() {
        if (inputStream != null) {
            StringBuffer stringBuffer = new StringBuffer();
            byte[] buffer = new byte[bufferLen];
            int n = 0;
            while (n >= 0) {
                stringBuffer.append(new String(buffer, 0, n));
                try {
                    n = inputStream.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                    n = -1;
                }
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("关闭文件异常");
            }
            System.out.println(stringBuffer.toString());
        } else {
            System.out.println("读取文件异常，文件流为null");
        }
    }
}
