package com.sys.mgr.service;

import com.sys.mgr.model.TradeDetail;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
public interface TradeDetailService {

    List<TradeDetail> getList(long tid,Integer offset, Integer rows);

    Long getCount(long tid);

    String getXMl(long id,String type);
}
