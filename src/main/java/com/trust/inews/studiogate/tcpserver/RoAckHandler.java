package com.trust.inews.studiogate.tcpserver;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mos.roAck命令处理类
 * 打印回复信息，不做其他处理
 */
@ChannelHandler.Sharable
public class RoAckHandler extends SimpleChannelInboundHandler{
    private Logger logger = LoggerFactory.getLogger(RoAckHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
