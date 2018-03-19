package com.sys.mgr.service;

import com.sys.mgr.model.NodeInfo;
import org.w3c.dom.Node;

import java.util.List;

/**
 * Created by liangtao on 2018/3/17.
 */
public interface NodeInfoService {

    List<NodeInfo> getList(long tid,Integer offset,Integer rows);

    long getCount();

    boolean add(NodeInfo nodeInfo);

    boolean delete(List<Long> ids,String updatePin);

    boolean update(NodeInfo nodeInfo);

    NodeInfo query(long tid,long id);
}
