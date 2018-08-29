package com.zkn.learnspringboot.httpclient;

import java.io.IOException;

/**
 * @description: 刷量工具
 * @author: lxh
 * @create: 2018-04-17 17:27
 **/
public class web_look {

    public static void main(String[] args) throws IOException {

        String web_name = null;
        web_name = "http://industry.caijing.com.cn/20180417/4437424.shtml";
        //用try-catch来忽略超时异常继续执行
        try {
            Thread threads[] = new Thread[5];
            new Thread(new MyThread(web_name, "d://1.txt")).start();
           /* threads[1] = new Thread(new MyThread(web_name, "d://2.txt"));
            threads[2] = new Thread(new MyThread(web_name, "d://3.txt"));
            threads[3] = new Thread(new MyThread(web_name, "d://4.txt"));
            threads[4] = new Thread(new MyThread(web_name, "d://5.txt"));

            for (int i = 0; i < 5; i++) {
                threads[i].start();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
