package com.sys.mgr.controller;

import com.sys.mgr.model.SysCallRela;
import com.sys.mgr.service.SysCallRelaService;
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
@RequestMapping("/syscallrela")
@Controller
public class SysCallRelaController {

    private final static Logger log = LoggerFactory.getLogger(SysCallRelaController.class);

    @Autowired
    SysCallRelaService sysCallRelaService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("syscallrela");
    }

    @RequestMapping("list")
    @ResponseBody
    public JsonResponse getList(
            @RequestParam(value = "page",  defaultValue = "0") Integer offset,
            @RequestParam(value = "rows",  defaultValue = "10") Integer rows
    ){
        long tid = System.nanoTime();
        try{
            List<SysCallRela> list =  sysCallRelaService.getList(tid,offset,rows);
            Map<String,Object> result = new HashMap<String,Object>();
            long count=sysCallRelaService.getCount(tid);
            result.put("rows",list);
            result.put("total",count);
            return new JsonResponse(result);
        }catch (Exception e){
            log.error("tid:{} 获取系统调用关系异常",tid,e);
            return JsonResponse.errorResponse(-1,"error");
        }
    }

    @RequestMapping("add")
    @ResponseBody
    public JsonResponse add(@RequestBody SysCallRela info){
        long tid = System.nanoTime();
        try{
            boolean result = sysCallRelaService.add(info);
            return new JsonResponse("ok");
        }catch (Exception e){
            log.error("tid:{} 添加系统调用关系异常",tid,e);
            return JsonResponse.errorResponse(-1,"error");
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public JsonResponse update(@RequestBody SysCallRela info){
        long tid = System.nanoTime();
        try{
            boolean result = sysCallRelaService.update(info);
            return new JsonResponse("ok");
        }catch (Exception e){
            log.error("tid:{} 添加调用关系信息异常",tid,e);
            return JsonResponse.errorResponse(-1,"error");
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonResponse del(
            @RequestParam(value = "idlist") String idlist
    ){
        long tid = System.nanoTime();
        List<Long> ids= CommonUtil.splitId(tid,idlist);
        try{
            boolean result = sysCallRelaService.delete(ids,"test");
            return new JsonResponse("ok");
        }catch (Exception e){
            log.error("tid:{} 添加调用关系信息异常",tid,e);
            return JsonResponse.errorResponse(-1,"error");
        }
    }

    @RequestMapping("query")
    @ResponseBody
    public JsonResponse query(String id){
        long tid = System.nanoTime();
        try{
            SysCallRela sysCallRela = sysCallRelaService.query(tid,Long.parseLong(id));
            return new JsonResponse(sysCallRela);
        }catch (Exception e){
            log.error("tid:{} 查询调用关系信息异常,id:{}",tid,id,e);
            return JsonResponse.errorResponse(-1,"error");
        }
    }

}
