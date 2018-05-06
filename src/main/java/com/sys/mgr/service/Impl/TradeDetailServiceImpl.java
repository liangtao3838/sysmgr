package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.TradeDetailDao;
import com.sys.mgr.model.*;
import com.sys.mgr.service.TradeDetailService;
import org.apache.commons.lang3.StringUtils;
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
        String xml="";
        if(tradeDetailDao.getXMl(id,type)==null){
            return xml;
        }
        if(type.equals("qqxml")){
            xml=tradeDetailDao.getXMl(id,type).get("qqxml");

        }else if(type.equals("fhxml")){
            xml=tradeDetailDao.getXMl(id,type).get("fhxml");
        }else if(type.equals("xyxml")){
            xml= tradeDetailDao.getXMl(id,type).get("xyxml");
        }else if(type.equals("ycxx")){
            xml= tradeDetailDao.getXMl(id,type).get("ycxx");
        }else {
            return "参数错误";
        }
        if(StringUtils.isBlank(xml)){
            return "";
        }else {
            return xml;
        }

    }

    @Override
    public ExportResponse getExport(ExceptRequest request) {
        List<TradeDetail> list = tradeDetailDao.getExport(request);
        ExportResponse response = new ExportResponse();
        response.setList(list);
        return response;
    }

    @Override
    public ExportAnalyseResponse getExportWeek(ExceptAnalyseRequest request) {
        List<TradeDetailAnalyse> list = tradeDetailDao.getExportWeek(request);
        ExportAnalyseResponse response = new ExportAnalyseResponse();
        response.setList(list);
        return response;
    }

    @Override
    public ExportAnalyseResponse getExportMonth(ExceptAnalyseRequest request) {
        List<TradeDetailAnalyse> list = tradeDetailDao.getExportMonth(request);
        ExportAnalyseResponse response = new ExportAnalyseResponse();
        response.setList(list);
        return response;
    }


}
