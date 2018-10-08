package com.user_1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
//继承ByteToMessageDecoder，
//ByteToMessageDecoder也是一个ChannelInboundHandler
public class UserDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        //读取字节
        in.readBytes(bytes);
        //反序列化
        Object obj = ByteObjConverter.byteToObject(bytes);
        //交给下一个handler
        out.add(obj);
    }
}
