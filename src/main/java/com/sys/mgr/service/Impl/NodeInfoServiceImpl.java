package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.NodeInfoDao;
import com.sys.mgr.model.NodeInfo;
import com.sys.mgr.service.NodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liangtao on 2018/3/14.
 */
@Service
public class NodeInfoServiceImpl implements NodeInfoService{

    @Autowired
    NodeInfoDao nodeInfoDao;

    public List<NodeInfo> getList(long tid, Integer offset,Integer rows){
        return nodeInfoDao.getList(offset,rows);
    }

    public long getCount(){
        return nodeInfoDao.getCount();
    }


    public boolean add(NodeInfo nodeInfo){
        return nodeInfoDao.add(nodeInfo) > 0;
    }

    public boolean delete(List<Long> ids,String updatePin){
        return nodeInfoDao.delete(ids,updatePin)>0;
    }

    public boolean update(NodeInfo nodeInfo){
        return nodeInfoDao.update(nodeInfo)>0;
    }

    public NodeInfo query(long tid,long id){
        return nodeInfoDao.query(id);
    }
}
