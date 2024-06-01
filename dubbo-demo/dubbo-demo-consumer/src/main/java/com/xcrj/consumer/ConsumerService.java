package com.xcrj.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson2.JSONObject;
import com.xcrj.api.ProviderServiceInterface;
import com.xcrj.api.User;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService{
    @Reference
    private ProviderServiceInterface providerService;

    public String test(){
        //自己发送http请求的方式
        String result= HttpClientUtils.get("http://localhost:9094/provider/service");
        User user= JSONObject.parseObject(result, User.class);
        return user.getUsername();

        //dubbo方式
        // User user=providerService.getUser();
        // return user.getUsername();
    }

    public String dubbo2(){
        //dubbo方式
        User user=providerService.getUser();
        return user.getUsername();
    }
}