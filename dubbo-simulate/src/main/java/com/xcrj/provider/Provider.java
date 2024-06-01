package com.xcrj.provider;

import com.xcrj.framework.Protocol;
import com.xcrj.framework.ProtocolFactory;
import com.xcrj.framework.URL;
import com.xcrj.framework.protocol.dubbo.DubboProtocol;
import com.xcrj.framework.protocol.dubbo.NettyServer;
import com.xcrj.framework.protocol.http.HttpServer;
import com.xcrj.framework.register.LocalRegister;
import com.xcrj.framework.register.RemoteMapRegister;
import com.xcrj.provider.api.HelloService;
import com.xcrj.provider.impl.HelloServiceImpl;

public class Provider {
    /**
     * 1. RPC调用与传统HTTP调用的区别
     * 2. Dubbo的基本用法
     * 3. Dubbo的服务暴露基本原理
     * 4. Dubbo的服务注册基本原理
     * 5. Dubbo的服务调用基本原理
     * 6. Dubbo的服务容错基本原理
     * 7. Dubbo的服务重试基本原理
     */
    public static void main(String[] args) {
        /**
         * 启动provider的过程: 网络传递Invocation对象
         * 1. 接收请求：启动Tomcat》加入DispatcherServlet到tomcat容器中》
         * 2. 获取请求：DispatcherServlet.service()方法处理请求》交给HttpServerHandler.handler()方法处理
         * 3. 解析请求：Invocation对象（接口名、方法名、方法参数类型列表，方法参数值列表，version）
         * 4. 根据接口名找到具体实现类：遍历实现类性能低，本地注册LocalRegister，启动httpServer时就进行本地注册
         * 5. 根据方法名 参数类型列表找到方法
         * 6. 反射调用方法
         */

        //本地注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        //远程注册
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.regist(HelloService.class.getName(), url);

        //启动HttpServer
//        HttpServer httpServer = new HttpServer();
//        httpServer.start(url.getHostname(), url.getPort());

        //启动NettyServer
//        NettyServer nettyServer = new NettyServer();
//        nettyServer.start(url.getHostname(), url.getPort());

        //再抽象为Protocol
//        Protocol protocol = new DubboProtocol();
//        protocol.start(url);

        //工厂模式
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
