package com.xcrj;

import org.apache.dubbo.common.stream.StreamObserver;

public interface UserService {
    //流式通信 UNARY
    public String getUser();

    //SERVER_STREAM 能响应客户端多次
    default void sayHiServerStream(String name, StreamObserver<String> response) {
    }

    //CLIENT_STREAM/BI_STREAM 既能发送多次，也能响应多次
    default StreamObserver<String> sayHiBIStream(StreamObserver<String> response) {
        return response;
    }
}
