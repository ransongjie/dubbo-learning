package com.xcrj.provider;

import com.xcrj.api.ProviderServiceInterface;
// import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Service;
import com.xcrj.api.User;

@Service
public class ProviderService implements ProviderServiceInterface {
	public User getUser(){
		return new User("xcrj");
	}
}