package com.xcrj.framework.protocol.dubbo;

import com.xcrj.framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

//需要等待结果，NettyClientHandler实现任务接口
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context;
    private Invocation invocation;
    private String result;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.context = ctx;
    }

    //synchronized 方法，否则多个线程都进入该方法notify，而无wait线程了
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = (String) msg;
        notify();//收到响应唤醒线程
    }

    //synchronized 方法
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(this.invocation);
        wait();//发送完后阻塞线程
        return result;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }
}
