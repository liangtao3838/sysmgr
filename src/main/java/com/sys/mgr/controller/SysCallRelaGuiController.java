package com.sys.mgr.controller;

import com.sys.mgr.model.NodeInfoVo;
import com.sys.mgr.service.SysCallRelaGuiService;
import com.sys.mgr.utils.CommonUtil;
import com.sys.mgr.utils.DateUtil;
import com.sys.mgr.utils.DocumentUtil;
import com.sys.mgr.utils.JsonResponse;
import org.apache.tools.ant.taskdefs.optional.ejb.JonasDeploymentTool;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liangtao on 2018/3/20.
 */
@RequestMapping("/syscallrelagui")
@Controller
public class SysCallRelaGuiController {

    private final static Logger log = LoggerFactory.getLogger(SysCallRelaGuiController.class);

    @Autowired
    SysCallRelaGuiService sysCallRelaGuiService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("syscallrelagui");
    }


    @RequestMapping(value = "/getsysname",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getSysName(){
        long tid = System.nanoTime();
        try {
            List<NodeInfoVo> nodeInfoVos = sysCallRelaGuiService.getSysName();
            Map<String,List<NodeInfoVo>> map = CommonUtil.dataConvert(nodeInfoVos);
            //String result = DocumentUtil.getXMl(map);
            return new JsonResponse(map).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取系统调用关系系统名称异常",tid,e);
            return JsonResponse.errorResponse(-1,"查询异常").toJSON();
        }
    }

    @RequestMapping(value = "/getNodeInfo",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getNodeInfo(){
        long tid = System.nanoTime();
        try {
            List<NodeInfoVo> nodeInfoVos = sysCallRelaGuiService.getSysName();
            Map<String,List<NodeInfoVo>> map = CommonUtil.dataConvert(nodeInfoVos);
            return new JsonResponse(map.keySet()).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取系统调用关系系统名称异常",tid,e);
            return JsonResponse.errorResponse(-1,"查询异常").toJSON();
        }
    }

    @RequestMapping(value = "/getsyscount",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getsyscount(
            @RequestParam(value = "monitortime") String monitortime
    ){
        long tid = System.nanoTime();
        String startTime = null;
        String endTime = DateUtil.getStringDate(new Date());
        if(monitortime.equals("hour")){
            startTime = DateUtil.getStringDate(DateUtil.addHour(new Date(),-1));
        }else if(monitortime.equals("minutes")){
            startTime = DateUtil.getTimeByMinute(-5);
        }else if(monitortime.equals("day")){
            startTime = DateUtil.getStrDataDay(new Date(),-1);
        }else {
            return JsonResponse.errorResponse(-1,"监控周期参数异常").toJSON();
        }
        try {
            List<NodeInfoVo> nodeInfoVos = sysCallRelaGuiService.getSysName();
            Map<String,List<NodeInfoVo>> map = CommonUtil.dataConvert(nodeInfoVos);
            List<String> count = new ArrayList<String>();
            if(!CollectionUtils.isEmpty(nodeInfoVos)){
                for(String str:map.keySet()){
                    String strkey = str.split("-")[0];
                    Integer succNum = sysCallRelaGuiService.getSysSuccCount(str.split("-")[0],startTime,endTime);
                    Integer failNum = sysCallRelaGuiService.getSysFailCount(str.split("-")[0],startTime,endTime);
                    count.add(strkey+","+succNum+","+failNum);
               }
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("count",count);
            return new JsonResponse(resultMap).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取系统调用关系系统名称异常",tid,e);
            return JsonResponse.errorResponse(-1,"查询异常").toJSON();
        }
    }



}
