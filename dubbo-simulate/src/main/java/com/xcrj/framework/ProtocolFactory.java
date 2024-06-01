package com.xcrj.framework;

import com.xcrj.framework.protocol.dubbo.DubboProtocol;
import com.xcrj.framework.protocol.http.HttpProtocol;

public class ProtocolFactory {
    public static Protocol getProtocol() {
        //模拟读取用户配置
        //jvm启动命令, VM Options, -DprotocolName=dubbo
        String name = System.getenv("protocolName");

        if (name == null || name.equals("")) name = "http";

        switch (name) {
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return new DubboProtocol();
            default:
                break;
        }

        return new HttpProtocol();
    }
}
