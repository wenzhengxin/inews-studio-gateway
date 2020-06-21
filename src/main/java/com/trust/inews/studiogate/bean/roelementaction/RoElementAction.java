package com.trust.inews.studiogate.bean.roelementaction;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  mos 命令<mos>.<roElementAction>标签实体类
 */
@XmlRootElement(name = "roElementAction")
public class RoElementAction {
    private String operation;
    private String roID;
    private ElementTarget elementTarget;
    private ElementSource elementSource;

    public String getOperation() {
        return operation;
    }

    @XmlAttribute(name = "operation")
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRoID() {
        return roID;
    }

    @XmlElement(name = "roID")
    public void setRoID(String roID) {
        this.roID = roID;
    }

    public ElementTarget getElementTarget() {
        return elementTarget;
    }

    @XmlElement(name = "element_target")
    public void setElementTarget(ElementTarget elementTarget) {
        this.elementTarget = elementTarget;
    }

    public ElementSource getElementSource() {
        return elementSource;
    }

    @XmlElement(name = "element_source")
    public void setElementSource(ElementSource elementSource) {
        this.elementSource = elementSource;
    }
}
