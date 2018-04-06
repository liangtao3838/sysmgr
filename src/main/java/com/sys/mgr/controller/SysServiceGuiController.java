package com.sys.mgr.controller;

import com.sys.mgr.model.NodeInfoVo;
import com.sys.mgr.service.SysServiceGuiService;
import com.sys.mgr.utils.CommonUtil;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/getsysname")
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
            return new JsonResponse("error").toJSON();
        }
    }

    @RequestMapping("/getsyscount")
    @ResponseBody
    public String  getsyscount(@RequestParam(value="nodecode") String nodecode){
        long tid = System.nanoTime();
        try {
            List<NodeInfoVo> sysname = sysServiceGuiService.getServiceName(nodecode);
            List<String> count = new ArrayList<String>();
            if(!CollectionUtils.isEmpty(sysname)){
                for(NodeInfoVo str:sysname){
                    Integer succNum = sysServiceGuiService.getSuccCount(str.getNextRouteNode());
                    Integer failNum = sysServiceGuiService.getFailCount(str.getNextRouteNode());
                    count.add(str.getNextRouteNode()+","+succNum+","+failNum);
                }
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("count",count);
            return new JsonResponse(resultMap).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取服务调用关系系统名称异常",tid,e);
            return new JsonResponse("error").toJSON();
        }
    }
}
