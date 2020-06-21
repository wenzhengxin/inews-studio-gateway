package com.trust.inews.studiogate.bean.roelementaction;



import com.trust.inews.studiogate.bean.rocreate.Story;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * mos命令<mos>.<roElementAction>.<element_source>实体类
 */
@XmlRootElement(name = "element_source")
public class ElementSource {
    private List<Story> story;
    private List<String> storyID;

    public List<Story> getStory() {
        return story;
    }

    @XmlElement(name = "story")
    public void setStory(List<Story> story) {
        this.story = story;
    }

    public List<String> getStoryID() {
        return storyID;
    }

    @XmlElement(name = "storyID")
    public void setStoryID(List<String> storyID) {
        this.storyID = storyID;
    }
}
