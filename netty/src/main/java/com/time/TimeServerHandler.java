package com.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override

    //这里只 覆盖了channelActive（）方法，该方法成功建立连接时的回调。
    //因为，客户端一连接上服务端，服务端就给客户端发送一个时间过去，然后断开连接。
    public void channelActive(final ChannelHandlerContext ctx) {
        //一个整数是4个字节
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));
        //netty是非阻塞是的io，所以它会立刻返回一个ChannelFuture对象（无论数据是否传输完成），该对象可用于监听写操作是否完成
        final ChannelFuture f = ctx.writeAndFlush(time);
        //监听这个操作，等这个操作(ctx.writeAndFlush(time)将数据输出)完成之后，再调用close（）
        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}