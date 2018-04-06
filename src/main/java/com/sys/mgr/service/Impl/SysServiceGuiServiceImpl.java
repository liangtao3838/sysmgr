package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.SysServiceGuiDao;
import com.sys.mgr.service.SysCallRelaGuiService;
import com.sys.mgr.service.SysServiceGuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liangtao on 2018/3/25.
 */
@Service
public class SysServiceGuiServiceImpl implements SysServiceGuiService {


    @Autowired
    SysServiceGuiDao sysServiceGuiDao;

    @Override
    public List<String> getServiceName() {
        return sysServiceGuiDao.getServiceName();
    }

    public Integer getSuccCount(String serviceName){
        return sysServiceGuiDao.getSuccCount(serviceName);
    }

    public Integer getFailCount(String servieName){
        return sysServiceGuiDao.getFailCount(servieName);
    }
}
