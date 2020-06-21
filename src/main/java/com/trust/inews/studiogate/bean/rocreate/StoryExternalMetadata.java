package com.trust.inews.studiogate.bean.rocreate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * roCreate命令<mos>.<roCreate>.<story>.<mosExternalMetadata>标签实体类
 */
@XmlRootElement(name = "mosExternalMetadata")
public class StoryExternalMetadata {
    private String mosScope;
    private StoryMosPayload mosPayload;

    public StoryExternalMetadata() {
    }

    public String getMosScope() {
        return mosScope;
    }

    @XmlElement
    public void setMosScope(String mosScope) {
        this.mosScope = mosScope;
    }

    public StoryMosPayload getMosPayload() {
        return mosPayload;
    }

    @XmlElement
    public void setMosPayload(StoryMosPayload mosPayload) {
        this.mosPayload = mosPayload;
    }
}