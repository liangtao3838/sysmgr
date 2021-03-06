package com.sys.mgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sys.mgr.model.NodeInfo;
import com.sys.mgr.service.Impl.NodeInfoServiceImpl;
import com.sys.mgr.utils.CommonUtil;
import com.sys.mgr.utils.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/14.
 */
@Controller
@RequestMapping("/nodeinfo")
public class NodeInfoController {

    private final static Logger log = LoggerFactory.getLogger(NodeInfoController.class);

    @Autowired
    NodeInfoServiceImpl nodeInfoServiceImpl;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("nodemgr");
    }

    @RequestMapping(value = "list",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getList(
            @RequestParam(value = "page",  defaultValue = "0") Integer offset,
            @RequestParam(value = "rows",  defaultValue = "10") Integer rows){
        long tid = System.nanoTime();
        try{
            List<NodeInfo> list =  nodeInfoServiceImpl.getList(tid,offset,rows);
            Map<String,Object> result = new HashMap<String,Object>();
            long count=nodeInfoServiceImpl.getCount();
            result.put("rows",list);
            result.put("total",count);
            return new JsonResponse(result).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取节点信息异常",tid,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping(value = "add",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String  add(NodeInfo info){
        long tid = System.nanoTime();
        try{
            boolean result = nodeInfoServiceImpl.add(info);
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{} ,nodeinof:{}添加节点信息异常",tid,info.toString(),e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping(value = "update",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String update(NodeInfo info){
        long tid = System.nanoTime();
        try{
            boolean result = nodeInfoServiceImpl.update(info);
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{} nodeInfo:{}修改节点信息异常",tid,info.toString(),e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping(value = "delete",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String del(
            @RequestParam(value = "idlist") String idlist
    ){
        if(StringUtils.isEmpty(idlist)){
            return JsonResponse.errorResponse(-1,"请选择一条记录").toJSON();
        }
        long tid = System.nanoTime();
        List<Long> ids= CommonUtil.splitId(tid,idlist);
        try{
            boolean result = nodeInfoServiceImpl.delete(ids,"test");
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{} id:{}删除节点信息异常",tid,idlist,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping(value = "query",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String query(String id){
        long tid = System.nanoTime();
        try{
            if(StringUtils.isEmpty(id)){
                return JsonResponse.errorResponse(-1,"请选择一条记录").toJSON();
            }
            NodeInfo nodeInfo = nodeInfoServiceImpl.query(tid,Long.parseLong(id));
            return new JsonResponse(nodeInfo).toJSON();
        }catch (Exception e){
            log.error("tid:{} 查询节点信息异常,id:{}",tid,id,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }
}
