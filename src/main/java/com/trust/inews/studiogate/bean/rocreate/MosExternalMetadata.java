package com.trust.inews.studiogate.bean.rocreate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * roCreate命令<mos>.<roCreate>.<mosExternalMetadata>标签实体类
 */
@XmlRootElement(name = "MosExternalMetadata")
public class MosExternalMetadata {
    private MosPayload mosPayload;

    public MosExternalMetadata() {
    }

    public MosPayload getMosPayload() {
        return mosPayload;
    }

    @XmlElement
    public void setMosPayload(MosPayload mosPayload) {
        this.mosPayload = mosPayload;
    }
}