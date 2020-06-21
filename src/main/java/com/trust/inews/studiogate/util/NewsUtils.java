package com.trust.inews.studiogate.util;

import com.trust.inews.studiogate.config.Config;
import org.apache.commons.lang.StringUtils;

/**
 * 根据文稿xml获取各个字段信息的工具
 */
public class NewsUtils {

    /**
     * 标题
     *
     * @param news
     * @return
     */
    public static String getTitle(String news) {
        String split = Config.title_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.title_tag_end));
        return substring1;
    }

    /**
     * 视频id
     *
     * @param news
     * @return
     */
    public static String getVideoid(String news) {
        String split = Config.videoid_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.videoid_tag_end));
        return substring1;
    }

    public static String getPageNumber(String news) {
        String split = Config.pagenumber_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.pagenumber_tag_end));
        return substring1;
    }

    /**
     * 栏目
     *
     * @param news
     * @return
     */
    public static String getLanmu(String news) {
        String split = Config.lanmu_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.lanmu_tag_end));
        return substring1;
    }

    /**
     * 文稿id
     *
     * @param news
     * @return
     */
    public static String getStoryid(String news) {
        String split = Config.storyid_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.videoid_tag_end));
        return substring1;
    }

    /**
     * 通讯员
     *
     * @param news
     * @return
     */
    public static String getTongxunyuan(String news) {
        String split = Config.tongxunyuan_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.tongxunyuan_tag_end));
        return substring1;
    }

    /**
     * 撰稿/记者
     *
     * @param news
     * @return
     */
    public static String getWriter(String news) {
        String split = Config.writer_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.writer_tag_end));
        return substring1;
    }

    /**
     * 创建时间
     *
     * @param news
     * @return
     */
    public static String getCreateDate(String news) {
        String split = Config.createdate_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.createdate_tag_end));
        return substring1;
    }

    /**
     * 总时长
     *
     * @param news
     * @return
     */
    public static String getTotalTime(String news) {
        String split = Config.totaltime_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.totaltime_tag_end));
        return substring1;
    }

    /**
     * 更新时间
     *
     * @param news
     * @return
     */
    public static String getModifyDate(String news) {
        String split = Config.modifydate_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        String substring1 = substring.substring(0, substring.indexOf(Config.modifydate_tag_end));
        return substring1;
    }


    /**
     * 导语
     *
     * @param news
     * @return
     */
    public static String getKouboqian(String news) {
        String split = Config.kouboqian_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        if (StringUtils.isBlank(Config.koubohou_tag_end) || substring.indexOf(Config.kouboqian_tag_end) < 0) {
            return substring;
        }
        String substring1 = substring.substring(0, substring.indexOf(Config.kouboqian_tag_end));
        return substring1;
    }

    /**
     * 正文
     *
     * @param news
     * @return
     */
    public static String getZhengwen(String news) {
        String split = Config.zhengwen_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        if (StringUtils.isBlank(Config.koubohou_tag_end) || substring.indexOf(Config.zhengwen_tag_end) < 0) {
            return substring;
        }
        String substring1 = substring.substring(0, substring.indexOf(Config.zhengwen_tag_end));
        return substring1;
    }

    /**
     * 编后/口播后
     *
     * @param news
     * @return
     */
    public static String getKoubohou(String news) {
        String split = Config.koubohou_tag_begin;

        String substring = news.substring(news.lastIndexOf(split) + split.length());
        if (StringUtils.isBlank(Config.koubohou_tag_end) || substring.indexOf(Config.koubohou_tag_end) < 0) {
            return substring;
        }
        String substring1 = substring.substring(0, substring.indexOf(Config.koubohou_tag_end));
        return substring1;
    }


}
