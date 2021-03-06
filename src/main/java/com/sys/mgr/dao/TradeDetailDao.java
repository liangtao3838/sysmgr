package com.sys.mgr.dao;

import com.sys.mgr.model.ExceptAnalyseRequest;
import com.sys.mgr.model.ExceptRequest;
import com.sys.mgr.model.TradeDetail;
import com.sys.mgr.model.TradeDetailAnalyse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/25.
 */
@Repository
public interface TradeDetailDao {

    List<TradeDetail> getList(
            @Param("syscallname") String syscallname,
            @Param("qqxt") String qqxt,
            @Param("zt") String zt,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long getCount(
            @Param("syscallname") String syscallname,
            @Param("qqxt") String qqxt,
            @Param("zt") String zt,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    Map<String,String> getXMl(@Param("id") Long id, @Param("type") String type);

    List<TradeDetail> getExport(@Param("request") ExceptRequest request);

    List<TradeDetailAnalyse> getExportWeek(@Param("request") ExceptAnalyseRequest request);

    List<TradeDetailAnalyse> getExportMonth(@Param("request") ExceptAnalyseRequest request);


}
