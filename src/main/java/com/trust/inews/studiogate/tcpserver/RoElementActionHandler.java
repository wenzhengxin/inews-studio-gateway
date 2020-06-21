package com.trust.inews.studiogate.tcpserver;


import com.trust.inews.studiogate.util.XmlUtil;
import com.trust.inews.studiogate.httpclient.HttpClient;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mos.roElementAction命令处理类
 */
@ChannelHandler.Sharable
public class RoElementActionHandler extends SimpleChannelInboundHandler {
    private Logger logger = LoggerFactory.getLogger(RoElementActionHandler.class);



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
