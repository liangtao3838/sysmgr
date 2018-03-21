package com.sys.mgr.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtao on 2018/3/20.
 */
@Repository
public interface SysCallRelaGuiDao {

    List<String> getSysName();

    Integer getSysSuccCount(@Param("sysname") String sysname);

    Integer getSysFailCount(@Param("sysname") String sysname);
}
