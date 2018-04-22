package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.SysCallRelaGuiDao;
import com.sys.mgr.model.NodeInfoVo;
import com.sys.mgr.service.SysCallRelaGuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/20.
 */
@Service
public class SysCallRelaGuiServiceImpl implements SysCallRelaGuiService{

    @Autowired
    SysCallRelaGuiDao sysCallRelaGuiDao;

    @Override
    public List<NodeInfoVo> getSysName() {
        List<NodeInfoVo> list =sysCallRelaGuiDao.getSysName();
        return list;
    }

    @Override
    public Integer getSysSuccCount(String sysname,String startTime,String endTime) {
        return sysCallRelaGuiDao.getSysSuccCount(sysname,startTime,endTime);
    }

    @Override
    public Integer getSysFailCount(String sysname,String startTime,String endTime) {
        return sysCallRelaGuiDao.getSysFailCount(sysname,startTime,endTime);
    }
}
