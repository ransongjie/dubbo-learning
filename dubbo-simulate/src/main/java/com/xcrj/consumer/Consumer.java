package com.xcrj.consumer;

import com.xcrj.framework.Invocation;
import com.xcrj.framework.ProxyFactory;
import com.xcrj.framework.protocol.http.HttpClient;
import com.xcrj.provider.api.HelloService;

public class Consumer {
    public static void main(String[] args) {
        /**
         * 1. 构建内容
         * 2. 发送http请求
         * 3. 隐藏http请求发送的代码：dubbo直接调用接口的方法
         */

        //发送http请求。被放到 ProxyFactory 中
//        HttpClient httpClient = new HttpClient();
//        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Class[]{String.class}, new Object[]{"xcrj"});
//        String result = httpClient.send("localhost", 8080, invocation);
//        System.out.println(result);

        //dubbo方式，Interface Service 被动态代理，ProxyFactory
        //进一步，可以将代理类放入IOC容器，从IOC容器中获得代理对象，再调用方法
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        //动态代理，sayHello()方法是代理对象的sayHello方法
        String result = helloService.sayHello(" xcrj dubbo");
        System.out.println(result);
    }
}
