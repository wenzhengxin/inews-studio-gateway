package com.trust.inews.studiogate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 读取全局配置信息
 */
public class StudioProperties {
    private static Logger logger = LoggerFactory.getLogger(StudioProperties.class);
    private static final String PROPERTIES_FILE = "config.properties";
    private static Properties prop;

    /**
     * 从配置文件config.properties获取配置信息，优先从运行当前目录（jar包同级目录）
     * 获取配置文件, 当前目录无配置文件的，从class path路径（jar包内）获取配置文件
     * @return Properties 配置信息，获取失败时返回null
     */
    public static Properties getProperties() {
        if (null != prop) {
            return prop;
        }

        //从当前路径获取配置文件
        String path = getPathFromUserDir();
        prop = getPropertiesByPath(path);
        if (null != prop) {
            logger.info("从当前路径读取配置文件");
            return prop;
        }

        //从class path获取配置文件
        if (isJarRun()) {
            //jar包运行方式，从jar包中获取配置文件
            prop = getPropertiesByJar();
            logger.info("从jar包读取配置文件");
            return prop;
        }
        //非jar包运行(idea运行)，从class path获取配置
        path = getPathFromClassPath();
        prop = getPropertiesByPath(path);
        logger.info("从class path读取配置文件");
        return prop;
    }

    /**
     * 判断是否jar包运行
     */
    private static Boolean isJarRun() {
        URL url = Object.class.getResource("/" + PROPERTIES_FILE);
        return url.toString().startsWith("jar:");
    }

    /**
     * 从当前运行路径中获取配置文件路径
     */
    private static String getPathFromUserDir() {
        return System.getProperty("user.dir") + File.separator + PROPERTIES_FILE;
    }

    /**
     * 从class path路径中获取配置文件路径
     */
    private static String getPathFromClassPath() {
        try {
            String path = Object.class.getResource("/" + PROPERTIES_FILE).getPath();
            path = URLDecoder.decode(path,"utf-8"); //为了解决中文路径问题
            return path;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过配置文件路径，获取配置文件信息
     */
    private static Properties getPropertiesByPath(String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            return getPropertiesByInputStream(in);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从jar包中获取配置文件
     */
    private static Properties getPropertiesByJar() {
        InputStream in = Object.class.getResourceAsStream("/" + PROPERTIES_FILE);
        return getPropertiesByInputStream(in);
    }

    /**
     * 通过配置文件InputStream，获取配置文件信息
     */
    private static Properties getPropertiesByInputStream(InputStream in) {
        try {
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (Exception e) {
            return null;
        }
    }
}

