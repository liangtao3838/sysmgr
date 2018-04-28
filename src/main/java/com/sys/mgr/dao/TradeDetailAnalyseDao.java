package com.sys.mgr.dao;

import com.sys.mgr.model.TradeDetailAnalyse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liangtao15
 * @Description:
 * @Date: Created in 14:58 2018/4/28
 * 流水明细定时分析
 */
@Repository
public interface TradeDetailAnalyseDao {

    List<TradeDetailAnalyse> getWeekTradeDetailAnalyse(String startDate, String endDate);

    List<TradeDetailAnalyse> getMonthTradeDetailAnalyse(String startDate,String endDate);

    void insertAnalyseList(List<TradeDetailAnalyse> list);

}
