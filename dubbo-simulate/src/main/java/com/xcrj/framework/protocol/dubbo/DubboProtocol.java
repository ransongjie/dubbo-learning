package com.xcrj.framework.protocol.dubbo;

import com.xcrj.framework.Invocation;
import com.xcrj.framework.Protocol;
import com.xcrj.framework.URL;

public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        return new NettyClient().send(url.getHostname(), url.getPort(), invocation);
    }
}
