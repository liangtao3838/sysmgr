package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.TradeDetailAnalyseDao;
import com.sys.mgr.model.TradeDetailAnalyse;
import com.sys.mgr.service.TraceDetailTaskWeekService;
import com.sys.mgr.service.TradeDetailService;
import com.sys.mgr.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 21:01 2018/4/27
 */
@Service(value = "traceDetailTaskWeekService")
public class TraceDetailTaskWeekServiceImpl implements TraceDetailTaskWeekService {

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    @Autowired
    TradeDetailAnalyseDao tradeDetailAnalyseDao;


    @Override
    public void excute() {
        Date date = new Date();
        String week = DateUtil.getDateWeek(DateUtil.addDay(date,-1));
        String startDate = threadLocal.get().format(DateUtil.addDay(date,-7));
        String endDate = threadLocal.get().format(DateUtil.addDay(date,-1));
        List<TradeDetailAnalyse> analyseList = tradeDetailAnalyseDao.getWeekTradeDetailAnalyse(startDate,endDate);
        if(!CollectionUtils.isEmpty(analyseList)){
            for(TradeDetailAnalyse v:analyseList){
                v.setWeek(week);
            }
            tradeDetailAnalyseDao.insertAnalyseList(analyseList);
        }
    }
}
