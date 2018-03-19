package com.sys.mgr.service;

import com.sys.mgr.model.SysCallRela;

import java.util.List;

/**
 * Created by liangtao on 2018/3/18.
 */
public interface SysCallRelaService {

    List<SysCallRela> getList(long tid,Integer offset,Integer rows);

    long getCount(long tid);

    boolean add(SysCallRela sysCallRela);

    boolean update(SysCallRela sysCallRela);

    boolean delete(List<Long> ids,String updatePin);

    SysCallRela query(long tid, long id);
}
