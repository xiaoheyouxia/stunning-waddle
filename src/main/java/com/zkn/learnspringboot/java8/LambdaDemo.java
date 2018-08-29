package com.zkn.learnspringboot.java8;

import com.google.common.base.Function;
import com.zkn.learnspringboot.entity.Apple;
import org.junit.Test;
import sun.java2d.pipe.SpanIterator;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Locale.filter;
import static java.util.stream.Collectors.groupingBy;

/**
 * @description: Lamdar表达式demo
 * @author: lxh
 * @create: 2018-04-09 10:38
 **/
public class LambdaDemo {

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    static List<Apple> filterApples(List<Apple> apples,
                                    Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void fileFilter() {
        System.out.println(new File(".").exists());
        System.out.println(new File(".").isDirectory());
        System.out.println(new File(".").getAbsolutePath().toString());
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        System.out.println(hiddenFiles.toString());

        File[] hiddenFilesNew = new File(".").listFiles(File::isHidden);
        System.out.println(hiddenFilesNew);
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<Apple>();
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        apple1.setColor("red");
        apple1.setWeight(150);
        apple2.setWeight(160);
        apple2.setColor("green");
        apple3.setWeight(160);
        apple3.setColor("green");
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        System.out.println(apples.size());
        Apple red = new Apple("red", 150);
        List<Apple> apples1 = new ArrayList<>();
        apples1.add(red);
        apples.removeAll(apples1);
        System.out.println(apples.size());
    }

    @Test
    public void apple() {
        List<Apple> apples = new ArrayList<Apple>();
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        apple1.setColor("red");
        apple1.setWeight(150);
        apple2.setWeight(160);
        apple2.setColor("green");
        apple3.setWeight(160);
        apple3.setColor("green");
        apples.add(apple1);
        apples.add(apple2);
        apples.add(apple3);
        // lambda用法
        List<Apple> apples1 = filterApples(apples, LambdaDemo::isGreenApple);
        List<Apple> apples2 = filterApples(apples, (Apple a) -> a.getWeight() > 150);

        // stream流操作集合
        List<Apple> apples3 = apples.stream()
                .filter((Apple a) -> a.getWeight() > 150)
                .collect(Collectors.toList());

        // lambda开启线程
        new Thread(() -> System.out.println("haha")).start();

        /**
         * 正常定义比较器:实现接口Comparator，并重写里面的方法compare
         * java8定义比较器:通过lambda表达式简洁的定义比较器
         */
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };
        Comparator<Apple> appleComparator = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        System.out.println(appleComparator.compare(apple1, apple2));

        // 消费一个对象.返回一个comsumer对象,无返回值void
        Consumer<Apple> appleConsumer = (Apple a) -> System.out.println(a.getWeight());
        appleConsumer.accept(apple1);

        // 组合两个值,返回一个comparator对象,有返回值
        Comparator<Apple> appleComparator1 = (Apple a1, Apple a2) -> a1.getWeight() * a2.getWeight();
        System.out.println(appleComparator1.compare(apple1, apple2));

        // 对象中提取值
        Function<String, Object> stringObjectFunction = (String s) -> s.length();
        System.out.println(stringObjectFunction.apply("12345"));


        /**
         *  开发中,使用lambda表达式避免自动装箱,占用内存
         */
        // 不装箱,返回一个Predicate对象,返回boolean
        IntPredicate integer = (int i) -> i % 2 == 0;
        System.out.println(integer.test(1000));
        // 自动装箱
        Function<Integer, Object> integerObjectFunction = (Integer i) -> i % 2 == 0;
        System.out.println(integerObjectFunction.apply(1000));

        Predicate<Integer> integerPredicate = (Integer i) -> i % 2 == 0;
        System.out.println(integerPredicate.test(1000));
    }

    // 函数式接口用法 Function,Predicate,Consumer
    @Test
    public void test7() {
        Boolean flag = operation(100, (x) -> x > 200);
        System.out.println(flag);
    }

    public boolean operation(Integer num, MyPredicate<Integer> myPredicate) {
        return myPredicate.test(num);
    }

    public interface MyPredicate<T> {
        public boolean test(T t);
    }

    @Test
    public void test(){
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));             // val33

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));     // false

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));    // true

        map.computeIfAbsent(3, num -> "bam");
        System.out.println( map.get(3));             // val33

        map.computeIfAbsent(11, num -> "bam");
        System.out.println( map.get(11));

        map.remove(3, "val3");
        System.out.println(map.get(3));             // val33

        map.remove(3, "val33");
        System.out.println(map.get(3));             // null

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9concat
    }


}
