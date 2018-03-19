package com.sys.mgr.dao;

import com.sys.mgr.model.NodeInfo;
import com.sys.mgr.model.SysCallRela;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtao on 2018/3/18.
 */
@Repository
public interface SysCallRelaDao {

    List<SysCallRela> getList(@Param("offset") Integer offset,@Param("rows") Integer rows);

    long getCount();

    int add(SysCallRela sysCallRela);

    int delete(@Param("ids") List<Long> ids, @Param("pin") String pin);

    int update(SysCallRela sysCallRela);

    SysCallRela query(long id);
}
