package com.sys.mgr.controller;

import com.sys.mgr.service.SysCallRelaGuiService;
import com.sys.mgr.utils.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/20.
 */
@RequestMapping("/syscallrelagui")
@Controller
public class SysCallRelaGUIController {

    private final static Logger log = LoggerFactory.getLogger(SysCallRelaGUIController.class);

    @Autowired
    SysCallRelaGuiService sysCallRelaGuiService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("syscallrelagui");
    }


    @RequestMapping("/getsysname")
    @ResponseBody
    public JsonResponse getSysName(){
        long tid = System.nanoTime();
        try {
            List<String> sysname = sysCallRelaGuiService.getSysName();
            Map<String,String> map = new HashMap<String, String>();
            if(!CollectionUtils.isEmpty(sysname)){
                for(String str:sysname){
                    Integer succNum = sysCallRelaGuiService.getSysSuccCount(str);
                    Integer failNum = sysCallRelaGuiService.getSysFailCount(str);
                    map.put(str,succNum+","+failNum);

                }
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("sysname",sysname);
            resultMap.put("count",map);
            return new JsonResponse(resultMap);
        }catch (Exception e){
            log.error("tid:{} 获取系统调用关系系统名称异常",tid,e);
            return new JsonResponse("error");
        }
    }
}
