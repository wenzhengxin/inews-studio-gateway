package com.trust.inews.studiogate.tcpserver;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * tpc server 处理类
 * 处理网络异常，如客户端断开连接，关闭channel通道
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(ServerHandler.class);
    /**
     * 网络异常处理，并关闭channel通道
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("TcpServer：连接异常！" + cause.getMessage());
        ctx.close();
    }
}
