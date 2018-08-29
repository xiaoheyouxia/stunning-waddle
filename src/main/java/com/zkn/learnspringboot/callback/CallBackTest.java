package com.zkn.learnspringboot.callback;

/**
 * @description: 测试类
 * @author: lxh
 * @create: 2018-05-04 14:56
 **/
public class CallBackTest {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);
        client.sendMsg("hello");
    }
}
