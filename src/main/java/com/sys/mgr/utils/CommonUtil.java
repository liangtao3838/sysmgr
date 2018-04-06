package com.sys.mgr.utils;

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

    private static final byte[] b = new byte[1000];

    {
        for(int i = 0;i<b.length ;i++){
            b[i] = (byte)(i%2 == 0?(-1)*new Random().nextInt(128):new Random().nextInt(128));
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

    public static Map<String,List<NodeInfoVo>> dataConvert( List<NodeInfoVo> list){

        if(CollectionUtils.isNotEmpty(list)){
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
                if(map.containsKey(nodeInfo.getNowRouteNode())){
                    map.get(nodeInfo.getNowRouteNode()).add(nodeInfo);
                }else{
                    List<NodeInfoVo> nodeInfos1 = new ArrayList<NodeInfoVo>();
                    nodeInfos1.add(nodeInfo);
                    map.put(nodeInfo.getNowRouteNode(),list);
                }
            }else {
                if(map.containsKey(nodeInfo.getNowRouteNode())){
                    map.get(nodeInfo.getNowRouteNode()).add(nodeInfo);
                }else{
                    List<NodeInfoVo> nodeInfos1 = new ArrayList<NodeInfoVo>();
                    nodeInfos1.add(nodeInfo);
                    map.put(nodeInfo.getNowRouteNode(),list);
                }
            }
        }
        return map;
    }

    public static String getRandomValue(){
       return b[new Random().nextInt(1000)]+"";
    }



}
