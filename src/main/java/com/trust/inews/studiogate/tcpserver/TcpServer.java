package com.trust.inews.studiogate.tcpserver;

import com.trust.inews.studiogate.config.Constant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * inews监听服务，主备服务
 */
public class TcpServer implements Runnable {
    private Logger logger = LoggerFactory.getLogger(TcpServer.class);
    private Integer port;

    public TcpServer(Integer port) {
        this.port = port;
    }

    /**
     * 启动主服务器
     */
    @Override
    public void run() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128) //可连接、未连接客户队列长度
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //开启TCP底层心跳机制，定时检查连接是否存活
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer(Constant.MOS_END_TAG.getBytes(Constant.UTF_16BE));
                            ChannelPipeline pl = ch.pipeline();
                            pl.addLast(new DelimiterBasedFrameDecoder(1024 * 1024 * 50, delimiter)); //按标识符拆包
                            pl.addLast(new StringDecoder(Constant.UTF_16BE)); //字符解码器
                            pl.addLast(new StringEncoder(Constant.UTF_16BE)); //字符编码器
                            pl.addLast(new HeartbeatHandler()); //处理心跳
                            pl.addLast(new RoAckHandler()); //处理roAck回复信息
                            pl.addLast(new ServerHandler()); //主要处理连接异常，并关闭连接
                        }
                    });
            ChannelFuture f = bootstrap.bind(this.port).sync();
            logger.info("TcpServer：服务已启动，端口号：{}", this.port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("TcpServer：服务启动异常! {} ", e.getMessage());
        } finally {
            //释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
