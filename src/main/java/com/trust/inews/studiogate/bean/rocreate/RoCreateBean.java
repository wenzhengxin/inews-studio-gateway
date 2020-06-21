package com.trust.inews.studiogate.bean.rocreate;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * MOS roCreate命令消息实体类
 */
@XmlRootElement(name = "mos")
@XmlType(propOrder = {"roCreate"})
public class RoCreateBean {
    private RoCreate roCreate;

    public RoCreateBean() {
        super();
    }


    public RoCreate getRoCreate() {
        return roCreate;
    }

    @XmlElement
    public void setRoCreate(RoCreate roCreate) {
        this.roCreate = roCreate;
    }


}
