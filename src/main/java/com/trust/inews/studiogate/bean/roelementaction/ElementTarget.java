package com.trust.inews.studiogate.bean.roelementaction;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * mos 命令<mos>.<roElementAction>.<element_target>标签实体类
 */
@XmlRootElement(name = "element_target")
public class ElementTarget {
    private String storyID;

    public String getStoryID() {
        return storyID;
    }

    @XmlElement(name = "storyID")
    public void setStoryID(String storyID) {
        this.storyID = storyID;
    }
}
