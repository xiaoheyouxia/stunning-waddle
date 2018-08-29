package com.zkn.learnspringboot.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 用于List排序
 * @author: lxh
 * @create: 2018-04-08 17:16
 **/


public class User implements Comparable<User>{

    private int score;

    private int age;

    public User(int score, int age){
        super();
        this.score = score;
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        int i = this.getAge() - o.getAge();//先按照年龄排序
        if(i == 0){
            return this.score - o.getScore();//如果年龄相等了再用分数进行排序
        }
        return i;
    }


    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        users.add(new User(78, 26));
        users.add(new User(67, 23));
        users.add(new User(34, 56));
        users.add(new User(55, 23));
        Collections.sort(users);
        for(User user : users){
            System.out.println(user.getScore() + "," + user.getAge());
        }
    }
}

