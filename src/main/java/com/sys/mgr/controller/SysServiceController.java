package com.sys.mgr.controller;

import com.sys.mgr.model.SysCallRela;
import com.sys.mgr.model.SysService;
import com.sys.mgr.service.SysCallRelaService;
import com.sys.mgr.service.SysServiceService;
import com.sys.mgr.utils.CommonUtil;
import com.sys.mgr.utils.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/18.
 */
@RequestMapping("/sysservice")
@Controller
public class SysServiceController {

    private final static Logger log = LoggerFactory.getLogger(SysServiceController.class);

    @Autowired
    SysServiceService sysServiceService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("sysservice");
    }

    @RequestMapping("list")
    @ResponseBody
    public String getList(
            @RequestParam(value = "page",  defaultValue = "0") Integer offset,
            @RequestParam(value = "rows",  defaultValue = "10") Integer rows){
        long tid = System.nanoTime();
        try{
            List<SysService> list =  sysServiceService.getList(tid,offset,rows);
            Map<String,Object> result = new HashMap<String,Object>();
            long count=sysServiceService.getCount(tid);
            result.put("rows",list);
            result.put("total",count);
            return new JsonResponse(result).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取服务调用关系异常",tid,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(@RequestBody SysService info){
        long tid = System.nanoTime();
        try{
            boolean result = sysServiceService.add(info);
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{} 添加服务调用关系异常",tid,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }


    @RequestMapping("update")
    @ResponseBody
    public String update(@RequestBody SysService info){
        long tid = System.nanoTime();
        try{
            boolean result = sysServiceService.update(info);
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{} 添加服务关系信息异常",tid,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String del(
            @RequestParam(value = "idlist") String idlist
    ){
        long tid = System.nanoTime();
        List<Long> ids= CommonUtil.splitId(tid,idlist);
        try{
            boolean result = sysServiceService.delete(ids,"test");
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{} 添加服务关系信息异常",tid,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping("query")
    @ResponseBody
    public String query(String id){
        long tid = System.nanoTime();
        try{
            SysService sysService = sysServiceService.query(tid,Long.parseLong(id));
            return new JsonResponse(sysService).toJSON();
        }catch (Exception e){
            log.error("tid:{} 查询服务关系信息异常,id:{}",tid,id,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

}
