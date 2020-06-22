package com.trust.inews.studiogate.config;

import org.apache.commons.lang.StringUtils;

import java.util.Properties;

/**
 * 全局配置信息实体类
 */
public class Config {
    public static String newsFilePath;//inews串联单存放路径
    public static Integer monitorInterval;//监听频率
    public static String newsColumn;//新闻栏目
    public static String serverMode;//运行状态：master主，slave备
    public static String masterHost;//运行状态：master主，slave备
    public static Integer masterPort;//运行状态：master主，slave备
    public static String httpHost; //演播室控制系统http服务ip
    public static Integer httpPort; //演播室控制系统http端口
    public static String httpHostBK; //演播室控制系统http服务ip
    public static Integer httpPortBK; //演播室控制系统http端口
    public static String logLevel; //日志等级 trace debug info warn error
    public static String logPackage; //需要设置日志等级的包名，如com.trust

    public static String title_tag_begin;
    public static String title_tag_end;

    public static String videoid_tag_begin;
    public static String videoid_tag_end;

    public static String pagenumber_tag_begin;
    public static String pagenumber_tag_end;

    public static String lanmu_tag_begin;
    public static String lanmu_tag_end;

    public static String storyid_tag_begin;
    public static String storyid_tag_end;

    public static String tongxunyuan_tag_begin;
    public static String tongxunyuan_tag_end;

    public static String writer_tag_begin;
    public static String writer_tag_end;

    public static String createdate_tag_begin;
    public static String createdate_tag_end;

    public static String totaltime_tag_begin;
    public static String totaltime_tag_end;

    public static String modifydate_tag_begin;
    public static String modifydate_tag_end;

    public static String kouboqian_tag_begin;
    public static String kouboqian_tag_end;

    public static String zhengwen_tag_begin;
    public static String zhengwen_tag_end;

    public static String koubohou_tag_begin;
    public static String koubohou_tag_end;

    /**
     * 设置全局参数，配置参数为空或格式错误时返回失败
     *
     * @param prop 配置文件
     * @return true：成功，false：失败
     */
    public static Boolean setConfig(Properties prop) {
        try {
            newsFilePath = prop.getProperty("news.file.path");
            newsColumn = prop.getProperty("news.column");
            serverMode = prop.getProperty("server.mode");
            masterHost = prop.getProperty("master.host");
            if (StringUtils.isNotBlank(prop.getProperty("master.port"))) {
                masterPort = Integer.parseInt(prop.getProperty("master.port"));
            }
            if (StringUtils.isNotBlank(prop.getProperty("monitor.interval"))) {
                monitorInterval = Integer.parseInt(prop.getProperty("monitor.interval"));
            }
            httpHost = prop.getProperty("http.host");
            if (StringUtils.isNotEmpty(prop.getProperty("http.port"))) {
                httpPort = Integer.parseInt(prop.getProperty("http.port"));
            }
            httpHostBK = prop.getProperty("http.host.bk");
            if (StringUtils.isNotEmpty(prop.getProperty("http.port.bk"))) {
                httpPortBK = Integer.parseInt(prop.getProperty("http.port.bk"));
            }
            logLevel = prop.getProperty("log.level");
            logPackage = prop.getProperty("log.package");

            title_tag_begin = prop.getProperty("title_tag_begin");
            title_tag_end = prop.getProperty("title_tag_end");
            videoid_tag_begin = prop.getProperty("videoid_tag_begin");
            videoid_tag_end = prop.getProperty("videoid_tag_end");
            pagenumber_tag_begin = prop.getProperty("pagenumber_tag_begin");
            pagenumber_tag_end = prop.getProperty("pagenumber_tag_end");
            lanmu_tag_begin = prop.getProperty("lanmu_tag_begin");
            lanmu_tag_end = prop.getProperty("lanmu_tag_end");
            storyid_tag_begin = prop.getProperty("storyid_tag_begin");
            storyid_tag_end = prop.getProperty("storyid_tag_end");
            tongxunyuan_tag_begin = prop.getProperty("tongxunyuan_tag_begin");
            tongxunyuan_tag_end = prop.getProperty("tongxunyuan_tag_end");
            writer_tag_begin = prop.getProperty("writer_tag_begin");
            writer_tag_end = prop.getProperty("writer_tag_end");
            createdate_tag_begin = prop.getProperty("createdate_tag_begin");
            createdate_tag_end = prop.getProperty("createdate_tag_end");
            totaltime_tag_begin = prop.getProperty("totaltime_tag_begin");
            totaltime_tag_end = prop.getProperty("totaltime_tag_end");
            modifydate_tag_begin = prop.getProperty("modifydate_tag_begin");
            modifydate_tag_end = prop.getProperty("modifydate_tag_end");
            kouboqian_tag_begin = prop.getProperty("kouboqian_tag_begin");
            kouboqian_tag_end = prop.getProperty("kouboqian_tag_end");
            zhengwen_tag_begin = prop.getProperty("zhengwen_tag_begin");
            zhengwen_tag_end = prop.getProperty("zhengwen_tag_end");
            koubohou_tag_begin = prop.getProperty("koubohou_tag_begin");
            koubohou_tag_end = prop.getProperty("koubohou_tag_end");

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
