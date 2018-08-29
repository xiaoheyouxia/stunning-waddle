package com.zkn.learnspringboot.callback;

/**
 * @description: Client
 * @author: lxh
 * @create: 2018-05-04 14:52
 **/
public class Client {
    Server server;

    public Client(Server server) {
        this.server = server;
    }

    public void sendMsg(final String msg) {
        System.out.println("客户端正在发生消息：" + msg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                //调用server类的获取消息方法，并且传入myCallback对象
                    server.getMsg(new myCallback(), msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //实现Callback接口
    private class myCallback implements Callback {
        @Override
        public void process(int status) {
            System.out.println("处理成功，返回状态为：" + status);
        }
    }
}
