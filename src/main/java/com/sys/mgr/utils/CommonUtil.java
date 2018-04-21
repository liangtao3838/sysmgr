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
        Map<String,List<NodeInfoVo>> map = new LinkedHashMap<String, List<NodeInfoVo>>();
        map.put(list.get(0).getNowRouteNode(),list);
        for (NodeInfoVo vo:list){
            map.put(vo.getNextRouteNode(),new ArrayList<NodeInfoVo>());
        }
        return map;
    }

    public static Map<String,List<NodeInfoVo>> dataConvert( List<NodeInfoVo> list){

        /*if(CollectionUtils.isEmpty(list)){
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
        return map;*/

        Map<String,List<NodeInfoVo>> map = new LinkedHashMap<String, List<NodeInfoVo>>();
        for(NodeInfoVo t:list){
            List<NodeInfoVo> edge = new ArrayList<NodeInfoVo>();
            map.put(t.getNowRouteNode(),edge);
            map.put(t.getNextRouteNode(),edge);
            /*if(map.containsKey(t.getNowRouteNode())){
                List<NodeInfoT> edgs = map.get(t.getNowRouteNode());
                edgs.add(t);
                map.put(t.getNowRouteNode(),edgs);
            }
            if(map.containsKey(t.getNextRouteNode())){
                List<NodeInfoT> edgs = map.get(t.getNextRouteNode());
                NodeInfoT nodeInfoT = new NodeInfoT(t.getNextRouteNode(),t.getNowRouteNode());
                edgs.add(nodeInfoT);
                map.put(t.getNextRouteNode(),edgs);
            }*/

            /*List<NodeInfoT> edgeNow = new ArrayList<NodeInfoT>();
            edgeNow.add(t);
            map.put(t.getNowRouteNode(),edgeNow);
            List<NodeInfoT> edgeNext = new ArrayList<NodeInfoT>();
            edgeNext.add(new NodeInfoT(t.getNextRouteNode(),t.getNowRouteNode()));
            map.put(t.getNextRouteNode(),edgeNext);*/
        }
        for(NodeInfoVo t1:list){
            if(map.containsKey(t1.getNowRouteNode())){
                List<NodeInfoVo> now = map.get(t1.getNowRouteNode());
                now.add(t1);
                map.put(t1.getNowRouteNode(),now);
            }
            if(map.containsKey(t1.getNextRouteNode())){
                List<NodeInfoVo> edgs = map.get(t1.getNextRouteNode());
                NodeInfoVo nodeInfoVo = new NodeInfoVo();
                nodeInfoVo.setNowRouteNode(nodeInfoVo.getNextRouteNode());
                nodeInfoVo.setNextRouteNode(nodeInfoVo.getNowRouteNode());
                edgs.add(nodeInfoVo);
                map.put(t1.getNextRouteNode(),edgs);
            }
        }
        return map;
    }

    /*public static void main(String[] args){
        NodeInfoT info1= new NodeInfoT("ESB","ESC");
        NodeInfoT info2= new NodeInfoT("EKA","ESB");
        NodeInfoT info3= new NodeInfoT("ECA","EBC");
        NodeInfoT info4= new NodeInfoT("EKD","EBC");
        NodeInfoT info5= new NodeInfoT("ESB","EKC");
        List<NodeInfoT> list = new ArrayList<NodeInfoT>();
        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        list.add(info5);
        Map<String,List<NodeInfoT>> map = dataConvert(list);
        System.out.println(DocumentUtilTEST.getXMl(map));
    }*/

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

/*class NodeInfoT{
    private String nowRouteNode;
    private String nextRouteNode;

    public NodeInfoT() {
    }

    public NodeInfoT(String nowRouteNode, String nextRouteNode) {
        this.nowRouteNode = nowRouteNode;
        this.nextRouteNode = nextRouteNode;
    }

    public String getNowRouteNode() {
        return nowRouteNode;
    }

    public void setNowRouteNode(String nowRouteNode) {
        this.nowRouteNode = nowRouteNode;
    }

    public String getNextRouteNode() {
        return nextRouteNode;
    }

    public void setNextRouteNode(String nextRouteNode) {
        this.nextRouteNode = nextRouteNode;
    }
}*/
