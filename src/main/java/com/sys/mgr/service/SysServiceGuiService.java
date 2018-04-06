package com.sys.mgr.service;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
public interface SysServiceGuiService {

    List<String> getServiceName();

    Integer getSuccCount(String serviceName);

    Integer getFailCount(String serviceName);
}
