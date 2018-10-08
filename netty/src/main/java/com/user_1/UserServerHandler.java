package com.user_1;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class UserServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        System.out.println("已经成功过连接上 ");
        User user = new User(1,"jing",123,"xxxxxx");
        ChannelFuture future = ctx.writeAndFlush(user);
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                ctx.close();
            }
        });
    }
}
