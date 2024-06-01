package com.xcrj.framework.protocol.http;

import com.xcrj.framework.Invocation;
import com.xcrj.framework.Protocol;
import com.xcrj.framework.URL;

public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new HttpServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new HttpClient().send(url.getHostname(), url.getPort(), invocation);
    }
}
