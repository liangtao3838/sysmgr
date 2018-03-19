package com.sys.mgr.model;

import java.io.Serializable;

/**
 * Created by liangtao on 2018/3/18.
 */
public class SysService implements Serializable {
    private long id;
    private String serviceName;
    private String nodeName;
    private String nodeCode;
    private String serviceAddr;
    private String methodName;
    private String protocolType;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getServiceAddr() {
        return serviceAddr;
    }

    public void setServiceAddr(String serviceAddr) {
        this.serviceAddr = serviceAddr;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
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
        return "SysService{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", nodeCode='" + nodeCode + '\'' +
                ", serviceAddr='" + serviceAddr + '\'' +
                ", methodName='" + methodName + '\'' +
                ", protocolType='" + protocolType + '\'' +
                ", yn=" + yn +
                ", createTime='" + createTime + '\'' +
                ", createPin='" + createPin + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updatePin='" + updatePin + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}

