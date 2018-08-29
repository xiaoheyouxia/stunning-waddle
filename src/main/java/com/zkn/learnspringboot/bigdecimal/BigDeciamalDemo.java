package com.zkn.learnspringboot.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @description: Decimal
 * @author: lxh
 * @create: 2018-04-23 14:04
 **/
public class BigDeciamalDemo {
    @Test
    public void test(){
        BigDecimal bigDecimal = new BigDecimal("12345123123.2342432");
        System.out.println(bigDecimal);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String format = df.format(bigDecimal);
        System.out.println(format);
    }

}
