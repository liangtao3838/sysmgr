package com.sys.mgr.controller;

import com.sys.mgr.model.SysCallRela;
import com.sys.mgr.model.SysService;
import com.sys.mgr.service.SysCallRelaService;
import com.sys.mgr.service.SysServiceService;
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

    @RequestMapping(value = "/index",produces= "text/plain;charset=UTF-8")
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

    @RequestMapping(value = "add",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(SysService info){
        long tid = System.nanoTime();
        try{
            boolean result = sysServiceService.add(info);
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{} ,SysService:{}添加服务调用关系异常",tid,info.toString(),e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }


    @RequestMapping(value = "update",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String update(SysService info){
        long tid = System.nanoTime();
        try{
            boolean result = sysServiceService.update(info);
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{},SysService:{} 添加服务关系信息异常",tid,info.toString(),e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping(value = "delete",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String del(
            @RequestParam(value = "idlist") String idlist
    ){
        long tid = System.nanoTime();
        if(StringUtils.isEmpty(idlist)){
            return JsonResponse.errorResponse(-1,"请选择一条记录").toJSON();
        }
        List<Long> ids= CommonUtil.splitId(tid,idlist);
        try{
            boolean result = sysServiceService.delete(ids,"test");
            return new JsonResponse("ok").toJSON();
        }catch (Exception e){
            log.error("tid:{} ,id:{}删除服务关系信息异常",tid,idlist,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping(value = "query",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String query(String id){
        long tid = System.nanoTime();
        if(StringUtils.isEmpty(id)){
            return JsonResponse.errorResponse(-1,"请选择一条记录").toJSON();
        }
        try{
            SysService sysService = sysServiceService.query(tid,Long.parseLong(id));
            return new JsonResponse(sysService).toJSON();
        }catch (Exception e){
            log.error("tid:{} ,查询服务关系信息异常,id:{}",tid,id,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

}
