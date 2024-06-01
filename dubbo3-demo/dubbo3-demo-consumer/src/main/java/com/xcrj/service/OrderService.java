package com.xcrj.service;

import com.xcrj.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderService {
    @Resource
    private RestTemplate restTemplate;

    @DubboReference(version = "1.0")
    private UserService userService;

    //restTemplate调用方式
    public String getOrder() {
        String result = restTemplate.getForObject("http://localhost:8080/user", String.class);
        return result;
    }

    //dubbo调用方式
    public String getOrderByDubbo() {
        return userService.getUser();
    }

    //流式通信 server stream
    public String getOrderServerStream() {
        userService.sayHiServerStream("hi", new StreamObserver<String>() {
            //服务端每响应1次数据，触发这个方法
            @Override
            public void onNext(String data) {
                System.out.println("来自服务端的数据: " + data);
            }

            @Override
            public void onCompleted() {
                System.out.println("结束");
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        return "server stream";
    }

    //流式通信 bi stream
    public String getOrderBIStream() {
        //获得服务端 StreamObserver
        StreamObserver<String> streamObserver = userService.sayHiBIStream(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("服务端发送的数据："+data);
            }

            @Override
            public void onCompleted() {
                System.out.println("结束");
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
        //发送给服务端
        streamObserver.onNext("1");
        streamObserver.onNext("2");
        streamObserver.onNext("3");

        streamObserver.onCompleted();

        return "bi stream";
    }
}
