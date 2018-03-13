package com.zkn.learnspringboot.learnTimer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 定时任务
 * @author: lxh
 * @create: 2018-03-12 16:59
 **/
@Component
public class learnTimer {
    @Scheduled(cron = "0/5 * *  * * ? ")   //每5秒执行一次
    public void timer() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + "*********B任务每5秒执行一次进入测试");
    }
}
