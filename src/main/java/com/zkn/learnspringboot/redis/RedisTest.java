package com.zkn.learnspringboot.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import sun.applet.Main;

/**
 * @description: redis测试类
 * @author: lxh
 * @create: 2018-03-14 15:53
 **/
public class RedisTest {

    static Jedis jedis = new Jedis();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void set(){
        jedis.set("hello","1");
        jedis.set("hello","2");
    }

    @Test
    public void getTest(){
        System.out.println(jedis.get("hello"));
    }


    @Test
    public void hset(){
        redisTemplate.opsForHash().increment("banner","banner1",1);
        jedis.hincrBy("banner","banner2",2);
        System.out.println(redisTemplate.opsForHash().entries("banner"));
        System.out.println(redisTemplate.opsForHash().values("banner"));
        System.out.println(jedis.hget("n","m"));
        System.out.println(jedis.hgetAll("n"));
    }
}
