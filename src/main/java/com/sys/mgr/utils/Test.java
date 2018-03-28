package com.sys.mgr.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/28.
 */
public class Test {

    public static void main(String[] args) {


        List<NodeInfo> nodeInfoList = null;
        Map<String,List<NodeInfo>> map = null;


        for(NodeInfo nodeInfo : nodeInfoList){

            if(map.containsKey(nodeInfo.nextNodeCode)){
                List<NodeInfo> nodeInfos = map.get(nodeInfo.nextNodeCode);
                boolean flag = false;
                for(NodeInfo nodeInfo1 : nodeInfos){
                    if(nodeInfo1.currentNodeCode.equals(nodeInfo.nextNodeCode) && nodeInfo1.nextNodeCode.equals(nodeInfo.currentNodeCode)){
                        flag = true;
                        continue;
                    }
                }
                if(flag){
                    continue;
                }

                if(map.containsKey(nodeInfo.currentNodeCode)){
                    map.get(nodeInfo.currentNodeCode).add(nodeInfo);
                }else{
                    List<NodeInfo> nodeInfos1 = null;
                    nodeInfos1.add(nodeInfo);
                    map.put(nodeInfo.currentNodeCode,nodeInfoList);
                }


            }

            if(map.containsKey(nodeInfo.currentNodeCode)){

            }


        }






    }


    class NodeInfo{

        private String currentNodeCode;

        private String nextNodeCode;

        public String getCurrentNodeCode() {
            return currentNodeCode;
        }

        public void setCurrentNodeCode(String currentNodeCode) {
            this.currentNodeCode = currentNodeCode;
        }

        public String getNextNodeCode() {
            return nextNodeCode;
        }

        public void setNextNodeCode(String nextNodeCode) {
            this.nextNodeCode = nextNodeCode;
        }
    }


}
