package com.sys.mgr.dao;

import com.sys.mgr.model.NodeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by liangtao on 2018/3/14.
 */
@Repository
public interface NodeInfoDao {

    List<NodeInfo> getList(@Param("offset") Integer offset,@Param("rows") Integer rows);

    long  getCount();

    int add(NodeInfo nodeInfo);

    int delete(@Param("ids") List<Long> ids, @Param("pin") String pin);

    NodeInfo query(long id);

    int update(NodeInfo nodeInfo);
}
