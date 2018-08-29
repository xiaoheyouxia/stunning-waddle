package com.zkn.learnspringboot.string;

import org.junit.Test;

/**
 * @description: String测试
 * @author: lxh
 * @create: 2018-04-23 14:06
 **/
public class StringDemo {
    @Test
    public void test(){
        String a = "123";
        String b = new String("123");
        String c = "123";

        // 重新初始化字符串 对应内存地址发生变化 == false
        if(a == b){
            System.out.println("true");
        }else{
            System.out.println("false");
        }

        // 虚拟机将相同的字符串共享 == true
        if(a == c){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }

}
