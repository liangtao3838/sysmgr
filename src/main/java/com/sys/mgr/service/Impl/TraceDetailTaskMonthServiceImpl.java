package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.TradeDetailAnalyseDao;
import com.sys.mgr.model.TradeDetailAnalyse;
import com.sys.mgr.service.TraceDetailTaskMonthService;
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
 * @Date: Created in 21:49 2018/4/27
 */
@Service(value = "traceDetailTaskMonthService")
public class TraceDetailTaskMonthServiceImpl implements TraceDetailTaskMonthService {


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
        Date date = DateUtil.addDay(new Date(),-1);
        String month = DateUtil.getMonth(date);
        String startDate = DateUtil.getMonthFirstDay(date);
        String endDate = DateUtil.getMonthLastDay(date);
        List<TradeDetailAnalyse> analyseList = tradeDetailAnalyseDao.getMonthTradeDetailAnalyse(startDate,endDate);
        if(!CollectionUtils.isEmpty(analyseList)){
            for(TradeDetailAnalyse v:analyseList){
                v.setWeek(month);
            }
            tradeDetailAnalyseDao.insertAnalyseList(analyseList);
        }
    }
}
