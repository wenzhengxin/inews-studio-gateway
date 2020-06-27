package com.trust.inews.studiogate;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.trust.inews.studiogate.bean.rocreate.RoCreateBean;
import com.trust.inews.studiogate.config.Config;
import com.trust.inews.studiogate.config.Constant;
import com.trust.inews.studiogate.config.StudioProperties;
import com.trust.inews.studiogate.httpclient.HttpClient;
import com.trust.inews.studiogate.listener.FileListener;
import com.trust.inews.studiogate.session.NativeCache;
import com.trust.inews.studiogate.tcpclient.TcpClient;
import com.trust.inews.studiogate.tcpserver.TcpServer;
import com.trust.inews.studiogate.util.BeanUitls;
import com.trust.inews.studiogate.util.XmlUtil;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
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
        //主
        if (Constant.MASTER.equals(Config.serverMode)){
            //主备服务连接
            Thread tcpServer = new Thread(new TcpServer(Config.masterPort));
            tcpServer.start();
        }
        //备
        if (Constant.SLAVE.equals(Config.serverMode)){
            NativeCache.put(Constant.MASTER_STATUS, Constant.MASTER_OFF);
            //主备服务连接
            Thread tcpClient = new Thread(new TcpClient(Config.masterHost, Config.masterPort, Constant.TCP_CLIENT_NAME));
            tcpClient.start();
        }
        // 监控目录
        String[] inewsDirs = Config.newsFilePath.split("；");
        if (inewsDirs == null || inewsDirs.length == 0){
            logger.error("请配置串联单目录");
        }

        for (String dir : inewsDirs){
            //开启串联单监听器
            startFileListener(dir);
        }
        if (Constant.SLAVE.equals(Config.serverMode)){
            return;
        }
        for (String dir : inewsDirs){
            File file = new File(dir);
            if (file.isDirectory()){
                File[] files = file.listFiles();
                if (files == null){
                    return;
                }
                RoCreateBean roCreateBean = null;
                try {
                    roCreateBean = BeanUitls.getRoCreateBean(file);
                } catch (IOException e) {
                    logger.error("解析串联单失败",e);
                }
                if (roCreateBean == null){
                    continue;
                }
                HttpClient.sendRoCreate(XmlUtil.beanToXml(roCreateBean));
            }

        }

    }

    private static void startFileListener(String inewsDir) {
        // 轮询间隔 秒
        long interval = TimeUnit.SECONDS.toMillis(Config.monitorInterval);
        // 创建过滤器 只监听文件
        IOFileFilter filter = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter());
        // 使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(inewsDir), filter);
        //不使用过滤器
        //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileListener());
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        try {
            monitor.start();
        } catch (Exception e) {
            logger.error("文件夹失败", e);
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
