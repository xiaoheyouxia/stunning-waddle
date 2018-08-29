package com.zkn.learnspringboot.stream;

import com.zkn.learnspringboot.entity.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @description: stream流Demo
 * @author: lxh
 * @create: 2018-04-12 16:37
 **/
public class StreamTest {
@Test
    public void streamDemo() {
        Apple a1 = new Apple("red", 110);
        Apple a2 = new Apple("blue", 120);
        Apple a3 = new Apple("green", 80);
        Apple a4 = new Apple("black", 100);
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(a1);
        apples.add(a2);
        apples.add(a3);
        apples.add(a4);

        Comparator<Integer> integerComparator = (Integer a, Integer b) -> a.compareTo(b);

        List<String> collect = apples.stream()
                .distinct() //去重 key,value都相同才去重
                //.filter((Apple a) -> a.getWeight() <= 100) //过滤 过滤条件
                .sorted(Comparator.comparing(Apple::getWeight).reversed())    // 排序 倒序
                .map(Apple::getColor)
                .collect(toList());

        for (String list : collect
             ) {
            System.out.println(list);
        }
    }

}
