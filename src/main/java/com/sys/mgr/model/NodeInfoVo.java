package com.sys.mgr.model;

import java.io.Serializable;

/**
 * Created by liangtao on 2018/3/28.
 */
public class NodeInfoVo implements Serializable {

    private String nowRouteNode;
    private String nowRouteName;
    private String nextRouteNode;
    private String nextRouteName;
    private Integer yn;
    private String nowRouteStatus;
    private String nextRouteStatus;


    public String getNowRouteStatus() {
        return nowRouteStatus;
    }

    public void setNowRouteStatus(String nowRouteStatus) {
        this.nowRouteStatus = nowRouteStatus;
    }

    public String getNextRouteStatus() {
        return nextRouteStatus;
    }

    public void setNextRouteStatus(String nextRouteStatus) {
        this.nextRouteStatus = nextRouteStatus;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public String getNowRouteNode() {
        return nowRouteNode;
    }

    public void setNowRouteNode(String nowRouteNode) {
        this.nowRouteNode = nowRouteNode;
    }

    public String getNowRouteName() {
        return nowRouteName;
    }

    public void setNowRouteName(String nowRouteName) {
        this.nowRouteName = nowRouteName;
    }

    public String getNextRouteNode() {
        return nextRouteNode;
    }

    public void setNextRouteNode(String nextRouteNode) {
        this.nextRouteNode = nextRouteNode;
    }

    public String getNextRouteName() {
        return nextRouteName;
    }

    public void setNextRouteName(String nextRouteName) {
        this.nextRouteName = nextRouteName;
    }

    @Override
    public String toString() {
        return "NodeInfoVo{" +
                "nowRouteNode='" + nowRouteNode + '\'' +
                ", nowRouteName='" + nowRouteName + '\'' +
                ", nextRouteNode='" + nextRouteNode + '\'' +
                ", nextRouteName='" + nextRouteName + '\'' +
                ", yn=" + yn +
                ", nowRouteStatus='" + nowRouteStatus + '\'' +
                ", nextRouteStatus='" + nextRouteStatus + '\'' +
                '}';
    }
}
