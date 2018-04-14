package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.TradeDetailDao;
import com.sys.mgr.model.ExceptRequest;
import com.sys.mgr.model.ExportResponse;
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
    public List<TradeDetail> getList(long tid,String syscallname,String qqxt,String zt,String startDate,String endDate,Integer offset, Integer rows) {
        return tradeDetailDao.getList(syscallname,qqxt,zt,startDate,endDate,offset,rows);
    }

    @Override
    public Long getCount(long tid,String syscallname,String qqxt,String zt,String startDate,String endDate) {
        return tradeDetailDao.getCount(syscallname,qqxt,zt,startDate,endDate);
    }

    @Override
    public String getXMl(long id, String type) {
        return tradeDetailDao.getXMl(id,type);
    }

    @Override
    public ExportResponse getExport(ExceptRequest request) {
        List<TradeDetail> list = tradeDetailDao.getExport(request);
        ExportResponse response = new ExportResponse();
        response.setList(list);
        return response;
    }
}
