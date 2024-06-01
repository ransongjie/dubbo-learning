package com.xcrj.consumer;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("consumer")
public class ConsumerController{
    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value="user")
    public String getUsername(){
        return consumerService.test();
    }

    @RequestMapping(value="dubbo")
    public String getUsername2(){
        return consumerService.dubbo2();
    }
}
