package com.trust.inews.studiogate.util;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.Date;

public class FileUtils {

    /**
     * 获取文件夹最新更新时间
     * @param dir 文件夹路径
     * @return
     */
    public static Date getDirModifyTime(String dir) {
        if (StringUtils.isBlank(dir)) {
            return null;
        }
        File file = new File(dir);
        long lastModified = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                lastModified = f.lastModified() > lastModified ? f.lastModified() : lastModified;
            }
        } else {
            return null;
        }
        return new Date(lastModified);
    }
}
