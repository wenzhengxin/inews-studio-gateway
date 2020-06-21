package com.trust.inews.studiogate.tcpclient;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理服务端ack回复
 */
@ChannelHandler.Sharable
public class RoAckHandler extends SimpleChannelInboundHandler {
    private Logger logger = LoggerFactory.getLogger(RoAckHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
