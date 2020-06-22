package com.trust.inews.studiogate.listener;

import com.trust.inews.studiogate.bean.rocreate.*;
import com.trust.inews.studiogate.config.Config;
import com.trust.inews.studiogate.config.Constant;
import com.trust.inews.studiogate.httpclient.HttpClient;
import com.trust.inews.studiogate.session.NativeCache;
import com.trust.inews.studiogate.util.DateUtil;
import com.trust.inews.studiogate.util.FileUtils;
import com.trust.inews.studiogate.util.NewsUtils;
import com.trust.inews.studiogate.util.XmlUtil;
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
        if (Constant.SLAVE.equals(Config.serverMode) && NativeCache.get(Constant.MASTER_STATUS).toString().equals(Constant.MASTER_ON)){
            return;
        }
        logger.info("[新建]:" + file.getAbsolutePath());
        File parentFile = file.getParentFile();
        if (parentFile.isDirectory()) {
            RoCreateBean roCreateBean = getRoCreateBean(parentFile);
            HttpClient.sendRoCreate(XmlUtil.beanToXml(roCreateBean));
        }
    }

    /**
     * 文件修改执行
     */
    public void onFileChange(File file) {
        if (Constant.SLAVE.equals(Config.serverMode) && NativeCache.get(Constant.MASTER_STATUS).toString().equals(Constant.MASTER_ON)){
            return;
        }
        logger.info("[修改]:" + file.getAbsolutePath());
        long start = System.currentTimeMillis();
        File parentFile = file.getParentFile();
        if (parentFile.isDirectory()) {
            RoCreateBean roCreateBean = getRoCreateBean(parentFile);
            HttpClient.sendRoCreate(XmlUtil.beanToXml(roCreateBean));
        }
        long end = System.currentTimeMillis();
        logger.info(Long.toString((start-end)));
    }

    /**
     * 文件删除执行
     */
    public void onFileDelete(File file) {
        if (Constant.SLAVE.equals(Config.serverMode) && NativeCache.get(Constant.MASTER_STATUS).toString().equals(Constant.MASTER_ON)){
            return;
        }
        logger.info("[删除]:" + file.getAbsolutePath());
        File parentFile = file.getParentFile();
        if (parentFile.isDirectory()) {
            RoCreateBean roCreateBean = getRoCreateBean(parentFile);
            HttpClient.sendRoCreate(XmlUtil.beanToXml(roCreateBean));
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
     * 获取bean
     *
     * @param parentFile
     * @return
     */
    private RoCreateBean getRoCreateBean(File parentFile) {

        RoCreateBean roCreateBean = new RoCreateBean();
        RoCreate roCreate = new RoCreate();
        MosPayload mosPayload = new MosPayload();
        MosExternalMetadata mosExternalMetadata = new MosExternalMetadata();
        String roSlug = parentFile.getName();//串联单标题，此处也作串联单id
        String lanmu = "";//栏目
        Integer duration = 0;
        String modifyTime = DateUtil.dateToString(FileUtils.getDirModifyTime(parentFile.getAbsolutePath()), DateUtil.T_EN_SECONDS_FORMAT_PATTERN);//串联单最新修改时间
        List<Story> stories = new ArrayList<>();
        File[] files = parentFile.listFiles();
        for (File f : files) {
            try {
                InputStreamReader fr = new InputStreamReader(new FileInputStream(f));
                String news = IOUtils.toString(fr);
                String pageNumber = NewsUtils.getPageNumber(news);//文稿再串联单中的序号
                String author = StringUtils.isBlank(NewsUtils.getWriter(news)) ? "" : NewsUtils.getWriter(news).replaceAll(" ", ",");//记者 格式：张三,李四
                String writer = StringUtils.isBlank(author) ? "" : author.split(",")[0];//撰稿人 author取第一个
                String kouboqian = NewsUtils.getKouboqian(news);//导语/口播前
                String zhengwen = NewsUtils.getZhengwen(news);//正文
                String koubohou = NewsUtils.getKoubohou(news);//编后/口播后
                String totalTime = NewsUtils.getTotalTime(news);//文稿时长
                duration = duration + (StringUtils.isBlank(totalTime) ? 0 : Integer.parseInt(totalTime));
                String createDate = NewsUtils.getCreateDate(news);//创建时间
                String tongxunyuan = NewsUtils.getTongxunyuan(news);//通讯员
                String title = NewsUtils.getTitle(news);//标题
                String storyid = NewsUtils.getStoryid(news);//文稿id
                lanmu = NewsUtils.getLanmu(news);//栏目
                String videoid = NewsUtils.getVideoid(news);//视频id
                Story story = new Story();
                story.setStoryID(storyid);
                story.setStoryNum(pageNumber);
                story.setStorySlug(title);

                Item item = new Item();
                item.setItemID(videoid);
                List<Item> items = new ArrayList<>();
                items.add(item);
                story.setItem(items);
                StoryExternalMetadata storyExternalMetadata = new StoryExternalMetadata();
                StoryMosPayload storyMosPayload = new StoryMosPayload();
                storyMosPayload.setAfterword(koubohou);
                storyMosPayload.setDocTail(zhengwen);
                storyMosPayload.setGuideTail(kouboqian);
                storyMosPayload.setAuthor(author);
                storyMosPayload.setWriter(writer);
                storyExternalMetadata.setMosPayload(storyMosPayload);
                story.setMosExternalMetadata(storyExternalMetadata);
                stories.add(story);

            } catch (IOException e) {
                logger.error("串联单内容解析失败", e);
            }
        }
        mosPayload.setColumn(lanmu);
        mosExternalMetadata.setMosPayload(mosPayload);
        roCreate.setRoSlug(roSlug);
        roCreate.setRoID(roSlug);
        roCreate.setRoEdDur(DateUtil.secToTime(duration));
        roCreate.setModifyTime(modifyTime);
        roCreate.setMosExternalMetadata(mosExternalMetadata);
        roCreate.setStory(stories);
        roCreateBean.setRoCreate(roCreate);
        return roCreateBean;
    }
}