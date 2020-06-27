package com.trust.inews.studiogate.listener;

import com.trust.inews.studiogate.bean.rocreate.*;
import com.trust.inews.studiogate.config.Config;
import com.trust.inews.studiogate.config.Constant;
import com.trust.inews.studiogate.httpclient.HttpClient;
import com.trust.inews.studiogate.session.NativeCache;
import com.trust.inews.studiogate.util.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件变化监听器
 * 监听文件的新增 修改 删除
 */
public class FileListener extends FileAlterationListenerAdaptor {
    private Logger logger = LoggerFactory.getLogger(FileListener.class);

    /**
     * 文件创建执行
     */
    public void onFileCreate(File file) {
        if (Constant.SLAVE.equals(Config.serverMode) && NativeCache.get(Constant.MASTER_STATUS).toString().equals(Constant.MASTER_ON)) {
            return;
        }
        logger.info("[新建]:" + file.getAbsolutePath());
        try {
            sendRundown(file);
        } catch (Exception e) {
            logger.error("监听服务奔溃了");
            System.exit(-1);
        }
    }

    /**
     * 文件修改执行
     */
    public void onFileChange(File file) {
        if (Constant.SLAVE.equals(Config.serverMode) && NativeCache.get(Constant.MASTER_STATUS).toString().equals(Constant.MASTER_ON)) {
            return;
        }
        logger.info("[修改]:" + file.getAbsolutePath());
        try {
            sendRundown(file);
        } catch (Exception e) {
            logger.error("监听服务奔溃了");
            System.exit(-1);
        }
    }

    /**
     * 文件删除执行
     */
    public void onFileDelete(File file) {
        if (Constant.SLAVE.equals(Config.serverMode) && NativeCache.get(Constant.MASTER_STATUS).toString().equals(Constant.MASTER_ON)) {
            return;
        }
        logger.info("[删除]:" + file.getAbsolutePath());
        try {
            sendRundown(file);
        } catch (Exception e) {
            logger.error("监听服务奔溃了");
            System.exit(-1);
        }
    }

    /**
     * 目录创建
     */
    public void onDirectoryCreate(File directory) {
    }

    /**
     * 目录修改
     */
    public void onDirectoryChange(File directory) {
    }

    /**
     * 目录删除
     */
    public void onDirectoryDelete(File directory) {
    }

    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }

    /**
     * 向文稿服务器发送更新命令
     */
    private void sendRundown(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile.isDirectory()) {
            RoCreateBean roCreateBean = null;
            roCreateBean = BeanUitls.getRoCreateBean(parentFile);
            if (roCreateBean == null) {
                return;
            }
            HttpClient.sendRoCreate(XmlUtil.beanToXml(roCreateBean));
        }
    }


}