package com.trust.inews.studiogate.util;

import com.trust.inews.studiogate.bean.rocreate.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BeanUitls {

    /**
     * 获取bean
     *
     * @param parentFile
     * @return
     */
    public static RoCreateBean getRoCreateBean(File parentFile) throws IOException {

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
