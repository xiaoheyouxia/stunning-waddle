package com.zkn.learnspringboot.java8;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @description: java8时间类
 * @author: lxh
 * @create: 2018-05-07 13:53
 **/
public class TimeDemo {
    @Test
    public void test() {
        // 获取当前时间戳
        Instant now = Instant.now();
        System.out.println(now);

        // 获取当前年月日
        LocalDate now1 = LocalDate.now();
        System.out.println(now1);

        // 拼接年月日
        System.out.println(now1.getYear() + "年" + now1.getMonthValue() + "月" + now1.getDayOfMonth() + "日");

        // 转换年月日
        System.out.println(LocalDate.of(2018, 05, 07));

        // 获取时分秒 xx:xx:xx.xxx
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
        System.out.println(LocalTime.now() + "----" + LocalTime.now().format(formatter));

        // 自定义格式化时间格式
        LocalDateTime now2 = LocalDateTime.now();
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm a");
            String aaaa = now2.format(dateTimeFormatter);
            System.out.println("转换前" + now2 + ",转换后" + aaaa);
        } catch (DateTimeException e) {
            System.out.println("格式转换错误" + now2);
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws ParseException {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        List<String> strings1 = strings.subList(1, strings.size());
        for (int i = 0; i < strings1.size(); i++) {
            System.out.println(strings1.get(i));
        }
    }
}
