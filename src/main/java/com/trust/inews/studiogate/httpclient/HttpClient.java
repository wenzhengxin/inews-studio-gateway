package com.trust.inews.studiogate.httpclient;

import com.trust.inews.studiogate.config.Config;
import com.trust.inews.studiogate.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 演播室系统http 客户端
 * 向演播室系统发送串联单新增和串联单更新
 * 发送失败时，自动向备份服务器发送
 */
public class HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
    private static final String HTTP_PROTOCOL = "http://";
    private static final String RO_CREATE_URL = "/api/v1/external/broadcast/roCreate";
    private static final String RO_ELEMENT_ACTION_URL = "/api/v1/external/broadcast/roElementAction";

    /**
     * 发送串联单信息新建到演播室服务器
     */
    public static String sendRoCreate(String params) {
        String httpUrl = HTTP_PROTOCOL + Config.httpHost + ":" + Config.httpPort + RO_CREATE_URL;
        String response = doSend(httpUrl, params);
        if (null == response) {
            //发送失败，发送到备份服务器
            logger.error("TcpServer: 向演播室控制服务器发送信息失败，尝试向备份服务器发送信息...");
            response = sendRoCreateToBackupServer(params);
        }
        return response;
    }

    /**
     * 发送串联单编辑信息到演播室服务器
     */
    public static String sendRoElementAction(String params) {
        String httpUrl = HTTP_PROTOCOL + Config.httpHost + ":" + Config.httpPort + RO_ELEMENT_ACTION_URL;
        String response = doSend(httpUrl, params);
        if (null == response) {
            //发送失败，发送到备份服务器
            logger.error("TcpServer: 向演播室控制服务器发送信息失败，尝试向备份服务器发送信息...");
            response = sendRoElementActionToBackupServer(params);
        }
        return response;
    }

    /**
     * 发送串联单新建信息到备份演播室服务器
     */
    private static String sendRoCreateToBackupServer(String params) {
        String httpUrl = HTTP_PROTOCOL + Config.httpHostBK + ":" + Config.httpPortBK + RO_CREATE_URL;
        return doSend(httpUrl, params);
    }

    /**
    * 发送串联单编辑信息到演播室服务器
     */
    private static String sendRoElementActionToBackupServer(String params) {
        String httpUrl = HTTP_PROTOCOL + Config.httpHostBK + ":" + Config.httpPortBK + RO_ELEMENT_ACTION_URL;
        return doSend(httpUrl, params);
    }

    /**
     * 执行发送信息到演播室服务器
     */
    private static String doSend(String httpUrl, String params) {
        return HttpUtil.sendHttpPostXml(httpUrl, params);
    }
}
