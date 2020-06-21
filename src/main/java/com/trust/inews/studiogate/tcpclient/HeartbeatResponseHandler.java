package com.trust.inews.studiogate.tcpclient;

import com.trust.inews.studiogate.protocol.heartbeat.HeartbeatBean;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理服务端心跳回复
 */
@ChannelHandler.Sharable
public class HeartbeatResponseHandler extends SimpleChannelInboundHandler<HeartbeatBean> {
    private Logger logger = LoggerFactory.getLogger(HeartbeatResponseHandler.class);

    /**
     * 收到服务端心跳回复，打印信息，不做其他处理
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, HeartbeatBean bean) {
        logger.debug("TcpClient：收到心跳回复...");
    }
}
