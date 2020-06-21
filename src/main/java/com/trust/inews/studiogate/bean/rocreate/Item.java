package com.trust.inews.studiogate.bean.rocreate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * roCreate命令<mos>.<roCreate>.<story>.<itme>标签实体类
 */
@XmlRootElement(name = "item")
public class Item {
    private String itemID;
    private String itemSlug;
    private String objID; //视频ID
    private String mosID;

    public Item() {

    }

    public String getItemID() {
        return itemID;
    }

    @XmlElement
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemSlug() {
        return itemSlug;
    }

    @XmlElement
    public void setItemSlug(String itemSlug) {
        this.itemSlug = itemSlug;
    }

    public String getObjID() {
        return objID;
    }

    @XmlElement
    public void setObjID(String objID) {
        this.objID = objID;
    }

    public String getMosID() {
        return mosID;
    }

    @XmlElement
    public void setMosID(String mosID) {
        this.mosID = mosID;
    }

}
