package com.sys.mgr.service;

import java.util.List;

/**
 * Created by liangtao on 2018/3/20.
 */
public interface SysCallRelaGuiService {

    List<String> getSysName();

    Integer getSysSuccCount(String sysname);

    Integer getSysFailCount(String sysname);
}
