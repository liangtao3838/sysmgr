package com.sys.mgr.model;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 10:33 2018/4/14
 */
public class ExceptRequest {
    private String jkmc;
    private String qqxt;
    private String zt;
    private String startDate;
    private String endDate;
    private Integer startRow;
    private Integer pageSize;

    public String getJkmc() {
        return jkmc;
    }

    public void setJkmc(String jkmc) {
        this.jkmc = jkmc;
    }

    public String getQqxt() {
        return qqxt;
    }

    public void setQqxt(String qqxt) {
        this.qqxt = qqxt;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "ExceptRequest{" +
                "jkmc='" + jkmc + '\'' +
                ", qqxt='" + qqxt + '\'' +
                ", zt='" + zt + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startRow=" + startRow +
                ", pageSize=" + pageSize +
                '}';
    }
}
