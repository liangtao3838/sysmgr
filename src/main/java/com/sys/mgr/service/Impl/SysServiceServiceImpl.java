package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.SysServiceDao;
import com.sys.mgr.model.SysService;
import com.sys.mgr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liangtao on 2018/3/18.
 */
@Service
public class SysServiceServiceImpl implements SysServiceService {

    @Autowired
    SysServiceDao sysServiceDao;

    @Override
    public List<SysService> getList(long tid,Integer offset,Integer rows) {
        return sysServiceDao.getList(offset,rows);
    }

    @Override
    public long getCount(long tid) {
        return sysServiceDao.getCount();
    }

    @Override
    public boolean add(SysService info) {
        return sysServiceDao.add(info)>0;
    }

    @Override
    public boolean update(SysService info) {
        return sysServiceDao.update(info)>0;
    }

    @Override
    public SysService query(long tid, long id) {
        return sysServiceDao.query(id);
    }

    @Override
    public boolean delete(List<Long> ids,String updatePin) {
        return sysServiceDao.delete(ids,updatePin)>0;
    }
}
