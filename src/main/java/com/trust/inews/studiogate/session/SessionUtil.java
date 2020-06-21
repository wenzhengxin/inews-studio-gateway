package com.trust.inews.studiogate.session;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * session相关工具类，存储全局连接通道
 */
public class SessionUtil {
    //tcp客户端连接通道全局容器，保存当前有效tcp连接，"tcpClient"连接到主服务器，“tcpClientBK”连到到备份服务器
    public static ConcurrentHashMap<String, Channel> clientChannel = new ConcurrentHashMap<>();

    /**
     * 保存tcp 客户端连接通道
     */
    public static void putClientChannel(String clientName, Channel channel) {
        clientChannel.put(clientName, channel);
    }

    /**
     * 获取tcp客户端连接通道
     */
    public static Channel getClientChannel(String clientName) {
        return clientChannel.get(clientName);
    }

    /**
     * 删除tcp客户端连接通道
     */
    public static void removeClientChannel(String clientName) {
        clientChannel.remove(clientName);
    }

}
