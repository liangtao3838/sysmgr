package com.sys.mgr.model;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 14:52 2018/4/28
 */
public class TradeDetailAnalyse {
    private long id;
    private String nodeCode;
    private String serviceCode;
    private int dySuccNum;
    private int dyFailNum;
    private int bdSuccNum;
    private int bdFailNum;
    private String week;
    private String year;
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

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public int getDySuccNum() {
        return dySuccNum;
    }

    public void setDySuccNum(int dySuccNum) {
        this.dySuccNum = dySuccNum;
    }

    public int getDyFailNum() {
        return dyFailNum;
    }

    public void setDyFailNum(int dyFailNum) {
        this.dyFailNum = dyFailNum;
    }

    public int getBdSuccNum() {
        return bdSuccNum;
    }

    public void setBdSuccNum(int bdSuccNum) {
        this.bdSuccNum = bdSuccNum;
    }

    public int getBdFailNum() {
        return bdFailNum;
    }

    public void setBdFailNum(int bdFailNum) {
        this.bdFailNum = bdFailNum;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "TradeDetailAnalyse{" +
                "id=" + id +
                ", nodeCode='" + nodeCode + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", dySuccNum=" + dySuccNum +
                ", dyFailNum=" + dyFailNum +
                ", bdSuccNum=" + bdSuccNum +
                ", bdFailNum=" + bdFailNum +
                ", week='" + week + '\'' +
                ", year='" + year + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
