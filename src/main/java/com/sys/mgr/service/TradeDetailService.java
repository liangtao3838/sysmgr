package com.sys.mgr.service;

import com.sys.mgr.model.TradeDetail;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
public interface TradeDetailService {

    List<TradeDetail> getList(long tid,String syscallname,String qqxt,String zt,String startDate,String endDate,Integer offset, Integer rows);

    Long getCount(long tid,String syscallname,String qqxt,String zt,String startDate,String endDate);

    String getXMl(long id,String type);
}
