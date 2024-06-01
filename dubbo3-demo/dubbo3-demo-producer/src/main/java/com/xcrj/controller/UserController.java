//package com.xcrj.controller;

import com.xcrj.service.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
//dubbo不再对外提供http服务
//@RestController
//public class UserController {
//    @Resource
//    private UserServiceImpl userServiceImpl;
//
//    @GetMapping("/user")
//    public String getUser() {
//        return userServiceImpl.getUser();
//    }
//}
