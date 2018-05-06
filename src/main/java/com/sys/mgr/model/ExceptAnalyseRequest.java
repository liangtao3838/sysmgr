package com.sys.mgr.model;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 10:54 2018/5/6
 */
public class ExceptAnalyseRequest {

    private String startDate;
    private String endDate;
    private Integer startRow;
    private Integer pageSize;

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
        return "ExceptAnalyseRequest{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startRow=" + startRow +
                ", pageSize=" + pageSize +
                '}';
    }
}
