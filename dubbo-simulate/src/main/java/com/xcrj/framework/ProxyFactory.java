package com.xcrj.framework;

import com.xcrj.framework.protocol.dubbo.DubboProtocol;
import com.xcrj.framework.protocol.dubbo.NettyClient;
import com.xcrj.framework.protocol.http.HttpClient;
import com.xcrj.framework.register.RemoteMapRegister;
import com.xcrj.provider.api.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    public static <T> T getProxy(final Class interfaceClass) {
        //用户配置 cglib、动态代理
        Object newProxyInstance = Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {//服务容错
                            //mock
//                            String mock = System.getenv("mock");
                            //force:123, 强制返回123
                            //return:123, 调用失败返回123
//                            if (mock != null && mock.startsWith("force:")) {
//                                String result = mock.replace("force:", "");
//                                return result;
//                            }

                            //构建发送数据
                            Invocation invocation = new Invocation(
                                    interfaceClass.getName(),
                                    method.getName(),
                                    method.getParameterTypes(),
                                    args);
                            //服务发现
                            List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());
                            //负载均衡
                            URL url = LoadBalance.random(urlList);

                            //发送http请求
//                        HttpClient httpClient = new HttpClient();
//                        String result = httpClient.send(url.getHostname(), url.getPort(), invocation);

                            //发送tcp请求
//                        NettyClient nettyClient = new NettyClient();
//                        String result = nettyClient.send(url.getHostname(), url.getPort(), invocation);

                            //再抽象为protocol
//                        Protocol protocol = new DubboProtocol();
//                        String result = protocol.send(url, invocation);

                            //工厂模式
                            Protocol protocol = ProtocolFactory.getProtocol();
                            String result = protocol.send(url, invocation);

                            return result;
                        } catch (Exception e) {
                            return "XXXX";//返回更友好的信息
                        }
                    }
                });

        return (T) newProxyInstance;
    }
}
