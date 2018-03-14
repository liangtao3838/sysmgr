package com.sys.mgr.model;

/**
 * Created by liangtao on 2018/3/14.
 */
public class NodeInfo {
    private long id;
    private String nodeCode;
    private String nodeName;
    private String ipAddr;
    private String callAddr;
    private int yn;
    private String createTime;
    private String createPin;
    private String updateTime;
    private String updatePin;
    private String ts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getCallAddr() {
        return callAddr;
    }

    public void setCallAddr(String callAddr) {
        this.callAddr = callAddr;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreatePin() {
        return createPin;
    }

    public void setCreatePin(String createPin) {
        this.createPin = createPin;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePin() {
        return updatePin;
    }

    public void setUpdatePin(String updatePin) {
        this.updatePin = updatePin;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "NodeInfo{" +
                "id=" + id +
                ", nodeCode='" + nodeCode + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", ipAddr='" + ipAddr + '\'' +
                ", callAddr='" + callAddr + '\'' +
                ", yn=" + yn +
                ", createTime='" + createTime + '\'' +
                ", createPin='" + createPin + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updatePin='" + updatePin + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
