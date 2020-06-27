package com.trust.inews.studiogate.tcpclient;

import com.trust.inews.studiogate.config.Constant;
import com.trust.inews.studiogate.protocol.heartbeat.HeartbeatBean;
import com.trust.inews.studiogate.session.NativeCache;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


/**
 * 客户端连接处理类，
 * 1、连接成功后定时发送心跳信息
 * 2、掉线后，尝试定时重连
 */
@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NativeCache.put(Constant.MASTER_STATUS, Constant.MASTER_ON);
        //定时发送心跳
        scheduleSendHeartbeat(ctx);

        super.channelActive(ctx);
    }

    /**
     * 定时发送心跳
     */
    private void scheduleSendHeartbeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                HeartbeatBean heartbeatBean = new HeartbeatBean();
                logger.debug("TcpClient：发送心跳...");
                ctx.writeAndFlush(heartbeatBean);
                scheduleSendHeartbeat(ctx);
            }
        }, Constant.HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 网络异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("TcpClient：服务器异常！" + cause.getMessage());
        ctx.close();
    }

    /**
     * 掉线重连
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("TcpClient：主服务器掉线，备用服务器监听开启");
        NativeCache.put(Constant.MASTER_STATUS, Constant.MASTER_OFF);
        TcpClient tcpClient = ctx.channel().attr(Constant.TCP_CLIENT).get();
        tcpClient.connect();
    }

}
