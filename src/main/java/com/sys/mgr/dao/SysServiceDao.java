package com.sys.mgr.dao;

import com.sys.mgr.model.SysCallRela;
import com.sys.mgr.model.SysService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangtao on 2018/3/18.
 */
@Repository
public interface SysServiceDao {

    List<SysService> getList(@Param("offset") Integer offset,@Param("rows") Integer rows);

    long getCount();

    int add(SysService info);

    int delete(@Param("ids") List<Long> ids, @Param("pin") String pin);

    int update(SysService info);

    SysService query(long id);

}
