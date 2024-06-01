package com.xcrj.service;

import com.xcrj.User;
import com.xcrj.UserRequest;
import com.xcrj.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;
//import org.springframework.stereotype.Service;

@DubboService(version = "1.0")
//@DubboService
//@Service
public class UserServiceImpl implements UserService {
    //    @Override
    public String getUser() {
        return "xcrj";
    }

//    @Override
//    public User getUser(UserRequest request) {
//        return User.newBuilder().setUid("1").setUsername("xcrj").build();
//    }

    //ServerStream
    @Override
    public void sayHiServerStream(String name, StreamObserver<String> response) {
        response.onNext("hi: " + name);//服务端给客户端响应多次
        response.onNext("hello: " + name);//服务端给客户端响应多次
        response.onCompleted();//服务端告诉客户端无响应了
    }

    //bi-stream/client stream
    @Override
    public StreamObserver<String> sayHiBIStream(StreamObserver<String> response){
        return new StreamObserver<String>(){
            @Override
            public void onNext(String data){
                System.out.println("客户端发送的数据："+data);

                //服务端给客户端发送2次数据
                response.onNext(data);
                response.onNext(data);

                response.onCompleted();
            }
            @Override
            public void onCompleted(){
                System.out.println("结束");
            }
            @Override
            public void onError(Throwable throwable){
            }
        };
    }
}
