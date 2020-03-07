package com.waylau.netty.demo.echo;

import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.TimeUnit;

/**
 * 处理服务端 channel.
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String serverEcho = null;
        try {
            System.out.println(ctx.channel().remoteAddress()+"->Server :"+ msg.toString());

            serverEcho = msg + " from server\n";
            ctx.write(serverEcho);
        } finally {
//            if (msg != null) {
//                ReferenceCountUtil.release(msg);
//            }
//            if (serverEcho != null) {
//                ReferenceCountUtil.release(serverEcho);
//            }
        }

//        ctx.write(msg); // (1)
//        ctx.flush(); // (2)
//        final ChannelFuture future = ctx.writeAndFlush(msg);
//        final ChannelFuture future = ctx.write(msg + "\n");
//        future.addListener(ChannelFutureListener.CLOSE);
//        future.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                System.out.println("close channel");
//                future.channel().close();
//            }
//        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("server read complete");
        ctx.flush();
//        TimeUnit.MILLISECONDS.sleep(200);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}