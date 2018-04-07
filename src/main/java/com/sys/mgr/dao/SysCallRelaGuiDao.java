package com.sys.mgr.dao;

import com.sys.mgr.model.NodeInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtao on 2018/3/20.
 */
@Repository
public interface SysCallRelaGuiDao {

    List<NodeInfoVo> getSysName();

    Integer getSysSuccCount(@Param("sysname") String sysname,
                            @Param("startTime") String startTime,
                            @Param("endTime") String endTime);

    Integer getSysFailCount(@Param("sysname") String sysname,
                            @Param("startTime") String startTime,
                            @Param("endTime") String endTime);
}
