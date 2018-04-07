package com.sys.mgr.dao;

import com.sys.mgr.model.NodeInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
@Repository
public interface SysServiceGuiDao {

    List<NodeInfoVo> getServiceName(@Param("nodecode") String nodecode);

    Integer getSuccCount(@Param("serviceName") String serviceName,
                         @Param("startTime") String startTime,
                         @Param("endTime")String endTime);

    Integer getFailCount(@Param("serviceName") String serviceName,
                         @Param("startTime") String startTime,
                         @Param("endTime")String endTime);
}
