package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.SysServiceGuiDao;
import com.sys.mgr.model.NodeInfoVo;
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
    public List<NodeInfoVo> getServiceName(String nodecode) {
        return sysServiceGuiDao.getServiceName(nodecode);
    }

    public Integer getSuccCount(String serviceName,String startTime,String endTime){
        return sysServiceGuiDao.getSuccCount(serviceName,startTime,endTime);
    }

    public Integer getFailCount(String servieName,String startTime,String endTime){
        return sysServiceGuiDao.getFailCount(servieName,startTime,endTime);
    }
}
