package com.trust.inews.studiogate.config;

import com.trust.inews.studiogate.tcpclient.TcpClient;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 全局常量定义
 */
public class Constant {
    //通讯字符编码
    public static final Charset UTF_16BE = StandardCharsets.UTF_16BE;
    //MOS协议结束标识符
    public static final String MOS_END_TAG = "</mos>";
    //心跳间隔时长,秒
    public static final Integer HEARTBEAT_INTERVAL = 10;
    //tcpClient主连接通道名称，用于在容器中标识主tcpClient连接
    public static final String TCP_CLIENT_NAME = "tcpClient";
    //tcpClient备份连接通道名称，用于在容器中标识主tcpClient连接
    public static final String TCP_CLIENT_BK_NAME = "tcpClientBK";
    //AttributeKey tcpClient，在channel中保存客户端对象，用于断线重连
    public static final AttributeKey<TcpClient> TCP_CLIENT = AttributeKey.newInstance("tcpClient");

}