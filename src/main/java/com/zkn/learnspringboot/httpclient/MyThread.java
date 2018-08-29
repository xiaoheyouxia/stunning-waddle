package com.zkn.learnspringboot.httpclient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;


public class MyThread implements Runnable{
    private String web_name;
    private String IP_file;
    private HttpClient client = new HttpClient();
    private String str=null;
    private FileReader reader;
    private BufferedReader br;
    private Map<String,String> map =new HashMap<String,String>();

    public MyThread(String web_name,String IP_file)
    {
        this.web_name=web_name;
        this.IP_file=IP_file;
    }

    public void run()
    {

        try {
            reader=new FileReader(IP_file);
            br=new BufferedReader(reader);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }//读取代理IP地址
        try {
            while((str=br.readLine())!=null)//格式写成IP+端口
            {
                String result[]=str.split("@");
                String info[]=result[0].split(":");
                map.put(info[0], info[1]);
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Set<String> set=map.keySet();
        Iterator<String> iter=set.iterator();

        //设置超时时间
        client.setTimeout(10000);
        client.setConnectionTimeout(10000);
        //设置cookie管理策略
        client.getState().setCookiePolicy(CookiePolicy.COMPATIBILITY);

        while(iter.hasNext())//不断的取出IP和端口
        {
            String key=iter.next();
            System.out.println(key+":"+map.get(key));
            try
            {
                //设置代理服务器地址和端口
                client.getHostConfiguration().setProxy(key,Integer.parseInt(map.get(key)));
                //这是用刷的是唱吧的访问量，亲测有效，只不过效率不是怎么高，因为代理服务器有快有慢，所以设置了超时机制。
                //而且，貌似一个IP不能连续刷多首歌曲，所以就只能附带刷刷别的网页好了。

            }catch(Exception e)
            {
                e.printStackTrace();
            }
            HttpMethod method = new PostMethod(web_name);
            try
            {
                System.out.println(IP_file);
                client.executeMethod(method);

                //System.out.println(method.getStatusLine());
                //System.out.println(method.getResponseBodyAsString());
                method.releaseConnection();

            }catch(Exception e)
            {
                e.printStackTrace();

            }
        }
    }
}