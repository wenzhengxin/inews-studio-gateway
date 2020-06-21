package com.trust.inews.studiogate.protocol.heartbeat;

import com.trust.inews.studiogate.util.DateUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 命令<mos>.<heartbeat>标签实体类
 */
@XmlRootElement(name = "heartbeat")
public class Heartbeat {
    private String time;

    public Heartbeat() {
        this.time = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getTime() {
        return time;
    }

    @XmlElement(name = "time")
    public void setTime(String time) {
        this.time = time;
    }
}
