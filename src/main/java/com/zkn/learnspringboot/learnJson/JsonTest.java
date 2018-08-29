package com.zkn.learnspringboot.learnJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void jsonTest(){
        String json = "{\n" +
                "\t\"Certificates\": [{\n" +
                "\t\t\"certificateName\": \"笑\",\n" +
                "\t\t\"imgAdd\": \"http://192.168.210.110/group1/M00/08/A9/wKjScFpZwg-AFyNWAAEu5QEVPnc861.jpg\"\n" +
                "\t}, {\n" +
                "\t\t\"certificateName\": \"222\",\n" +
                "\t\t\"imgAdd\": \"http://192.168.210.110/group1/M00/08/AB/wKjScVpZwheAYHvFAACZn_AnScc061.jpg\"\n" +
                "\t}, {\n" +
                "\t\t\"certificateName\": \"哎哟\",\n" +
                "\t\t\"imgAdd\": \"http://192.168.210.110/group1/M00/08/B6/wKjScVpd1GCAY6omAACcYRp1Fco646.png\"\n" +
                "\t}, {\n" +
                "\t\t\"certificateName\": \"小腹婆\",\n" +
                "\t\t\"imgAdd\": \"http://192.168.210.110/group1/M00/08/BF/wKjScVpglE6ASwimAAENSqmpW6s353.jpg\"\n" +
                "\t}],\n" +
                "\t\"Resume\": [{\n" +
                "\t\t\"position\": \"1\",\n" +
                "\t\t\"professionGrade\": \"2\",\n" +
                "\t\t\"workExp\": \"1\"\n" +
                "\t}, {\n" +
                "\t\t\"position\": \"1\",\n" +
                "\t\t\"professionGrade\": \"1\",\n" +
                "\t\t\"workExp\": \"1\"\n" +
                "\t}],\n" +
                "\t\"belongCityId\": \"江苏省徐州市贾汪区\",\n" +
                "\t\"certificationImgAddr\": \"http://192.168.210.110/group1/M00/08/B9/wKjScFpf_X2AUus2AAENSqmpW6s304.jpg\",\n" +
                "\t\"certificationNumber\": \"430521191110091111\",\n" +
                "\t\"phoneNo\": \"18767118639\",\n" +
                "\t\"qq\": \"11223\",\n" +
                "\t\"serviceId\": \"0000000022BA3CF4791\",\n" +
                "\t\"serviceName\": \"反卫栖\",\n" +
                "\t\"serviceProductName\": \"代理记账\",\n" +
                "\t\"sex\": \"2\",\n" +
                "\t\"wechat\": \"22334\"\n" +
                "}";

        HashMap parse = JSON.parseObject(json,HashMap.class);
        Object certificates = parse.get("Certificates");
        List<HashMap> objects = JSON.parseArray(certificates.toString(),HashMap.class);
        for (HashMap list: objects) {
            System.out.println(list.get("imgAdd"));
        }
        System.out.println(parse);
        System.out.println(certificates);
        System.out.println(objects);
    }

    @Test
    public void test(){
       /* String i = "propertyValue10";
        i = i.substring(13);
        System.out.println(i);*/
       List<HashMap<String,Object>> l= new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> objectObjectHashMap1 = new HashMap<>();
        HashMap<String, Object> objectObjectHashMap2 = new HashMap<>();
        HashMap<String, Object> objectObjectHashMap3 = new HashMap<>();
        HashMap<String, Object> objectObjectHashMap4 = new HashMap<>();
        objectObjectHashMap1.put("1","a");
        objectObjectHashMap2.put("2","b");
        objectObjectHashMap3.put("3","c");
        objectObjectHashMap4.put("4","d");
        l.add(objectObjectHashMap1);
        l.add(objectObjectHashMap2);
        l.add(objectObjectHashMap3);
        l.add(objectObjectHashMap4);
        if(l.contains("1")){
            System.out.println(true);
        }

       /*String[] aa= new String[]{};
        ArrayList<String> strings = new ArrayList<>();
        strings.add("12");
        strings.add("11");
        strings.add("3");
        Collections.sort(strings);
        for (int i = 0; i < strings.size(); i++) {
            System.out.println(strings.get(i));
        }*/
    }

    @Test
    public void test3(){
        org.json.JSONObject jsonObject = new org.json.JSONObject(("{}"));
        if(jsonObject.toString().equals("{}")){
            System.out.println("t");
        }else{
            System.out.println("f");
        }
    }
}
