package com.xcrj.controller;

import com.xcrj.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order")
    public String getOrder() {
        return orderService.getOrder();
    }

    @GetMapping("/dubbo")
    public String getOrder2() {
        return orderService.getOrderByDubbo();
    }

    @GetMapping("/server_stream")
    public String getOrder3() {
        return orderService.getOrderServerStream();
    }

    @GetMapping("/bi_stream")
    public String getOrder4() {
        return orderService.getOrderBIStream();
    }
}
