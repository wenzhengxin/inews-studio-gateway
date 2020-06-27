package com.trust.inews.studiogate.tcpclient;

import com.trust.inews.studiogate.config.Constant;
import com.trust.inews.studiogate.session.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Tcp client
 * 演播室网关作为客户端，连接ncs网关，
 * 客户端用来推送串联单播出状态等信息到ncs
 */
public class TcpClient implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TcpClient.class);
    private String host;
    private Integer port;
    private String name; //tcpClient客户端名称，用于在全局容器中存取连接通道，区分主备两个连接通道
    private Bootstrap bootstrap;

    public TcpClient(String host, Integer port, String name) {
        this.host = host;
        this.port = port;
        this.name = name;
    }

    @Override
    public void run() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ByteBuf delimiter = Unpooled.copiedBuffer(Constant.MOS_END_TAG.getBytes(Constant.UTF_16BE));
                        ChannelPipeline pl = ch.pipeline();
                        pl.addLast(new DelimiterBasedFrameDecoder(1024 * 1024 * 50, delimiter)); //按标识符拆包
                        pl.addLast(new StringDecoder(Constant.UTF_16BE)); //字符解码器
                        pl.addLast(new StringEncoder(Constant.UTF_16BE)); //字符编码器
                        pl.addLast(new ClientHandler()); //客户端handler
                        pl.addLast(new HeartbeatResponseHandler()); //处理服务端心跳回复
                    }
                });

        connect();
    }

    /**
     * 连接ncs网关服务器，连接失败时尝试重连
     */
    public void connect() {
        bootstrap.connect(host, port).addListener(future -> {
                if (future.isSuccess()) {
                    logger.info("TcpClient：连接服务器({}:{})成功!", host, port);
                    //在当前连接通道中绑定tcpClient对象，用于断线重连
                    Channel channel = ((ChannelFuture)future).channel();
                    channel.attr(Constant.TCP_CLIENT).set(this);
                    //在全局容器中保存当前连接
                    SessionUtil.putClientChannel(name, channel);
                } else {
                    // 尝试重连
                    logger.info("TcpClient：连接服务器({}:{})失败!正在尝试重连...", host, port);
                    bootstrap.config().group().schedule(this::connect,
                            3L, TimeUnit.SECONDS);
                }
            }
        );
    }

}
