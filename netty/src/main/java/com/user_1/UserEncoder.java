package com.user_1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
//继承MessageToByteEncoder，泛型是我们要船体的java对象类型
//MessageToByteEncoder也是一个HandlerAdaptor
public class UserEncoder extends MessageToByteEncoder<User> {
    @Override
    //这里直接将我们要传的User对象当作参数传了进来
    protected void encode(ChannelHandlerContext ctx, User user, ByteBuf out) throws Exception {
        //序列化该User
        byte[] datas = ByteObjConverter.objectToByte(user);
        //传递到下一个Handler
        out.writeBytes(datas);
        //强制刷入
        ctx.flush();
    }
}
