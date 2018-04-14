package com.sys.mgr.model;

import java.util.List;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 10:41 2018/4/14
 */
public class ExportResponse {

    List<TradeDetail> list;

    public List<TradeDetail> getList() {
        return list;
    }

    public void setList(List<TradeDetail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ExportResponse{" +
                "list=" + list +
                '}';
    }
}
