package com.zkn.learnspringboot.httpclient;

import com.zkn.learnspringboot.util.HttpClientUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 点击网页
 * @author: lxh
 * @create: 2018-04-17 15:53
 **/
public class Click {
    @Test
    public void clickMethod() {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String> yjz = null;
        try {
            yjz = HttpClientUtil.getInstance().httpGET("http://industry.caijing.com.cn/20180417/4437424.shtml", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(yjz);
    }

}
