package com.sys.mgr.utils;

import com.sys.mgr.model.NodeInfo;
import com.sys.mgr.model.NodeInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by liangtao on 2018/3/19.
 */
public class CommonUtil {

    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    private static byte[] b = new byte[10000];

    static{
        for(int i = 0;i<b.length ;i++){
            b[i] = (byte)(i%2 == 0?(-1)*new Random().nextInt(1280):new Random().nextInt(1280));
        }
    }

    public static List<Long> splitId(long  tid, String ids){
        try{
            List<Long> result = new ArrayList<Long>();
            String[] list = ids.split(",");
            for(int i=0; i < list.length; i++){
                result.add(Long.parseLong(list[i]));
            }
            return result;
        } catch (Exception e){
            log.error("tid:{},解析id集合错误",tid);
            return null;
        }
    }

    public static Map<String,List<NodeInfoVo>> dataServiceConvert(List<NodeInfoVo> list){
        if(org.springframework.util.CollectionUtils.isEmpty(list)){
            return null;
        }
        Map<String,List<NodeInfoVo>> map = new HashMap<String, List<NodeInfoVo>>();
        List<NodeInfoVo> serviceNodeInfoVo1 = new ArrayList<NodeInfoVo>();
        map.put(list.get(0).getNowRouteNode(),list);
        for (NodeInfoVo vo:list){
            map.put(vo.getNextRouteNode(),null);
        }
        return map;
    }

    public static Map<String,List<NodeInfoVo>> dataConvert( List<NodeInfoVo> list){

        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        Map<String,List<NodeInfoVo>> map = new HashMap<String, List<NodeInfoVo>>();
        for(NodeInfoVo nodeInfo : list){
            if(map.containsKey(nodeInfo.getNextRouteNode())){
                List<NodeInfoVo> nodeInfos = map.get(nodeInfo.getNextRouteNode());
                boolean flag = false;
                for(NodeInfoVo nodeInfo1 : nodeInfos){
                    if(nodeInfo1.getNextRouteNode().equals(nodeInfo.getNowRouteNode())){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    continue;
                }
                getMap(map,nodeInfo,list);
            }else {
                getMap(map,nodeInfo,list);
            }
        }
        return map;
    }

    public static void getMap(Map<String,List<NodeInfoVo>> map, NodeInfoVo nodeInfo,List<NodeInfoVo> list){
        if(map.containsKey(nodeInfo.getNowRouteNode())){
            map.get(nodeInfo.getNowRouteNode()).add(nodeInfo);
        }else{
            List<NodeInfoVo> nodeInfos1 = new ArrayList<NodeInfoVo>();
            nodeInfos1.add(nodeInfo);
            map.put(nodeInfo.getNowRouteNode(),list);
        }
    }


    public static String getRandomValue(){
       return b[new Random().nextInt(1000)]+"";
    }



}
