package com.sys.mgr.model;

import java.util.List;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 10:52 2018/5/6
 */
public class ExportAnalyseResponse {

    List<TradeDetailAnalyse> list;

    public List<TradeDetailAnalyse> getList() {
        return list;
    }

    public void setList(List<TradeDetailAnalyse> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ExportAnalyseResponse{" +
                "list=" + list +
                '}';
    }
}
