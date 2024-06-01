package com.xcrj.consumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtils {
    public static String get(String url) {
        // 创建 HttpClient 实例  
        HttpClient client = HttpClientBuilder.create().build();

        // 创建 HttpGet 请求  
        HttpGet request = new HttpGet(url);

        // 发送请求并获取响应  
        HttpResponse response = null;
        HttpEntity entity = null;
        try {
            response = client.execute(request);
            // 获取响应实体
            entity = response.getEntity();
            // 如果响应实体不为空，打印响应内容
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}