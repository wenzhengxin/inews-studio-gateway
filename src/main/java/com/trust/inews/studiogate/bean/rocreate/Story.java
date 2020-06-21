package com.trust.inews.studiogate.bean.rocreate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * roCreate命令<mos>.<roCreate>.<story>标签实体类
 */
@XmlRootElement(name = "story")
public class Story {
    private String storyID;
    private String storySlug;
    private String storyModifyTime;
    private String storyNum;
    private StoryExternalMetadata mosExternalMetadata;
    private List<Item> item;

    public Story() {}

    public String getStoryID() {
        return storyID;
    }

    @XmlElement
    public void setStoryID(String storyID) {
        this.storyID = storyID;
    }

    public String getStorySlug() {
        return storySlug;
    }

    @XmlElement
    public void setStorySlug(String storySlug) {
        this.storySlug = storySlug;
    }

    public String getStoryModifyTime() {
        return storyModifyTime;
    }

    @XmlElement
    public void setStoryModifyTime(String storyModifyTime) {
        this.storyModifyTime = storyModifyTime;
    }

    public String getStoryNum() {
        return storyNum;
    }

    @XmlElement
    public void setStoryNum(String storyNum) {
        this.storyNum = storyNum;
    }

    public StoryExternalMetadata getMosExternalMetadata() {
        return mosExternalMetadata;
    }

    @XmlElement
    public void setMosExternalMetadata(StoryExternalMetadata mosExternalMetadata) {
        this.mosExternalMetadata = mosExternalMetadata;
    }

    public List<Item> getItem() {
        return item;
    }

    @XmlElement
    public void setItem(List<Item> item) {
        this.item = item;
    }
}
