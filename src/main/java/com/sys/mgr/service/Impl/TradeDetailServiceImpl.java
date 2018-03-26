package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.TradeDetailDao;
import com.sys.mgr.model.TradeDetail;
import com.sys.mgr.service.TradeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
@Service
public class TradeDetailServiceImpl implements TradeDetailService {

    @Autowired
    TradeDetailDao tradeDetailDao;

    @Override
    public List<TradeDetail> getList(long tid,Integer offset, Integer rows) {
        return tradeDetailDao.getList(offset,rows);
    }

    @Override
    public Long getCount(long tid) {
        return tradeDetailDao.getCount();
    }

    @Override
    public String getXMl(long id, String type) {
        return tradeDetailDao.getXMl(id,type);
    }
}
