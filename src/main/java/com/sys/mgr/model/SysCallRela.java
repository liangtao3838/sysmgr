package com.sys.mgr.model;

import java.io.Serializable;

/**
 * Created by liangtao on 2018/3/18.
 */
public class SysCallRela implements Serializable{
    private long id;
    private String routeUuid;
    private String nowRouteNode;
    private String nextRouteNode;
    private String protocolUuid;
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

    public String getRouteUuid() {
        return routeUuid;
    }

    public void setRouteUuid(String routeUuid) {
        this.routeUuid = routeUuid;
    }

    public String getNowRouteNode() {
        return nowRouteNode;
    }

    public void setNowRouteNode(String nowRouteNode) {
        this.nowRouteNode = nowRouteNode;
    }

    public String getNextRouteNode() {
        return nextRouteNode;
    }

    public void setNextRouteNode(String nextRouteNode) {
        this.nextRouteNode = nextRouteNode;
    }

    public String getProtocolUuid() {
        return protocolUuid;
    }

    public void setProtocolUuid(String protocolUuid) {
        this.protocolUuid = protocolUuid;
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
        return "SysCallRela{" +
                "id=" + id +
                ", routeUuid='" + routeUuid + '\'' +
                ", nowRouteNode='" + nowRouteNode + '\'' +
                ", nextRouteNode='" + nextRouteNode + '\'' +
                ", protocolUuid='" + protocolUuid + '\'' +
                ", yn=" + yn +
                ", createTime='" + createTime + '\'' +
                ", createPin='" + createPin + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updatePin='" + updatePin + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
