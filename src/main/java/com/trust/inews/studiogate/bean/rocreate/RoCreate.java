package com.trust.inews.studiogate.bean.rocreate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * mos roCreate命令<mos>.<roCreate>标签实体类
 */
@XmlRootElement(name = "roCreate")
public class RoCreate {
    private MosExternalMetadata mosExternalMetadata;
    private String roID;
    private String roSlug;
    private String roEdStart;
    private String roEdDur;
    private String modifyTime;
    private List<Story> story;

    public RoCreate() {
    }

    public MosExternalMetadata getMosExternalMetadata() {
        return mosExternalMetadata;
    }

    @XmlElement(name = "mosExternalMetadata")
    public void setMosExternalMetadata(MosExternalMetadata mosExternalMetadata) {
        this.mosExternalMetadata = mosExternalMetadata;
    }

    public String getRoID() {
        return roID;
    }

    @XmlElement
    public void setRoID(String orID) {
        this.roID = orID;
    }

    public String getRoSlug() {
        return roSlug;
    }

    @XmlElement
    public void setRoSlug(String roSlug) {
        this.roSlug = roSlug;
    }

    public String getRoEdStart() {
        return roEdStart;
    }

    @XmlElement
    public void setRoEdStart(String roEdStart) {
        this.roEdStart = roEdStart;
    }

    public String getRoEdDur() {
        return roEdDur;
    }

    @XmlElement
    public void setRoEdDur(String roEdDur) {
        this.roEdDur = roEdDur;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    @XmlElement
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<Story> getStory() {
        return story;
    }

    @XmlElement
    public void setStory(List<Story> story) {
        this.story = story;
    }
}
