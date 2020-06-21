package com.trust.inews.studiogate.bean.roelementaction;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * MOS roElementAction命令消息实体类
 */
@XmlRootElement(name = "mos")
public class RoElementActionBean {
    private RoElementAction roElementAction;


    public RoElementAction getRoElementAction() {
        return roElementAction;
    }

    @XmlElement(name = "roElementAction")
    public void setRoElementAction(RoElementAction roElementAction) {
        this.roElementAction = roElementAction;
    }
}
