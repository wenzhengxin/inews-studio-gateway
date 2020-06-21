package com.trust.inews.studiogate.protocol.heartbeat;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *MOS heartbeat命令消息实体类
 */
@XmlRootElement(name = "mos")
public class HeartbeatBean {
    private Heartbeat heartbeat;

    public HeartbeatBean() {
        super();
        this.heartbeat = new Heartbeat();
    }


    @XmlElement(name = "heartbeat")
    public Heartbeat getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Heartbeat heartbeat) {
        this.heartbeat = heartbeat;
    }
}
