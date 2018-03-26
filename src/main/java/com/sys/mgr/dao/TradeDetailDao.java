package com.sys.mgr.dao;

import com.sys.mgr.model.TradeDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
@Repository
public interface TradeDetailDao {

    List<TradeDetail> getList(@Param("offset") Integer offset,@Param("rows") Integer rows);

    Long getCount();

    String getXMl(@Param("id") Long id,@Param("type") String type);
}
