package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.SysCallRelaDao;
import com.sys.mgr.model.SysCallRela;
import com.sys.mgr.service.SysCallRelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liangtao on 2018/3/18.
 */
@Service
public class SysCallRelaServiceImpl implements SysCallRelaService {


    @Autowired
    SysCallRelaDao sysCallRelaDao;

    @Override
    public List<SysCallRela> getList(long tid,Integer offset,Integer rows) {
        return sysCallRelaDao.getList(offset,rows);
    }

    @Override
    public long getCount(long tid) {
        return sysCallRelaDao.getCount();
    }

    @Override
    public boolean add(SysCallRela sysCallRela) {
        return sysCallRelaDao.add(sysCallRela) > 0;
    }

    @Override
    public boolean update(SysCallRela sysCallRela) {
        return sysCallRelaDao.update(sysCallRela)>0;
    }

    @Override
    public boolean delete(List<Long> ids,String updatePin) {
        return sysCallRelaDao.delete(ids,updatePin)>0;
    }

    @Override
    public SysCallRela query(long tid, long id) {
        return sysCallRelaDao.query(id);
    }
}
