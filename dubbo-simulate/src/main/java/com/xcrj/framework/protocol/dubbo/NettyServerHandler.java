package com.xcrj.framework.protocol.dubbo;

import com.xcrj.framework.Invocation;
import com.xcrj.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;

        Class serviceImpl = LocalRegister.get(invocation.getInterfaceName());
        Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParamType());
        Object result = method.invoke(serviceImpl.newInstance(), invocation.getParams());

        ctx.writeAndFlush("Netty: " + result);
    }
}
