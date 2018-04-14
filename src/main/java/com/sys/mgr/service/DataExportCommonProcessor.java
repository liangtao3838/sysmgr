package com.sys.mgr.service;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 10:36 2018/4/14
 */
public interface DataExportCommonProcessor {

    /**
     * 获取导出数据
     * @return
     */
    Object[][] getExportData();


    /**
     * 获取导出数据
     * @param startRow
     * @param pageSize
     * @return
     */
    Object[][] getExportData(Integer startRow, Integer pageSize);

    /**
     * 重置位置
     */
    void resetPos();
}
