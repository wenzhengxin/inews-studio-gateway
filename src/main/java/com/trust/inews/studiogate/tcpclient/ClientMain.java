package com.trust.inews.studiogate.tcpclient;

import com.trust.inews.studiogate.config.Constant;
import com.trust.inews.studiogate.config.Config;
import com.trust.inews.studiogate.config.StudioProperties;
import com.trust.inews.studiogate.httpclient.HttpClient;
import com.trust.inews.studiogate.session.SessionUtil;
import io.netty.channel.Channel;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * tcp client test
 */
public class ClientMain {
    public static void main(String[] args) {

        testTcpServer();

        //testHttpServer
    }

    /**
     * test tcp server
     */
    private static void testTcpServer() {
        String ncsHost = "localhost";
        Integer ncsPort = 1111;
        TcpClient tcpClient = new TcpClient(ncsHost, ncsPort, Constant.TCP_CLIENT_NAME);
        Thread thread = new Thread(tcpClient);
        thread.start();
//        try {
//            Thread.sleep(2000);
//            String xml = "";
//            ConcurrentHashMap<String, Channel> clientChannel = SessionUtil.clientChannel;
//            io.netty.channel.Channel channel = SessionUtil.getClientChannel(Constant.TCP_CLIENT_NAME);
//            channel.writeAndFlush(xml);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

    }

    /**
     * test http server
     */
    private static void testHttpServer() {
        if (!readProperties()) {
            System.out.println("读取配置参数失败！请检查配置文件格式是否正确");
            return;
        }

        String xml = "";
        String res =  HttpClient.sendRoCreate(xml);
        System.out.println(res);

    }

    /**
     * 从配置文件读取配置信息
     * @return true:成功， false:失败
     */
    private static Boolean readProperties() {
        Properties prop = StudioProperties.getProperties();
        if (null == prop) {
            return false;
        }
        return Config.setConfig(prop); //设置全局配置参数
    }
}
