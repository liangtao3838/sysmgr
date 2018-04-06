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
        Map<String,List<NodeInfoVo>> map = new HashMap<String, List<NodeInfoVo>>();
        for(NodeInfoVo nodeInfo : list){
            if(map.containsKey(nodeInfo.getNextRouteNode())){
                List<NodeInfoVo> nodeInfos = map.get(nodeInfo.getNextRouteNode());
                boolean flag = false;
                for(NodeInfoVo nodeInfo1 : nodeInfos){
                    if(nodeInfo1.getNowRouteNode().equals(nodeInfo.getNextRouteNode()) && nodeInfo1.getNextRouteNode().equals(nodeInfo.getNowRouteNode())){
                        flag = true;
                        continue;
                    }
                }
                if(flag){
                    continue;
                }
                if(map.containsKey(nodeInfo.getNowRouteNode())){
                    map.get(nodeInfo.getNowRouteNode()).add(nodeInfo);
                }else{
                    List<NodeInfoVo> nodeInfos1 = new ArrayList<NodeInfoVo>();
                    nodeInfos1.add(nodeInfo);
                    map.put(nodeInfo.getNowRouteNode(),list);
                }
            }else {

            }

            if(map.containsKey(nodeInfo.getNowRouteNode())){
            }
        }
        return list;
    }

    @Override
    public List<String> getSysNameXXX() {
        return sysCallRelaGuiDao.getSysNameXXX();
    }

    @Override
    public Integer getSysSuccCount(String sysname) {
        return sysCallRelaGuiDao.getSysSuccCount(sysname);
    }

    @Override
    public Integer getSysFailCount(String sysname) {
        return sysCallRelaGuiDao.getSysFailCount(sysname);
    }
}
