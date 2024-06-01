package com.xcrj.provider;

import com.xcrj.api.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @RequestMapping(value = "service")
    public User test() {
        return providerService.getUser();
    }
}