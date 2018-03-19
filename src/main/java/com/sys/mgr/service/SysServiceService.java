package com.sys.mgr.service;

import com.sys.mgr.model.SysCallRela;
import com.sys.mgr.model.SysService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtao on 2018/3/18.
 */
@Repository
public interface SysServiceService {

    List<SysService> getList(long tid,Integer offset,Integer rows);

    long getCount(long tid);

    boolean add(SysService info);

    boolean update(SysService info);

    boolean delete(List<Long> ids,String updatePin);

    SysService query(long tid, long id);
}
