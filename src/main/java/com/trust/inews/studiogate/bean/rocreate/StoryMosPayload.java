package com.trust.inews.studiogate.bean.rocreate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * roCreate命令<mos>.<roCreate>.<story>.<mosExternalMetadata>.<mosPayload>
 * 标签实体类
 */
@XmlRootElement(name = "mosPayload")
public class StoryMosPayload {
    private String guideLength;
    private String guideTail; //导语（口播前）
    private String afterlength;
    private String afterword; //口播后，浙广目前没有提供内容
    private String docLength;
    private String docTail; //文稿正文，浙广目前没有提供内容
    private String author; //记者
    private String writer; //作者（撰稿）

    public String getGuideLength() {
        return guideLength;
    }

    @XmlElement(name = "GuideLength")
    public void setGuideLength(String guideLength) {
        this.guideLength = guideLength;
    }

    public String getGuideTail() {
        return guideTail;
    }

    @XmlElement(name = "GuideTail")
    public void setGuideTail(String guideTail) {
        this.guideTail = guideTail;
    }

    public String getAfterlength() {
        return afterlength;
    }

    @XmlElement(name = "Afterlength")
    public void setAfterlength(String afterlength) {
        this.afterlength = afterlength;
    }

    public String getAfterword() {
        return afterword;
    }

    @XmlElement(name = "Afterword")
    public void setAfterword(String afterword) {
        this.afterword = afterword;
    }

    public String getDocLength() {
        return docLength;
    }

    @XmlElement(name = "DocLength")
    public void setDocLength(String docLength) {
        this.docLength = docLength;
    }

    public String getDocTail() {
        return docTail;
    }

    @XmlElement(name = "DocTail")
    public void setDocTail(String docTail) {
        this.docTail = docTail;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement(name = "Author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWriter() {
        return writer;
    }

    @XmlElement(name = "Writer")
    public void setWriter(String writer) {
        this.writer = writer;
    }
}
