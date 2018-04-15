package com.sys.mgr.controller;

import com.sys.mgr.model.NodeInfoVo;
import com.sys.mgr.service.SysServiceGuiService;
import com.sys.mgr.utils.CommonUtil;
import com.sys.mgr.utils.DateUtil;
import com.sys.mgr.utils.DocumentUtil;
import com.sys.mgr.utils.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by liangtao on 2018/3/25.
 *
 */
@RequestMapping("/sysservicegui")
@Controller
public class SysServiceGuiController {

    private final static Logger log = LoggerFactory.getLogger(SysServiceGuiController.class);

    @Autowired
    SysServiceGuiService sysServiceGuiService;

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("sysservicegui");
    }

    @RequestMapping(value = "/getsysname",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getSysName(
            @RequestParam(value="nodecode") String nodecode
    ){
        long tid = System.nanoTime();
        try {
            List<NodeInfoVo> sysname = sysServiceGuiService.getServiceName(nodecode);
            Map<String,List<NodeInfoVo>> map = CommonUtil.dataServiceConvert(sysname);
            String xml = DocumentUtil.getXMl(map);
            return xml;
        }catch (Exception e){
            log.error("tid:{} 获取系统调用关系系统名称异常",tid,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping(value = "/getsyscount",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String  getsyscount(@RequestParam(value="nodecode") String nodecode,
                               @RequestParam(value = "monitortime") String monitortime){
        long tid = System.nanoTime();
        String startTime = null;
        String endTime = DateUtil.getStringDate(new Date());;
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
            List<NodeInfoVo> sysname = sysServiceGuiService.getServiceName(nodecode);
            List<String> count = new ArrayList<String>();
            if(!CollectionUtils.isEmpty(sysname)){
                for(NodeInfoVo str:sysname){
                    Integer succNum = sysServiceGuiService.getSuccCount(str.getNextRouteNode(),startTime,endTime);
                    Integer failNum = sysServiceGuiService.getFailCount(str.getNextRouteNode(),startTime,endTime);
                    count.add(str.getNextRouteNode()+","+succNum+","+failNum);
                }
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("count",count);
            return new JsonResponse(resultMap).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取服务调用关系系统名称异常",tid,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }
}
