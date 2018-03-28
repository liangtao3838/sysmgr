package com.sys.mgr.controller;

import com.sys.mgr.model.SysService;
import com.sys.mgr.model.TradeDetail;
import com.sys.mgr.service.TradeDetailService;
import com.sys.mgr.utils.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/25.
 */
@RequestMapping("tradedetail")
@Controller
public class TradeDetailController {

    private final  static Logger log = LoggerFactory.getLogger(TradeDetailController.class);

    @Autowired
    TradeDetailService tradeDetailService;

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("tradedetail");
    }


    @RequestMapping("list")
    @ResponseBody
    public JsonResponse getList(
            @RequestParam(value = "syscallname") String syscallname,
            @RequestParam(value = "qqxt") String qqxt,
            @RequestParam(value = "zt") String zt,
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate,
            @RequestParam(value = "page",  defaultValue = "0") Integer offset,
            @RequestParam(value = "rows",  defaultValue = "10") Integer rows){
        long tid = System.nanoTime();
        try{
            List<TradeDetail> list =  tradeDetailService.getList(tid,syscallname,qqxt,zt,startDate,endDate,offset,rows);
            Map<String,Object> result = new HashMap<String,Object>();
            long count=tradeDetailService.getCount(tid,syscallname,qqxt,zt,startDate,endDate);
            result.put("rows",list);
            result.put("total",count);
            return new JsonResponse(result);
        }catch (Exception e){
            log.error("tid:{} 获取交易明细异常",tid,e);
            return JsonResponse.errorResponse(-1,"error");
        }
    }


    @RequestMapping("getxml")
    @ResponseBody
    public JsonResponse getXml(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "type") String type){
        long tid = System.nanoTime();
        try{
            String data =  tradeDetailService.getXMl(id,type);
            if(StringUtils.isEmpty(data)){
                data = "woshinidaye";
            }
            return new JsonResponse(data);
        }catch (Exception e){
            log.error("tid:{} 获取交易相应明细异常,id:{},type",tid,id,type,e);
            return JsonResponse.errorResponse(-1,"error");
        }
    }
}
