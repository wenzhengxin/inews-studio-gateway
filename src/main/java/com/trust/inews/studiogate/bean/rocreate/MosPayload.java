package com.trust.inews.studiogate.bean.rocreate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * roCreate命令<mos>.<roCreate>.<mosExternalMetadata>.<mosPayload>
 * 标签实体类
 */
@XmlRootElement(name = "mosPayload")
public class MosPayload {
    private String column;

    public MosPayload() {
    }

    public String getColumn() {
        return column;
    }

    @XmlElement(name = "Column")
    public void setColumn(String column) {
        this.column = column;
    }
}
