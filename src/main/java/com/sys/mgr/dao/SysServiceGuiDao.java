package com.sys.mgr.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
@Repository
public interface SysServiceGuiDao {

    List<String> getServiceName();

    Integer getSuccCount(@Param("serviceName") String serviceName);

    Integer getFailCount(@Param("serviceName") String serviceName);
}
