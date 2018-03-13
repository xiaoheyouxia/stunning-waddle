package com.zkn.learnspringboot.learnJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @description: 系统学习FastJson
 * @author: lxh
 * @create: 2018-03-09 10:03
 **/
public class JsonTest {
    public static void main(String[] args) {
        // JSONObject 本身是一个Map
        JSONObject js = new JSONObject();
        js.put("11","hello");
        js.put("12",11);

        JSONObject js1 = new JSONObject();
        js1.put("21","gaga");
        js1.put("22",12);
        System.out.println(js1.toString());
        String s1 = js1.toJSONString();
        JSONObject js2 = JSONObject.parseObject(s1);
        JSONObject js3 = JSON.parseObject(s1);
        System.out.println(js2);
        System.out.println(js3);

        //Map转JSONObject
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("1","阿萨德");
        map.put("2","水电费");
        String s = JSONObject.toJSONString(map);
        System.out.println(s);

        JSONArray ja = new JSONArray();
        ja.add(js);
        ja.add(js1);
        String s2 = JSON.toJSONString(ja);
        System.out.println(s2);
        JSONArray ja1 = JSON.parseArray(s2);
        System.out.println(ja1);
    }

}
