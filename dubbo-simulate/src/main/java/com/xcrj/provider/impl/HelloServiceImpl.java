package com.xcrj.provider.impl;

import com.xcrj.provider.api.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String username) {
        return "Hello" + username;
    }
}
