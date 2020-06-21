package com.trust.inews.studiogate.tcpserver;

import com.trust.inews.studiogate.protocol.heartbeat.HeartbeatBean;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * mos Heartbeat命令处理器
 * 响应客户端心跳，回复心跳数据
 */
@ChannelHandler.Sharable
public class HeartbeatHandler extends SimpleChannelInboundHandler<HeartbeatBean> {
    private Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HeartbeatBean bean) {

    }
}
