package com.sys.mgr.service;

import com.sys.mgr.model.NodeInfoVo;

import java.util.List;

/**
 * Created by liangtao on 2018/3/20.
 */
public interface SysCallRelaGuiService {

    List<NodeInfoVo> getSysName();

    Integer getSysSuccCount(String sysname,String startTime,String endTime);

    Integer getSysFailCount(String sysname,String startTime,String endTime);
}
