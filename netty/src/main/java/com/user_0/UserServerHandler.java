package com.user_0;

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
        //这里可以是任意自定义对象
        User user = new User(1,"jing",123,"xxxxxx");
        //序列化位byte数组
        byte[] datas = ByteObjConverter.objectToByte(user);
        //写入channel
        ChannelFuture future = ctx.writeAndFlush(datas);
        //监听
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                ctx.close();
            }
        });
    }
}
