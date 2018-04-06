package com.sys.mgr.service;

import com.sys.mgr.model.NodeInfoVo;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
public interface SysServiceGuiService {

    List<NodeInfoVo> getServiceName(String nodecode);

    Integer getSuccCount(String serviceName);

    Integer getFailCount(String serviceName);
}
