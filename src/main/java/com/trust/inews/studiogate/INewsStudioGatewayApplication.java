package com.trust.inews.studiogate;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.trust.inews.studiogate.config.Config;
import com.trust.inews.studiogate.config.StudioProperties;
import com.trust.inews.studiogate.listener.FileListener;
import com.trust.inews.studiogate.session.SessionUtil;
import com.trust.inews.studiogate.tcpserver.TcpServer;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * inews串联单监听服务
 */
public class INewsStudioGatewayApplication {
    private static Logger logger = LoggerFactory.getLogger(INewsStudioGatewayApplication.class);

    public static void main(String[] args) {
        //read config properties
        if (!readProperties()) {
            logger.error("读取配置参数失败！请检查配置文件格式是否正确");
            return;
        }

        //set logger level
        changeLogLevel(Config.logLevel, Config.logPackage);

        //启动服务
        start();
    }

    /**
     * 启动服务
     */
    private static void start() {


        //主备服务连接
        Thread tcpServer = new Thread(new TcpServer(1111));
        tcpServer.start();
        try {
            //开启串联单监听器
            startFileListener();
        }catch (Exception e){
            logger.error("结束程序");
            System.exit(0);
        }


    }

    private static void startFileListener() {
        // 监控目录
        String inewsDir = Config.inewsDir;
        // 轮询间隔 秒
        long interval = TimeUnit.SECONDS.toMillis(Config.interval);
        // 创建过滤器 只监听文件
        IOFileFilter filter = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter());
        // 使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(inewsDir), filter){
            @Override
            public void destroy() throws Exception {
                super.destroy();
                logger.error("监听停止");
            }
        };
        //不使用过滤器
        //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileListener());
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        try {
            monitor.start();
        } catch (Exception e) {
            logger.error("文件夹监听开启失败", e);
        }
    }

    /**
     * 从配置文件读取配置信息
     *
     * @return true:成功， false:失败
     */
    private static Boolean readProperties() {
        Properties prop = StudioProperties.getProperties();
        if (null == prop) {
            return false;
        }
        return Config.setConfig(prop); //设置全局配置参数
    }

    /**
     * 动态修改日志级别
     *
     * @param level 日志级别
     * @param path  需改变级别的类路径，如root，com.trust
     */
    private static void changeLogLevel(String level, String path) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger log = loggerContext.getLogger(path);
        log.setLevel(Level.toLevel(level));
    }

}
